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
import agenda.FutureMeeting;
import agenda.FutureMeetingImpl;
import agenda.Meeting;
import agenda.MeetingImpl;


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
		Set<Contact> singleton = new HashSet<Contact>();
		singleton = cm.getContacts(2);
		int i = setActual.size();
		
		Iterator<Contact> it = setActual.iterator();
		while (it.hasNext()){
			Contact obj = it.next();
		}
		
		Calendar today = Calendar.getInstance();
		Calendar futureDay1 = ucm.setDate(15, 8, 2014);
		Calendar futureDay2 = ucm.setDate(12, 8, 2015);
		Calendar pastDay1 = ucm.setDate(1, 2, 2014);
	//	System.out.println(cm.getSize());
		int j = cm.addFutureMeeting(setActual, futureDay1);
	//	System.out.println(cm.getSize());
		int f = cm.addFutureMeeting(singleton, futureDay2);
		int g = cm.addFutureMeeting(singleton, futureDay1);
		
	//	System.out.println(cm.getSize());
		
		FutureMeeting fm = cm.getFutureMeeting(1);
		System.out.println("HERE" + fm.getId());
		Meeting mClass = cm.getMeeting(j);
		if (fm == null){
			System.out.println("NULL");
		}
		
		System.out.println(cm.getSizeMeeting());
		cm.flush();
		
		/*Meeting m1 = new FutureMeetingImpl (1, pastDay1, singleton);
		Meeting m2 = new FutureMeetingImpl (2, futureDay1, singleton);
		Meeting m3 =  new MeetingImpl(3, futureDay2, setActual);
		
		Set<Meeting> setM = new HashSet<Meeting>();
		
		setM.add(m1);
		setM.add(m2);
		setM.add(m3);
		
		
		Iterator<Meeting> itm = setM.iterator();
		while (itm.hasNext()){
			Meeting obj = itm.next();
			System.out.println(obj.getId());
		}	
		
		
		//System.out.println(mClass.getId());
		//if(cm.getMeeting(j).getClass().equals(FutureMeetingImpl.class)){
		//	System.out.println("future meeting");
		//}*/
		
	}

}
