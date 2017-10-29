package orthober.jeff.STSCampLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain object
 * @author jeffryorthober
 *
 */
public class ReservationList {
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	public List<Reservation> getReservations(){
		return reservations;
	}
	
	public void setReservationsList(List<Reservation> reservations){
		this.reservations = reservations;
	}
}