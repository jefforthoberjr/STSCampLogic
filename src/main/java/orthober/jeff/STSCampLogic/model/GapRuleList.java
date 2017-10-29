package orthober.jeff.STSCampLogic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain object
 * @author jeffryorthober
 *
 */
public class GapRuleList {
	private List<GapRule> gapRules = new ArrayList<GapRule>();
	
	public List<GapRule> getGapRules(){
		return gapRules;
	}
	
	public void setGapRuleList(List<GapRule> gapRules){
		this.gapRules = gapRules;
	}
}