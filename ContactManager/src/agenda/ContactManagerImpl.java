package agenda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ContactManagerImpl implements ContactManager {
	private Set<Contact> setContact;
	private Set<Meeting> setMeeting;
	private Set<PastMeeting> setPastMeeting;
	private Set<FutureMeeting> setFutureMeeting;
	
	public ContactManagerImpl(){
		this.setMeeting = new HashSet<Meeting>();
		this.setContact = new HashSet<Contact>();
		this.setPastMeeting  = new HashSet<PastMeeting>();
		this.setFutureMeeting  = new HashSet<FutureMeeting>();
	}

// *********************** TOOLS needed for method in the interface implementation ***************	
	
	public void Import(String fileName){
		// TODO generate method Import
	}
	
	public int findId (String s){
		int id = 0;
		boolean check = true;
		if (s == "contact"){
			do{
				check = true;
				id++;
				Iterator<Contact> it = setContact.iterator();
				while (it.hasNext()){
					Contact obj = it.next();
					if(obj.getId()==id){
						check = false;
					}
				}	
			}while(!check);
		}else if(s == "meeting"){
			do{
				check = true;
				id++;
				Iterator<Meeting> it = setMeeting.iterator();
				while (it.hasNext()){
					Meeting obj = it.next();
					if(obj.getId()==id){
						check = false;
					}
				}	
			}while(!check);
		}
		return id;
	}
	
	public void setPastFutureMeeting (){
		Calendar today =  Calendar.getInstance();
		PastMeeting pm = null;
		FutureMeeting fm = null;
		int id = 0;
		if (setMeeting.isEmpty()){
			System.out.println("No meeting has been recorded yet");
		}else{
			Iterator<Meeting> it = setMeeting.iterator();
			while (it.hasNext()){
				Meeting obj = it.next();
				id = obj.getId();
				if(obj.getDate().compareTo(today)<=0){
					pm = this.getPastMeeting(id);
					if (pm == null){
						pm = (PastMeeting)obj;
					}
				}else{
					fm = this.getFutureMeeting(id);
					if (fm == null){
						fm = (FutureMeeting)obj;
					}
				}
			}
		}
	}

//************** THe interface implementation start from here ******************
	
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
		this.setMeeting.add(meetingImpl);
		FutureMeeting futureMeetingImpl = new FutureMeetingImpl(id, date, contacts);
		this.setFutureMeeting.add(futureMeetingImpl);
		
		return id;
	}

	@Override
	public PastMeeting getPastMeeting(int id) throws IllegalArgumentException {
		PastMeeting pm = null;
		Iterator<FutureMeeting> itf = setFutureMeeting.iterator();
		while (itf.hasNext()){
			FutureMeeting obj = itf.next();
			if (obj.getId() ==id){
				throw new IllegalArgumentException("this id is related to a future meeting");
			}
		}
		if (setPastMeeting.isEmpty()) {
			return null;
		}else{
			Iterator<PastMeeting> it = setPastMeeting.iterator();
			while (it.hasNext()){
				PastMeeting obj = it.next();
				if (obj.getId() ==id){
					pm = obj;
				}
			}	
			return pm;
		}
	}
	
	@Override
	public FutureMeeting getFutureMeeting(int id) throws IllegalArgumentException {
		FutureMeeting fm = null;
		Iterator<PastMeeting> itp = setPastMeeting.iterator();
		while (itp.hasNext()){
			PastMeeting obj = itp.next();
			if (obj.getId() ==id){
				throw new IllegalArgumentException("this id is related to a past meeting");
			}
		}
		if (setFutureMeeting.isEmpty()) {
			return null;
		}else{
			Iterator<FutureMeeting> it = setFutureMeeting.iterator();
			while (it.hasNext()){
				FutureMeeting obj = it.next();
				if (obj.getId() ==id){
					fm = obj;
				}
			}	
			return fm;
		}
	}

	@Override
	public Meeting getMeeting(int id) {
		Meeting m = null;
		if (setMeeting.isEmpty()) {
			System.out.println("there is currently no meeting recorded");
			return null;
		}else{
			Iterator<Meeting> it = setMeeting.iterator();
			while (it.hasNext()){
				Meeting obj = it.next();
				if (obj.getId() ==id){
					m = obj;
				}
			}	
			return m;
		}
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) throws IllegalArgumentException {
		List<Meeting> lm = new ArrayList<Meeting>();
		if(!setContact.contains(contact)){
			throw new IllegalArgumentException("the contact is not currently registered");
		}
		if (setMeeting.isEmpty()) {
			System.out.println("there is currently no meeting recorded");
			return lm;
		}else{
			Iterator<Meeting> it = setMeeting.iterator();
			while (it.hasNext()){
				Meeting obj = it.next();
				if (obj.getContacts().contains(contact)) lm.add(obj);
			}
		}
		return lm;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		List<Meeting> lm = new ArrayList<Meeting>();
		if (setMeeting.isEmpty()) {
			System.out.println("there is currently no meeting recorded");
			return lm;
		}else{
			Iterator<Meeting> it = setMeeting.iterator();
			while (it.hasNext()){
				Meeting obj = it.next();
				if (obj.getDate().equals(date)) lm.add(obj);
			}
		}
		return lm;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) throws IllegalArgumentException{
		List<PastMeeting> lm = new ArrayList<PastMeeting>();
		if(!setContact.contains(contact)){
			throw new IllegalArgumentException("the contact is not currently registered");
		}
		if (setMeeting.isEmpty()) {
			System.out.println("there is currently no meeting recorded");
			return null;
		}else{
			Iterator<PastMeeting> it = setPastMeeting.iterator();
			while (it.hasNext()){
				PastMeeting obj = it.next();
				if (obj.getContacts().contains(contact)) lm.add(obj);
			}
		}
		return lm;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) throws IllegalArgumentException, NullPointerException {
		int id = this.findId("meeting");
		boolean check = true;
		if(contacts == null || date == null || text == null){
			check = false;
			throw new NullPointerException(" One of the argument Contact, Date or Notes was NULL");
		}
		
		// ************ Checking validity of contact ************
		if (contacts.isEmpty()) {
			check = false;
			throw new IllegalArgumentException("no contact was linked to the meeting");
		}else{
			if (!setContact.containsAll(contacts)){
				check = false;
				throw new IllegalArgumentException("not all contact linked to the meeting were register");
			}
		}
		
		PastMeetingImpl pmi = new PastMeetingImpl(id, date, contacts, text);
		if (setPastMeeting.isEmpty()){
			setMeeting.add(pmi);
		}else{
			Iterator<Meeting> itm = setMeeting.iterator();
			while (itm.hasNext()){
				Meeting obj = itm.next();
				if (obj.getDate().equals(date)){
					int objID = obj.getId();
					this.addMeetingNotes(objID, text);
					System.out.println("this meeting was already saved, notes has been updated ");
					check = false;  
				}
			}
		}
		if (check) setMeeting.add(pmi);
	}

	@Override
	public void addMeetingNotes(int id, String text) throws NullPointerException, IllegalStateException, IllegalArgumentException{
		boolean check = false;
		if (text == null){
			throw new NullPointerException("No notes were given");
		}
		Calendar today =  Calendar.getInstance();
		
		Iterator<PastMeeting> itp = setPastMeeting.iterator();
		while (itp.hasNext()){
			PastMeeting obj = itp.next();
			if (obj.getId() == id){
				if (obj.getDate().after(today)) {
					throw new IllegalArgumentException("this meeting is set in the future. No notes allowed yet");
				}else {
					obj.setNotes(text);  // *************** did modify the interface to be able to update the notes .... Need to find another solution *********************
					check = true;
				}
			}
		}
		if (!check) throw new IllegalArgumentException("No meeting is matching this ID");
	}

	@Override
	public void addNewContact(String name, String notes) throws NullPointerException{
		boolean check = false;
		if(name == null || notes == null){
			check = false;
			throw new NullPointerException("One of the argument Name or Notes was NULL");
		}
		int cid = this.findId("contact");
		Contact c = new ContactImpl(cid, name, notes);
		setContact.add(c);
	}

	@Override
	public Set<Contact> getContacts(int... ids) throws IllegalArgumentException{
		Set<Contact> setCtcIds = new HashSet<Contact>();
		if (ids.length>0){
			for (int i=0; i<ids.length ; i++){
				boolean check = false;
				int id = ids[i];
				
				Iterator<Contact> it = setContact.iterator();
				while (it.hasNext()){
					Contact obj = it.next();
					if (obj.getId() == id){
						check = true;
						setCtcIds.add(obj)	;
					}
				}
				if (!check) throw new IllegalArgumentException("the id " + id + " did not match any registered contact");
			}
		}
		return setCtcIds;
	}

	@Override
	public Set<Contact> getContacts(String name) throws NullPointerException {
		Set<Contact> setCtcName = new HashSet<Contact>();
		if (name == null){
			throw new NullPointerException("The given String Name was NULL");
		}else{
			Iterator<Contact> it = setContact.iterator();
			while (it.hasNext()){
				Contact obj = it.next();
				if (obj.getName().matches("(?i).*"+ name +".*")){  // match solution case insensitive partially by Coveros Gene at http://stackoverflow.com/questions/687577/how-do-i-see-if-a-substring-exists-inside-another-string-in-java-1-4
					setCtcName.add(obj)	;
				}
			}
		}	
		return setCtcName;  // Warning the set can be empty !
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

}
