package orthober.jeff.STSCampLogic.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orthober.jeff.STSCampLogic.model.Campsite;

/**
 * Controller
 * @author jeffryorthober
 *
 */
@RestController
public class CampsiteController {

    //This classed use to verify JSON consumption of campsite objects

    @RequestMapping(value = "/campsite")
    public void campsite(@RequestBody Campsite campsite) {
    		System.out.println("Just got a request for /campsite.");
    		System.out.println(campsite.getId());
    		System.out.println(campsite.getName());
    }
}
