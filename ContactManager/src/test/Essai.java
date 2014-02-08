package test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import agenda.Contact;
import agenda.ContactImpl;
import agenda.ContactManager;
import agenda.ContactManagerImpl;
import agenda.Meeting;
import agenda.MeetingImpl;
import agenda.Utilities_CM;

public class Essai {
	
	private ContactManager cm = null;

	public static void main(String[] args) {
		Essai ex = new Essai();
		ex.launch();
	}

	private void launch() {
		Utilities_CM ucm = new Utilities_CM();
		 ucm.setDate(1, 3, 2014);
		Contact c1 = new ContactImpl(1, "Jean", "vide");
		Contact c2 = new ContactImpl(1, "bill", "cravate");
		cm =  new ContactManagerImpl();
		cm.addNewContact("Jean", "vide");
		cm.addNewContact("bill", "cravate");
		
		Set<Contact> setActual = new HashSet<Contact>();
		setActual = cm.getContacts(1, 2);
		int i = setActual.size();
		
		Iterator<Contact> it = setActual.iterator();
		while (it.hasNext()){
			Contact obj = it.next();
			System.out.println(obj.getId());
		}
		
		Calendar today = Calendar.getInstance();
		Calendar futureDay1 = ucm.setDate(1, 3, 2014);
		Calendar futureDay2 = ucm.setDate(5, 3, 2014);
		Calendar pastDay1 = ucm.setDate(1, 2, 2014);
		
		cm.addFutureMeeting(setActual, futureDay1);
		
		
	}

}
