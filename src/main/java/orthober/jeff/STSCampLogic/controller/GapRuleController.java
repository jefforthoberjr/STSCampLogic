package orthober.jeff.STSCampLogic.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orthober.jeff.STSCampLogic.model.GapRule;

/**
 * Controller
 * @author jeffryorthober
 *
 */
@RestController
public class GapRuleController {

    //This class is used to verify JSON consumption of gaprule objects

    @RequestMapping(value = "/gaprule")
    public void campsite(@RequestBody GapRule gapRule) {
    		System.out.println("Just got a request for /gaprule.");
    		System.out.println(gapRule.getGapSize());
    }
}
