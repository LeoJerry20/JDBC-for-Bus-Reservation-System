package busReserve;

public class bus {
private int busNo;
private boolean ac;
private int capacity;  //get and set

bus(int no,boolean ac,int cap){
	this.busNo=no;
	this.ac=ac;
	this.capacity=cap;
}
public int getBusNo(){
	return busNo;
}
public boolean isAc() {
	return ac;
}
public int getCapacity() {
	return capacity;
}
public void setAc(boolean val) {
	ac=val;
}
public void setCapacity(int cap) {
	capacity=cap;
}

public void displayBusInfo() {
	System.out.println("Bus NO: "+busNo+" Ac: "+ac+" Total Capacity: "+capacity);
}
}
