package orthober.jeff.STSCampLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import orthober.jeff.STSCampLogic.model.AvailableCampsite;
import orthober.jeff.STSCampLogic.model.Campsite;
import orthober.jeff.STSCampLogic.model.Query;
import orthober.jeff.STSCampLogic.model.Reservation;

import org.joda.time.Interval;

public class GapLogic {

	/**
	 * Prevents new reservations on a campsite that will create gaps of a specified size
	 */
	
	public static AvailableCampsite[] findAllAvailableCampspots(Query q) {
		List<Long> validSiteIds = new ArrayList<Long>();
		
		//add +1 day to endDate, to ensure the range fully encompasses the last day 
		Interval searchInterval = new Interval(q.getSearch().getStartDate(), q.getSearch().getEndDate().plusDays(1));
		
		Map<Long, List<Interval>> reservationsBySite = groupReservationsBySite(q.getCampsites(), q.getReservations());
		
		//Reservation validity is determined independently for each campsite
		for(Long siteId : reservationsBySite.keySet()) {
			
			if(anyOverlapWithReservations(reservationsBySite.get(siteId), searchInterval)) {
				System.out.println("reservation cannot be made for site " + siteId + " . It overlaps an existing reservation.");
				continue;
			}
			
			//Check: Would the search create any unwanted gaps?
			Optional<Interval> reservationBefore = getNearestReservationBeforeSearchInterval(reservationsBySite.get(siteId), searchInterval);			
			Optional<Interval> reservationAfter = getNearestReservationAfterSearchInterval(reservationsBySite.get(siteId), searchInterval);

			//DEBUG
			//System.out.println("For site " + siteId + 
			//		" the nearest reservation before search inverval is " + reservationBefore);
			//System.out.println("For site " + siteId + 
			//		" the nearest reservation after search inverval is " + nearestReservationAfterSearchInterval);
			
			List<Long> gapsToAvoid = Arrays.stream(q.getGapRules())
					.map(g -> g.getGapSize())
					.collect(Collectors.toList());
			
			if(isInViolationOfGapRules(searchInterval, reservationBefore, reservationAfter, gapsToAvoid) ){
				System.out.println("reservation cannot be made for site " + siteId + " . It would create a gap that violates the gap rules.");
				continue;
			}
			
			System.out.println("Reservation is valid for site " + siteId);
			validSiteIds.add(siteId);
		}
		
		//Hardcoded example for testing
		AvailableCampsite siteA = new AvailableCampsite();
		siteA.setName("FOOBAR CAMP SITE A");
		AvailableCampsite siteB = new AvailableCampsite();
		siteB.setName("FOOBAR CAMP SITE B");
		AvailableCampsite[] result = {siteA, siteB};
		
		return result;
	}
	
	/**
	 * Returns 0 or 1 Interval.
	 * Note: There may be no existing reservations prior to the search interval
	 * 
	 * Reservations ending after searchInterval are ignored.
	 * Reservations overlapping searchInterval are ignored.
	 * 
	 * Abutting reservations are counted still as 'before' (i.e. not ignored).
	 * 
	 * Undetermined behavior if multiple reservations have the save endDate. 
	 * 
	 * @param reservations
	 * @param searchInterval
	 * @return
	 */
	public static Optional<Interval> getNearestReservationBeforeSearchInterval(List<Interval> reservations, Interval searchInterval) {
		return reservations.stream()
			.filter(r -> !r.getEnd().isAfter(searchInterval.getStart()))
			.max((r1, r2) -> r1.getEnd().compareTo(r2.getEnd()));
	}
	
	/**
	 * Returns 0 or 1 Interval.
	 * Note: There may be no existing reservations after to the search interval
	 * 
	 * Reservations starting before searchInterval are ignored.
	 * Reservations overlapping searchInterval are ignored.
	 * 
	 * Abutting reservations are counted still as 'after' (i.e. not ignored).
	 * 
	 * Undetermined behavior if multiple reservations have the save startDate. 
	 * 
	 * @param reservations
	 * @param searchInterval
	 * @return
	 */
	public static Optional<Interval> getNearestReservationAfterSearchInterval(List<Interval> reservations, Interval searchInterval){
		return reservations.stream()
			.filter(r -> !r.getStart().isBefore(searchInterval.getEnd()))
			.min((r1, r2) -> r1.getStart().compareTo(r2.getStart()));
	}
	
	/**
	 * Returns true is at least 1 reservation overlaps with searchInterval.
	 * Abutting reservations do not count as overlap.
	 * 
	 * @param reservations
	 * @param searchInterval
	 * @return
	 */
	public static Boolean anyOverlapWithReservations(List<Interval> reservations, Interval searchInterval) {
		return reservations.stream()
				.map(r -> searchInterval.overlaps(r))
				.anyMatch(o -> o==true);
	}
	
	/**
	 * Covert a flat list of campsite into a map grouped by siteId
	 * 
	 * Note: Some siteIds may have no reservations (i.e. empty list)
	 * 
	 * Assumes all campsiteIds all have referential integrity with campside.ids
	 *  
	 * @param campsites
	 * @param reservations
	 * @return
	 */
	public static Map<Long, List<Interval>> groupReservationsBySite(Campsite[] campsites, Reservation[] reservations){
		//Reservation validity to be determined independently for each campsite
		Map<Long, List<Interval>> reservationsBySite = new HashMap<Long, List<Interval>>();
		Arrays.stream(campsites).forEach(c -> 
			reservationsBySite.put(c.getId(), new ArrayList<Interval>()));
		
		//Sort all reservations into a map, and convert dates range to Interval	
		//add +1 day to endDate, to ensure the range fully encompasses the last day
		Arrays.stream(reservations).forEach(r -> 
			reservationsBySite.get(r.getCampsiteId())
			.add(new Interval(r.getStartDate(), r.getEndDate().plusDays(1))));
		
		return reservationsBySite;
	}
	
	/**
	 * 
	 * @param searchInterval
	 * @param reservationBefore
	 * @param reservationAfter
	 * @param gapsToAvoid
	 * @return
	 */
	public static Boolean isInViolationOfGapRules(Interval searchInterval, Optional<Interval> reservationBefore, Optional<Interval> reservationAfter, List<Long> gapsToAvoid) {
		Long gapBefore = 0L;
		if(reservationBefore.isPresent()) {
			Interval intervalBefore = searchInterval.gap(reservationBefore.get());
			if(intervalBefore != null) {
				gapBefore = intervalBefore.toDuration().getStandardDays();
			}
		}
		
		Long gapAfter = 0L;
		if(reservationAfter.isPresent()) {
			Interval intervalAfter = searchInterval.gap(reservationAfter.get());
			if(intervalAfter != null) {
				gapAfter = intervalAfter.toDuration().getStandardDays();
			}
		}
		
		return (gapsToAvoid.contains(gapBefore) || gapsToAvoid.contains(gapAfter));
	}
	
}
