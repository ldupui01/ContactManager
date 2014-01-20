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
	private Set<ContactImpl> setContact;
	private Set<Meeting> setMeeting;
	
	
	public ContactManagerImpl(){
		this.stringImport=null;
		this.setMeeting = new HashSet();
		this.setContact = new HashSet();
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
					ContactImpl contact = new ContactImpl(uid,name,note);
					this.setContact.add(contact);
//****************
					//System.out.println(contact.hashCode());
					//System.out.println(contact.getName());
					//System.out.println(setContact.size());
//*******************
					break;
				case("Meeting"):
					uid = Integer.parseInt(strImport[0]);
					date = strImport[1];
					time = this.setDate(date);
					with = strImport[2];
					Set<ContactImpl> setMeetingContact = this.setMeetingContact(with);
					MeetingImpl meeting = new MeetingImpl(uid, time, setMeetingContact);
					this.setMeeting.add(meeting);
					note = strImport[3];
					
					break;
				default:
					break;
				}
			}
		}
		/*******************
		 * Iterator<ContactImpl> it = setContact.iterator();
		while (it.hasNext()){
			ContactImpl obj = it.next();
			System.out.println(obj.getName());
		}*****************/
	}
	
	public <E> void iteratorTest(Set<E> e){
		Iterator<E> it = e.iterator();
		while (it.hasNext()){
			E obj = it.next();
			System.out.println(obj.toString());
		}
	}
	
	
	public ContactImpl retriveContact(int id, Set<ContactImpl> set){
		ContactImpl ci = null;
		for (ContactImpl obj : set){
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

	  /*cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month);
	    cal.set(Calendar.DATE, date);
	    System.out.println(cal.getTime());
	 */    
		return cal;
	}
	
	public Set<ContactImpl> setMeetingContact(String s){
		Set<ContactImpl> setMeetCont = new HashSet();
		int contactId=-1;
		int tagIndex = 0;
		int tagEndIndex = 0;
		boolean stop = false;
		do {
			tagEndIndex = s.indexOf(",",tagIndex);
			if (tagEndIndex>0){
				contactId = Integer.parseInt(s.substring(tagIndex, tagEndIndex));
				tagIndex = tagEndIndex+1;
				setMeetCont.add(this.retriveContact(contactId,setContact));
			}else{
				contactId = Integer.parseInt(s.substring(tagIndex, s.length()));
				setMeetCont.add(this.retriveContact(contactId,setContact));
				stop = true;
			}
		}while (!stop);
		//this.iteratorTest(setMeetCont);
		return setMeetCont;
	}

	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		// TODO Auto-generated method stub
		return 0;
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
	
	public Set<Contact> modifySet(Set<ContactImpl> set){
		Set<Contact> absSet = new HashSet();
		
		//absSet.add(set);
		// make absSet = set !?!?!?!?!?!
		return absSet;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

}
