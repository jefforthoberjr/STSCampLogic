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
public class GapRuleController {

    //This classed use to verify JSON consumption of gaprule objects

    @RequestMapping(value = "/gaprule")
    public void campsite(@RequestBody GapRule gapRule) {
    		System.out.println("Just got a request for /gaprule.");
    		System.out.println(gapRule.getGapSize());
    }
}
