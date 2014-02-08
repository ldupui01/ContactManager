package agenda;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;

public class Utilities_CM {

	
	public Calendar setDate(String s){
	    int year = Integer.parseInt(s.substring(6, 10));
	    int month = (Integer.parseInt(s.substring(3, 4))) - 1; // -1 because the month start at 0 for January
	    int date = Integer.parseInt(s.substring(0, 1));
	    Calendar cal = Calendar.getInstance();
	    cal.clear();
	    cal.set(year, month, date);
		return cal;
	}
	
	public Calendar setDate(int D, int M, int Y){
	    M = M -1; // -1 because the month start at 0 for January
	  
	    Calendar cal = Calendar.getInstance();
	    cal.clear();
	    cal.set(Y, M, D);
		return cal;
	}
	
	
	public Calendar today(){
		Calendar today =  Calendar.getInstance();
		return today;
	}
	

}
