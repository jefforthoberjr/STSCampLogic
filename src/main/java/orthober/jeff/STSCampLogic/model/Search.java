package orthober.jeff.STSCampLogic.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Domain object
 * @author jeffryorthober
 *
 */
public class Search{
	
	private Date startDate;
	private Date endDate;
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String date) throws ParseException {
		this.startDate = df.parse(date);
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String date) throws ParseException {
		this.endDate = df.parse(date);
	}

}