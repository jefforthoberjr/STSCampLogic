package orthober.jeff.STSCampLogic;

import java.util.Arrays;
import java.util.List;
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
		
		//Interval foo = new Interval(q.getSearch().getStartDate(), q.getSearch().getEndDate());
		
		//Convert all reservations dates ranges to Interval
		Stream<Reservation> f = Arrays.stream(q.getReservations());
		List<Interval> foo = f.map(r -> new Interval(r.getStartDate(), r.getEndDate())).collect(Collectors.toList());
		
		//Hardcoded example for testing
		AvailableCampsite siteA = new AvailableCampsite();
		siteA.setName("FOOBAR CAMP SITE A");
		AvailableCampsite siteB = new AvailableCampsite();
		siteB.setName("FOOBAR CAMP SITE B");
		AvailableCampsite[] result = {siteA, siteB};
		
		return result;
	}
	
}
