package orthober.jeff.STSCampLogic.model;

import java.text.ParseException;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Domain object
 * @author jeffryorthober
 *
 */
public class Reservation{
	
	private long campsiteId;
	private DateTime startDate;
	private DateTime endDate;
	
	//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	DateTimeFormatter df = DateTimeFormat.forPattern("YYYY-MM-dd");
	
	public long getCampsiteId() {
		return campsiteId;
	}
	
	public void setCampsiteId(long id) {
		this.campsiteId = id;
	}
	
	public DateTime getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String date) throws ParseException {
		this.startDate = DateTime.parse(date, df);
	}
	
	public DateTime getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String date) throws ParseException {
		this.endDate = DateTime.parse(date, df);
	}

}
