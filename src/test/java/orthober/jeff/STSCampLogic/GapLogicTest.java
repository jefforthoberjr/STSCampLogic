package orthober.jeff.STSCampLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.*;

public class GapLogicTest {

	DateTimeFormatter df = DateTimeFormat.forPattern("YYYY-MM-dd");
	DateTime june05 = DateTime.parse("2016-06-05", df);
	DateTime june06 = DateTime.parse("2016-06-06", df);
	
	DateTime june07 = DateTime.parse("2016-06-07", df);
	DateTime june08 = DateTime.parse("2016-06-08", df);
	DateTime june09 = DateTime.parse("2016-06-09", df);
	DateTime june10 = DateTime.parse("2016-06-10", df);
	
	DateTime june11 = DateTime.parse("2016-06-11", df);
	DateTime june12 = DateTime.parse("2016-06-12", df);
	DateTime june13 = DateTime.parse("2016-06-13", df);
	
	Interval searchInterval1Days = new Interval(june07, june08);	//1 day reservation
	Interval searchInterval4Days = new Interval(june07, june11);	//4 day reservation
	
	@Test
	public void testNoReservations(){
		List<Interval> reservations = new ArrayList<Interval>();
		
		Optional<Interval> expectedResults1DaysBefore = Optional.empty();
		Optional<Interval> actualResults1DaysBefore = GapLogic.getNearestReservationBeforeSearchInterval(reservations, searchInterval1Days);
		Assert.assertEquals(expectedResults1DaysBefore, actualResults1DaysBefore);

		Optional<Interval> expectedResults1DaysAfter = Optional.empty();
		Optional<Interval> actualResults1DaysAfter = GapLogic.getNearestReservationAfterSearchInterval(reservations, searchInterval1Days);
		Assert.assertEquals(expectedResults1DaysAfter, actualResults1DaysAfter);
		
		Boolean expectedResults1DaysOverlap = false;
		Boolean actualResults1DaysOverlap = GapLogic.anyOverlapWithReservations(reservations, searchInterval1Days);
		Assert.assertEquals(expectedResults1DaysOverlap, actualResults1DaysOverlap);
	}
	
	@Test 
	public void testOnlyOverlaps() {
		
		List<Interval> reservations = new ArrayList<Interval>();
		reservations.add(new Interval(june06, june08));	//2 day reservation; 1 day overlap with search start
		reservations.add(new Interval(june08, june09));  //1 day reservation; overlap between search start and search end
		reservations.add(new Interval(june10, june12));	//2 day reservation; 1 day overlap with search end
		
		Optional<Interval> expectedResults4DaysBefore = Optional.empty();
		Optional<Interval> actualResults4DaysBefore = GapLogic.getNearestReservationBeforeSearchInterval(reservations, searchInterval4Days);
		Assert.assertEquals(expectedResults4DaysBefore, actualResults4DaysBefore);

		Optional<Interval> expectedResults4DaysAfter = Optional.empty();
		Optional<Interval> actualResults4DaysAfter = GapLogic.getNearestReservationAfterSearchInterval(reservations, searchInterval4Days);
		Assert.assertEquals(expectedResults4DaysAfter, actualResults4DaysAfter);
		
		Boolean expectedResults4DaysOverlap = true;
		Boolean actualResults4DaysOverlap = GapLogic.anyOverlapWithReservations(reservations, searchInterval4Days);
		Assert.assertEquals(expectedResults4DaysOverlap, actualResults4DaysOverlap);
	}
	
	@Test
	public void testOverlapExactly() {
		
		List<Interval> reservations = new ArrayList<Interval>();
		reservations.add(searchInterval4Days);  //overlap exactly
		
		Optional<Interval> expectedResults4DaysBefore = Optional.empty();
		Optional<Interval> actualResults4DaysBefore = GapLogic.getNearestReservationBeforeSearchInterval(reservations, searchInterval4Days);
		Assert.assertEquals(expectedResults4DaysBefore, actualResults4DaysBefore);

		Optional<Interval> expectedResults4DaysAfter = Optional.empty();
		Optional<Interval> actualResults4DaysAfter = GapLogic.getNearestReservationAfterSearchInterval(reservations, searchInterval4Days);
		Assert.assertEquals(expectedResults4DaysAfter, actualResults4DaysAfter);
		
		Boolean expectedResults4DaysOverlap = true;
		Boolean actualResults4DaysOverlap = GapLogic.anyOverlapWithReservations(reservations, searchInterval4Days);
		Assert.assertEquals(expectedResults4DaysOverlap, actualResults4DaysOverlap);

	}
	
	@Test
	public void testNoOverlaps() {
		Interval abutBefore = new Interval(june06, june07);
		Interval abutAfter = new Interval(june12, june13);
		
		List<Interval> reservations = new ArrayList<Interval>();
		reservations.add(new Interval(june05, june06));	//1 day gap before
		reservations.add(abutBefore);	//abut before
		reservations.add(abutAfter);	//abut after
		reservations.add(new Interval(june12, june13));	//1 day gap after
		

		Optional<Interval> expectedResults4DaysBefore = Optional.of(abutBefore);
		Optional<Interval> actualResults4DaysBefore = GapLogic.getNearestReservationBeforeSearchInterval(reservations, searchInterval4Days);
		Assert.assertEquals(expectedResults4DaysBefore, actualResults4DaysBefore);

		Optional<Interval> expectedResults4DaysAfter = Optional.of(abutAfter);
		Optional<Interval> actualResults4DaysAfter = GapLogic.getNearestReservationAfterSearchInterval(reservations, searchInterval4Days);
		Assert.assertEquals(expectedResults4DaysAfter, actualResults4DaysAfter);
		
		Boolean expectedResults4DaysOverlap = false;
		Boolean actualResults4DaysOverlap = GapLogic.anyOverlapWithReservations(reservations, searchInterval4Days);
		Assert.assertEquals(expectedResults4DaysOverlap, actualResults4DaysOverlap);
	}
	
}
