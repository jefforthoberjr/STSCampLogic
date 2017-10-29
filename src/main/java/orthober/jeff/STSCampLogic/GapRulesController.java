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
public class GapRulesController {

    //This classed use to verify JSON consumption of gaprule objects

    @RequestMapping(value = "/gaprulelist")
    public void gapRuleList(@RequestBody GapRuleList gapRuleList) {
    		System.out.println("Just got a request for /gaprulelist.");
    		for(GapRule g : gapRuleList.getGapRules() ) {
	    		System.out.println(g.getGapSize());
    		}
    }
}
