package orthober.jeff.STSCampLogic.model;

/**
 * Domain object
 * @author jeffryorthober
 *
 */
public class Reservation{
	
	private long campsiteId;
	private String startDate;
	private String endDate;
	
	public long getCampsiteId() {
		return campsiteId;
	}
	
	public void setCampsiteId(long id) {
		this.campsiteId = id;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String date) {
		this.startDate = date;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String date) {
		this.endDate = date;
	}

}
