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
public class CampsiteListController {

    //This classed use to verify JSON consumption of campsite objects

    @RequestMapping(value = "/campsitelist")
    public void campsiteList(@RequestBody CampsiteList campsiteList) {
    		System.out.println("Just got a request for /campsitelist.");
    		for(Campsite c : campsiteList.getCampsites() ) {
	    		System.out.println(c.getId());
	    		System.out.println(c.getName());
    		}
    }
}
