package agenda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class ContactManagerImpl implements ContactManager {
	private ArrayList<String[]> stringImport;
	private Set<Contact> setContact;
	private Set<ContactImpl> setContactImpl;
	private Set<Meeting> setMeeting;
	private Set<MeetingImpl> setMeetingImpl;
	private Set<PastMeetingImpl> setPastMeetingImpl;
	private Set<FutureMeetingImpl> setFutureMeetingImpl;
	
	public ContactManagerImpl(){
		this.stringImport=null;
		this.setMeeting = new HashSet<Meeting>();
		this.setMeetingImpl = new HashSet();
		this.setContact = new HashSet<Contact>();
		this.setContactImpl = new HashSet();
		this.setPastMeetingImpl  = new HashSet();
		this.setFutureMeetingImpl  = new HashSet();
	}
	
	public void  readTextFile(){
		TextReader tr = new TextReader();
		this.stringImport= tr.reader("..\\contacts.txt");
		this.createSets(this.stringImport);
	}
	
	public void createSets(ArrayList<String[]> al){
		String[] strImport = null;
		int uid = 0;
		String name = null;
		String note = null;
		String date = null;
		Calendar time = null;
		String with = null;
		for(int i = 0;i<al.size();i++){
			strImport = al.get(i);
			if (strImport[0] != null){
				switch (strImport[4]){
				case("Contact"):
					uid = Integer.parseInt(strImport[0]);
					name = strImport[1];
					note = strImport[2];
					Contact contact = new ContactImpl(uid,name,note);
					this.setContact.add(contact);
					ContactImpl contactImpl = new ContactImpl(uid,name,note);
					this.setContactImpl.add(contactImpl);
					break;
				case("Meeting"):
					uid = Integer.parseInt(strImport[0]);
					date = strImport[1];
					time = this.setDate(date);
					with = strImport[2];
					Set<Contact> setMeetingContact = this.setMeetingContact(with);
					Meeting meeting = new MeetingImpl(uid, time, setMeetingContact);
					this.setMeeting.add(meeting);
					MeetingImpl meetingImpl = new MeetingImpl(uid, time, setMeetingContact);
					this.setMeetingImpl.add(meetingImpl);
					note = strImport[3];
/*
 * set future meeting and past meeting	HERE ... use date compare !!!				
 */
					break;
				default:
					break;
				}
			}
		}

	}
	
	public ContactImpl retriveContactImpl(int id, Set<ContactImpl> set){
		ContactImpl ci = new ContactImpl(0,"","");
		for (ContactImpl obj : set){
			if (obj.equals(id))
				ci = obj;
		}
		return ci;
	}
	
	public Contact retriveContact(int id, Set<Contact> set){
		Contact ci =  new ContactImpl(0,"","");
		for (Contact obj : set){
			if (obj.equals(id))
				 ci = obj;
		}
		return ci;
	}
	
	public Calendar setDate(String s){
	    int year = Integer.parseInt(s.substring(6, 10));
	    int month = (Integer.parseInt(s.substring(3, 4))) - 1; // -1 because the month start at 0 for January
	    int date = Integer.parseInt(s.substring(0, 1));
	    Calendar cal = Calendar.getInstance();
	    cal.clear();
	    cal.set(year, month, date);
		return cal;
	}
	
	public Calendar today(){
		Calendar today =  Calendar.getInstance();
		return today;
	}
	
	public Set<Contact> setMeetingContact(String s){
		Set<Contact> setMeetCont = new HashSet<Contact>();
		setMeetCont.clear();
		int contactId=-1;
		int tagIndex = 0;
		int tagEndIndex = 0;
		boolean stop = false;
		do {
			tagEndIndex = s.indexOf(",",tagIndex);
			if (tagEndIndex>0){
				contactId = Integer.parseInt(s.substring(tagIndex, tagEndIndex));
				tagIndex = tagEndIndex+1;
				setMeetCont.add(this.retriveContact(contactId,this.setContact));
			}else{
				contactId = Integer.parseInt(s.substring(tagIndex, s.length()));
				setMeetCont.add(this.retriveContact(contactId,this.setContact));
				stop = true;
			}
		}while (!stop);
		return setMeetCont;
	}
	
	public int findId (String s){
		int id = 0;
		boolean check = true;
		if (s == "contact"){
			do{
				check = true;
				id++;
				Iterator<ContactImpl> it = setContactImpl.iterator();
				while (it.hasNext()){
					ContactImpl obj = it.next();
					if(obj.getId()==id){
						check = false;
					}
				}	
			}while(!check);
		}else if(s == "meeting"){
			do{
				check = true;
				id++;
				Iterator<MeetingImpl> it = setMeetingImpl.iterator();
				while (it.hasNext()){
					MeetingImpl obj = it.next();
					if(obj.getId()==id){
						check = false;
					}
				}	
			}while(!check);
		}
		return id;
	}

	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException{
		// TODO Auto-generated method stub
		int id = findId("meeting");
		Calendar today =  Calendar.getInstance();
		if (date.compareTo(today)<0){
			throw new IllegalArgumentException("You can not set a futur meeting in the past");
		}
		if ( (contacts==null) || (!this.setContact.containsAll(contacts)) ) {
            throw new IllegalArgumentException("you did set unvalid contact or none for this meeting");
    }
		Meeting meeting = new MeetingImpl(id, date, contacts);
		this.setMeeting.add(meeting);
		MeetingImpl meetingImpl = new MeetingImpl(id, date, contacts);
		this.setMeetingImpl.add(meetingImpl);
		FutureMeetingImpl futuremeetingImpl = new FutureMeetingImpl(id, date, contacts);
		this.setFutureMeetingImpl.add(futuremeetingImpl);
		
		return id;
	}

	@Override
	public PastMeeting getPastMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meeting getMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date,
			String text) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addMeetingNotes(int id, String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNewContact(String name, String notes) {
		// TODO Auto-generated method stub
	}

	@Override
	public Set<Contact> getContacts(int... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Contact> getContacts(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

}
