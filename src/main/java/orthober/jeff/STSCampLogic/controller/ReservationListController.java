package orthober.jeff.STSCampLogic.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orthober.jeff.STSCampLogic.model.Reservation;
import orthober.jeff.STSCampLogic.model.ReservationList;

/**
 * Controller
 * @author jeffryorthober
 *
 */
@RestController
public class ReservationListController {

    //This class is used to verify JSON consumption of reservation objects

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
