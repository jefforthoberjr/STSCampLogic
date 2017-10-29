package orthober.jeff.STSCampLogic.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orthober.jeff.STSCampLogic.model.GapRule;
import orthober.jeff.STSCampLogic.model.GapRuleList;

/**
 * Controller
 * @author jeffryorthober
 *
 */
@RestController
public class GapRulesController {

    //This class is used to verify JSON consumption of gaprule objects

    @RequestMapping(value = "/gaprulelist")
    public void gapRuleList(@RequestBody GapRuleList gapRuleList) {
    		System.out.println("Just got a request for /gaprulelist.");
    		for(GapRule g : gapRuleList.getGapRules() ) {
	    		System.out.println(g.getGapSize());
    		}
    }
}
