package orthober.jeff.STSCampLogic.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orthober.jeff.STSCampLogic.model.Reservation;

/**
 * Controller
 * @author jeffryorthober
 *
 */
@RestController
public class ReservationController {

    //This class is used to verify JSON consumption of reservation objects

    @RequestMapping(value = "/reservation")
    public void campsite(@RequestBody Reservation reservation) {
    		System.out.println("Just got a request for /reservation.");
    		System.out.println(reservation.getCampsiteId());
    		System.out.println(reservation.getStartDate());
    		System.out.println(reservation.getEndDate());
    }
}

