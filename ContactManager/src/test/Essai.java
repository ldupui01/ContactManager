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

public class Essai {
	
	private ContactManager cm = null;

	public static void main(String[] args) {
		Essai ex = new Essai();
		ex.launch();
	}

	private void launch() {
		cm =  new ContactManagerImpl();
		cm.addNewContact("Jean", "vide");
		cm.addNewContact("bill", "notes");
		
		Set<Contact> setActual = new HashSet<Contact>();
		setActual = cm.getContacts(1, 2);
		int i = setActual.size();
		
		Iterator<Contact> it = setActual.iterator();
		while (it.hasNext()){
			Contact obj = it.next();
			System.out.println(obj.getId());
		}
	}

}
