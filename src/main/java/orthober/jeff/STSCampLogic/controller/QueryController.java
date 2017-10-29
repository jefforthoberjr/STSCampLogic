package orthober.jeff.STSCampLogic.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orthober.jeff.STSCampLogic.model.Query;

/**
 * Controller
 * @author jeffryorthober
 *
 */
@RestController
public class QueryController {

    //This class is used to verify JSON consumption of query objects

    @RequestMapping(value = "/query")
    public void campsite(@RequestBody Query query) {
    		System.out.println("Just got a request for /query.");
    		System.out.println("search start " + query.getSearch().getStartDate());
    		System.out.println("search end " + query.getSearch().getEndDate());
    		System.out.println("num of campsites " + query.getCampsites().length);
    		System.out.println("num of rules " + query.getGapRules().length);
    		System.out.println("num of reservations " + query.getReservations().length);
    }
}
