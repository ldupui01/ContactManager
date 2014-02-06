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
	public List<Meeting> getFutureMeetingList(Contact contact) {
		List<Meeting> lm = new ArrayList<Meeting>();
		if (setMeeting.isEmpty()) {
			System.out.println("there is currently no meeting recorded");
			return null;
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
			return null;
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
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		List<PastMeeting> lm = new ArrayList<PastMeeting>();
		if (setMeeting.isEmpty()) {
			System.out.println("there is currently no meeting recorded");
			return null;
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
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date,String text) {
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
