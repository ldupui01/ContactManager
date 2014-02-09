package test;

import agenda.Contact;
import agenda.ContactImpl;
import agenda.ContactManager;
import agenda.ContactManagerImpl;
import agenda.FutureMeeting;
import agenda.FutureMeetingImpl;
import agenda.Meeting;
import agenda.MeetingImpl;
import agenda.PastMeeting;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class TestRunComplete {
	public static void main(String[] args) {
		TestRunComplete ex = new TestRunComplete();
		ex.launch();
	}

	private void launch() {
		
		Utilities_CM util = new Utilities_CM();
		Calendar today = util.setDate(1, 3, 2014);
		
		ContactManager cm = new ContactManagerImpl();
		/******** Setting TEST_1 *****/
		/*Create contact*
		
		cm.addNewContact("JEAN", "bleu");
		cm.addNewContact("Michel", "rouge");
		cm.addNewContact("Henry", "Jaune"); 
		/***/
		
		Set<Contact> setCM1 = new HashSet<Contact>();
		setCM1= cm.getContacts("Jean");
		Set<Contact> setCM2 = new HashSet<Contact>();
		setCM2 = cm.getContacts(1, 2);
		//setCM2 = cm.getContacts("Michel");
		Set<Contact> setCM3 = new HashSet<Contact>();
		setCM3 = cm.getContacts(1);
		Set<Contact> setCM4 = new HashSet<Contact>();
		setCM4 = cm.getContacts("Henry");
		Set<Contact> setCM5 = new HashSet<Contact>();
		setCM5 = cm.getContacts("Jean");
		Set<Contact> setCM6 = new HashSet<Contact>();
		setCM6 = cm.getContacts(1, 3);
		//setCM6 = cm.getContacts("Henry");
		
		/*create meeting*/
		
		Calendar dateM1 = util.setDate(2, 1, 2014);
		Calendar dateM2 = util.setDate(6, 1, 2014);
		Calendar dateM3 = util.setDate(16, 1, 2014);
		Calendar dateM4 = util.setDate(10, 2, 2014);
		Calendar dateM5 = util.setDate(20, 2, 2014);
		Calendar dateM6 = util.setDate(15, 8, 2014);
		
		Calendar dateM7 = util.setDate(2, 1, 2015);
		Calendar dateM8 = util.setDate(6, 1, 2016);
		Calendar dateM9 = util.setDate(16, 1, 2015);
		Calendar dateM10 = util.setDate(10, 2, 2013);
		Calendar dateM11 = util.setDate(20, 2, 2012);
		Calendar dateM12 = util.setDate(15, 8, 2013);
		
		//System.out.println(": " + dateM1.DAY_OF_MONTH + "/" + dateM1.MONTH + "/" + dateM1.YEAR);
		//System.out.println(": " + dateM2.DAY_OF_MONTH + "/" + dateM2.MONTH + "/" + dateM2.YEAR);
		
		
		/***
		cm.addNewPastMeeting(setCM1, dateM1, "OK");  					//M1
		cm.addNewPastMeeting(setCM2, dateM2, "Chaud"); 					//M2
		cm.addNewPastMeeting(setCM3, dateM3, "Difficile");				//M3
		cm.addFutureMeeting(setCM4, dateM4);							//M4
		cm.addFutureMeeting(setCM5, dateM5);							//M5
		cm.addFutureMeeting(setCM6, dateM6);							//M6
		cm.addFutureMeeting(setCM6, dateM4);							//M7

		cm.addFutureMeeting(setCM1, dateM7);							//M8
		cm.addFutureMeeting(setCM2, dateM8);							//M9
		cm.addFutureMeeting(setCM3, dateM9);							//M10
		cm.addNewPastMeeting(setCM4, dateM10, "houlalala");				//M11
		cm.addNewPastMeeting(setCM5, dateM11, "meeting cancelled");		//M12
		cm.addNewPastMeeting(setCM6, dateM12, "working in progress");	//M13
		/****/
		/*********************** Test_1 end***************/
		
		//System.out.println("contact: " + cm.getSizeContact() + " meeting: " + cm.getSizeMeeting()); 
		
		Contact retriveContact = null;
		Set<Contact> setRetrive = cm.getContacts(1);
		Iterator<Contact> it = setRetrive.iterator();
		while (it.hasNext()){
			Contact obj = it.next();
			retriveContact = obj;
			System.out.println(retriveContact.getName());
			List<PastMeeting> lpm = cm.getPastMeetingList(retriveContact);
			List<Meeting> lfm = cm.getFutureMeetingList(retriveContact);
			System.out.println("PastMeeting: " + lpm.size());

			System.out.println("FutureMeeting: " + lfm.size());
			System.out.println("in chronological order:");
			Iterator<Meeting> itm = lfm.iterator();
			while (itm.hasNext()){
				Meeting mObj = itm.next();
				System.out.println(mObj.getId());
			}
		}
		
		//cm.addFutureMeeting(setCM6, dateM4);
		
		List<Meeting> ldm = cm.getFutureMeetingList(dateM4);
		System.out.println("FutureMeeting on the " + dateM4.DAY_OF_MONTH + "/" + dateM4.MONTH + "/" + dateM4.YEAR + ": "+ ldm.size());
		

		for (int i=0;i<13;i++){ //replace 13 with cm.getSizeMeeting()
			Meeting m = cm.getMeeting(i+1);
			//System.out.println(m.getId() + ": " + m.getDate().DAY_OF_MONTH + "/" + m.getDate().MONTH + "/" + m.getDate().YEAR);
			System.out.println(m.getId() + ": " + m.getDate().toString());
			if(m.getClass() == FutureMeetingImpl.class){
				FutureMeeting fm = cm.getFutureMeeting(i+1);
				System.out.println(fm.getId() + " is a meeting in the future");
			}
		}
		
		cm.addMeetingNotes(1, "new message here");
		
		PastMeeting mNote = (PastMeeting)cm.getMeeting(1);
		System.out.println(mNote.getNotes());
		
		//PastMeeting mNote2 = (PastMeeting)cm.getMeeting(6);
		//System.out.println(mNote2.getNotes());
		
		Set<Contact> setCbyName = cm.getContacts("J");
		System.out.println("Nb of J: " + setCbyName.size());
		
		System.out.println("Nb of Jean: " + setCM1.size());
		System.out.println("id6: " + setCbyName.size());
		
		/* flush */
		cm.flush();
	}
}
