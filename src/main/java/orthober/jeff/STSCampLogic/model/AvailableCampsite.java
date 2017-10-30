package orthober.jeff.STSCampLogic.model;

public class AvailableCampsite {

	//This is a return object for queries; a simpler version of Campsite
	
	private String name;
	
	public AvailableCampsite(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
