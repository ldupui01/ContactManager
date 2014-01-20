package test;

import java.util.Calendar;

import agenda.ContactManagerImpl;

public class Essai {

	public static void main(String[] args) {
		Essai ex = new Essai();
		ex.launch();
	}

	private void launch() {
		ContactManagerImpl cmi = new ContactManagerImpl();
		cmi.readTextFile();
		
		/*Calendar now = Calendar.getInstance();
	    // 
	    System.out.println("Current Year is : " + now.get(Calendar.YEAR));
	    // month start from 0 to 11
	    System.out.println("Current Month is : " + (now.get(Calendar.MONTH) + 1));
	    System.out.println("Current Date is : " + now.get(Calendar.DATE));
	    */
	}

}
