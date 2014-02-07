package test;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import agenda.ContactManagerImpl;

public class Essai {

	public static void main(String[] args) {
		Essai ex = new Essai();
		ex.launch();
	}

	private void launch() {
		//ContactManagerImpl cmi = new ContactManagerImpl();
		//cmi.readTextFile();
		//Calendar cal = cmi.today();
		//System.out.println(cal.getTime());

		//System.out.println(cmi.findId("meeting"));
		
		
		String A = "Blablombadoum";
		String template = "Blablo";
		
		if (A.matches("(?i).*"+ template +".*")){
			System.out.println("OK");
		}else{
			System.out.println("Not ok");
		}
			
	}

}
