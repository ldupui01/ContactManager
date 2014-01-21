package agenda;

import java.util.Calendar;
import java.util.Set;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
	
	/**
	* A meeting that was held in the past.
	*
	* It includes your notes about what happened and what was agreed.
	*/
	
	private String mNote;
	
	public PastMeetingImpl(int mUID, Calendar mDate, Set<Contact> mContacts, String mNote){
		super(mUID, mDate, mContacts);
		this.mNote = mNote;
	}

	@Override
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
	}

	@Override
	public String getNotes() {
		return mNote;
	}
	
	public void setNote(String note){
		this.mNote = note;
	}

}
