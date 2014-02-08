package agenda;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting, Serializable {
	
	/**
	* A meeting that was held in the past.
	*
	* It includes your notes about what happened and what was agreed.
	*/
	
	private String mNote;
	
	private int index;

	
	public PastMeetingImpl(int mUID, Calendar mDate, Set<Contact> mContacts, String mNote){
		super(mUID, mDate, mContacts);
		this.mNote = mNote;
		this.index = getSetIndex();
	}

	/*@Override
	public int getId() {
		int id = super.getId();
		return id;
	}

	@Override
	public Calendar getDate() {
		Calendar date = super.getDate();
		return date;
	}

	@Override
	public Set<Contact> getContacts() {
		Set<Contact> setContacts = super.getContacts();
		return setContacts;
	}*/

	@Override
	public String getNotes() {
		return mNote;
	}
	
	public void setNotes(String note){
		this.mNote = note;
	}
	
	public int setgetIndex(){
		int index = super.getSetIndex();
		return index; 
	}

}
