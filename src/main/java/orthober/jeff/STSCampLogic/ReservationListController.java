package orthober.jeff.STSCampLogic;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller
 * @author jeffryorthober
 *
 */
@RestController
public class ReservationListController {

    //This classed use to verify JSON consumption of reservation objects

    @RequestMapping(value = "/reservationlist")
    public void campsiteList(@RequestBody ReservationList reservationList) {
    		System.out.println("Just got a request for /reservationlist.");
    		for(Reservation r : reservationList.getReservations() ) {
	    		System.out.println(r.getCampsiteId());
	    		System.out.println(r.getStartDate());
	    		System.out.println(r.getEndDate());
    		}
    }
}