package orthober.jeff.STSCampLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import orthober.jeff.STSCampLogic.model.AvailableCampsite;
import orthober.jeff.STSCampLogic.model.Query;
import orthober.jeff.STSCampLogic.model.Reservation;

import org.joda.time.Interval;

public class GapLogic {

	/**
	 * Prevents new reservations on a campsite that will create gaps of a specified size
	 */
	
	public static AvailableCampsite[] findAllAvailableCampspots(Query q) {
		
		//We add +1 day to endDate, to ensure the inclusive date range takes up the full day of the last day of the reservation 
		Interval searchInterval = new Interval(q.getSearch().getStartDate(), q.getSearch().getEndDate().plusDays(1));
		
		//Reservation validity to be determined independently for each campsite
		Map<Long, List<Interval>> reservationsBySite = new HashMap<Long, List<Interval>>();
		Arrays.stream(q.getCampsites()).forEach(c -> 
			reservationsBySite.put(c.getId(), new ArrayList<Interval>()));
		
		//Sort all reservations into a map, and convert dates range to Interval	
		Arrays.stream(q.getReservations()).forEach(r -> 
			reservationsBySite.get(r.getCampsiteId())
			.add(new Interval(r.getStartDate(), r.getEndDate())));
		
		//Does the search overlap with any existing reservations?
		//TODO calls to searchInterval.overlaps(...)
		
		//What are the range of gaps the reservation creates?
		//TODO calls to searchInterval.gap(...)
		
		//Hardcoded example for testing
		AvailableCampsite siteA = new AvailableCampsite();
		siteA.setName("FOOBAR CAMP SITE A");
		AvailableCampsite siteB = new AvailableCampsite();
		siteB.setName("FOOBAR CAMP SITE B");
		AvailableCampsite[] result = {siteA, siteB};
		
		return result;
	}
	
}
