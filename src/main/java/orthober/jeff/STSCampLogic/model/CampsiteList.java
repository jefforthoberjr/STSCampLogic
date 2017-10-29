package orthober.jeff.STSCampLogic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain object
 * @author jeffryorthober
 *
 */
public class CampsiteList {
	private List<Campsite> campsites = new ArrayList<Campsite>();
	
	public List<Campsite> getCampsites(){
		return campsites;
	}
	
	public void setCampsiteList(List<Campsite> campsites){
		this.campsites = campsites;
	}
}
