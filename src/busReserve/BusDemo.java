package busReserve;

import java.util.Scanner;

public class BusDemo {
public static void main(String[] args){
	 try {
	BusDAO busdao = new BusDAO();
	busdao.displayBusInfo();
	
	
	int userOpt=1;
	Scanner sc=new Scanner(System.in);
	
	while(userOpt==1){
	System.out.println("Enter 1 to Book and 2 to exit");
	userOpt = sc.nextInt();
	if(userOpt==1) {
		Booking booking=new Booking();
		if(booking.isAvailable()) {
			BookingDao bookingDao=new BookingDao();
			bookingDao.addBooking(booking);
			System.out.println("Your booking is conformed");
		}
		else {
			System.out.println("Sorry. Bus is full. Try another bus or date.");
		}
	}
}
	sc.close();
	 }
	 catch (Exception e) {
		 System.out.println(e);
	 }
}
}
