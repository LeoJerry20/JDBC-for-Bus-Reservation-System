package busReserve;
import java.util.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Booking {
 String passengerName;
 int busNo;
 Date date;

Booking(){
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter name of passenger: ");
	passengerName =sc.next();
	System.out.println("Enter bus no: ");
	busNo =sc.nextInt();
	System.out.println("Enter date dd-mm-yyyy");
	String dateInput=sc.next();
	SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
     try {
		date= dateFormat.parse(dateInput);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public boolean isAvailable() throws SQLException {
	int capacity=0;
	  BusDAO busDao=new BusDAO();     
	capacity= busDao.getCapacity(busNo);
	int booked=0;
	BookingDao bookingdao=new BookingDao();
	 booked=bookingdao.getBookedCount(busNo,date);	
	return booked<capacity;
}
}