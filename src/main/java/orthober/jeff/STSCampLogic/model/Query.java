package orthober.jeff.STSCampLogic.model;

public class Query {

	private Search search;
	private Campsite[] campsites;
	private GapRule[] gapRules;
	private Reservation[] reservations;
	
	public Search getSearch() {
		return search;
	}
	
	public void setSearch(Search search) {
		this.search = search;
	}
	
	public Campsite[] getCampsites() {
		return campsites;
	}
	
	public void setCampsites(Campsite[] campsites) {
		this.campsites = campsites;
	}
	
	public GapRule[] getGapRules() {
		return gapRules;
	}
	
	public void setGapRules(GapRule[] gapRules) {
		this.gapRules = gapRules;
	}
	
	public Reservation[] getReservations() {
		return reservations;
	}
	
	public void setReservations(Reservation[] reservations) {
		this.reservations = reservations;
	}
	
}
