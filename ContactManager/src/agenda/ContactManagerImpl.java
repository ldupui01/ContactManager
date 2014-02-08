package agenda;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ContactManagerImpl implements ContactManager {
	private Set<Contact> setContact;
	private Set<Meeting> setMeeting;
	//private Set<PastMeeting> setPastMeeting;
	//private Set<FutureMeeting> setFutureMeeting;
	private static final String EXPORTFILE = "contacts.txt";
	private Calendar today = null;
	
	public ContactManagerImpl(){
		today = Calendar.getInstance(); // to move to its own method to allow refresh
		if(new File(EXPORTFILE).exists()){
			try
		      {
		         FileInputStream fileIn = new FileInputStream(EXPORTFILE);
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         setContact = (Set<Contact>) in.readObject();
		         setMeeting = (Set<Meeting>) in.readObject();
		         //setPastMeeting = (Set<PastMeeting>) in.readObject();
		         //setFutureMeeting = (Set<FutureMeeting>) in.readObject();
		         in.close();
		         fileIn.close();
		      }catch(IOException i)
		      {
		         i.printStackTrace();
		         return;
		      }catch(ClassNotFoundException e)
		      {
		         System.out.println("Contact or Meeting class not found");
		         e.printStackTrace();
		      }
		}else{
			this.setMeeting = new HashSet<Meeting>();
			this.setContact = new HashSet<Contact>();
			//this.setPastMeeting  = new HashSet<PastMeeting>();
			//this.setFutureMeeting  = new HashSet<FutureMeeting>();
		}
	}

// *********************** TOOLS needed for method in the interface implementation ***************	
	
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
		int id = findId("meeting");
		if (date.compareTo(today)<0){
			throw new IllegalArgumentException("You can not set a future meeting in the past");
		}
		if ( (contacts==null) || (!this.setContact.containsAll(contacts)) || (contacts.size()<1)) {
            throw new IllegalArgumentException("None or unregistered contact(s) were set for this meeting");
		}
		Meeting newMeeting = new FutureMeetingImpl(id, date, contacts);
		setMeeting.add(newMeeting);
		
		return id;
	}

	@Override
	public PastMeeting getPastMeeting(int id) throws IllegalArgumentException {
		PastMeeting pm = null;
		if (setMeeting.isEmpty()) {
			return null;
		}else{
			Iterator<Meeting> it = setMeeting.iterator();
			while (it.hasNext()){
				Meeting obj = it.next();
				if (id == obj.getId() ){
					if(obj.getDate().after(today)){ // need to take today meeting as future meeting
						throw new IllegalArgumentException("the id " + id + " is related to a future meeting");
					}else{
						pm = (PastMeeting) obj;
					}
				}
			}	
			return pm;
		}
	}
	
	@Override
	public FutureMeeting getFutureMeeting(int id) throws IllegalArgumentException {
		FutureMeeting fm = null;
		
		if (setMeeting.isEmpty()) {
			return null;
		}else{
			Iterator<Meeting> it = setMeeting.iterator();
			
			while (it.hasNext()){
				Meeting obj = it.next();
				
				if (obj.getId() == id){
					if(obj.getDate().before(today)){
						throw new IllegalArgumentException("the id " + id + " is related to a past meeting");
					}else{
						fm = (FutureMeeting) obj;
					}
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
				if (id == obj.getId()){
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
	public List<Meeting> getFutureMeetingList(Calendar date) throws IllegalArgumentException {
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
			Iterator<Meeting> it = setMeeting.iterator();
			while (it.hasNext()){
				Meeting obj = it.next();
				if (obj.getContacts().contains(contact)) lm.add((PastMeeting) obj);
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
		if (check){
			Meeting pmi = new PastMeetingImpl(id, date, contacts, text);
			if (!setMeeting.isEmpty()){
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
	}

	@Override
	public void addMeetingNotes(int id, String text) throws NullPointerException, IllegalStateException, IllegalArgumentException{
		boolean check = false;
		if (text == null){
			throw new NullPointerException("No notes were given");
		}
		Calendar today =  Calendar.getInstance();
		
		Iterator<Meeting> itp = setMeeting.iterator();
		while (itp.hasNext()){
			Meeting obj = itp.next();
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
			throw new NullPointerException("The given String Name " + name + " was NULL");
		}else{
			Iterator<Contact> it = setContact.iterator();
			while (it.hasNext()){
				Contact obj = it.next();
				if (obj.getName().matches("(?i).*"+ name +".*")){  // match solution case insensitive partially by Coveros Gene at http://stackoverflow.com/questions/687577/how-do-i-see-if-a-substring-exists-inside-another-string-in-java-1-4
					setCtcName.add(obj);
				}
			}
		}	
		return setCtcName;  // Warning the set can be empty !
	}

	@Override
	public void flush() {
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut = null;
		try {
			fileOut = new FileOutputStream(EXPORTFILE);
			objOut = new ObjectOutputStream(new BufferedOutputStream(fileOut));
			objOut.writeObject(setContact);
			objOut.writeObject(setMeeting);
			//objOut.writeObject(setPastMeeting);
			//objOut.writeObject(setFutureMeeting);
			
			objOut.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			System.out.println(fileOut + " was not found");
			e.printStackTrace();
		
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/********************** TEST *************************/
	
	public int getSize(){
		 int i = setMeeting.size();
		 return i;
	}

}
