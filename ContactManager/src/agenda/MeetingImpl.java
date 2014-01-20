package agenda;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class MeetingImpl implements Meeting {
	
	/**
	 * The unique ID of the meeting
	 */
	private int mUID;
	
	/**
	 * The schedule date of the meeting
	 */
	private Calendar mDate;
	
	/**
	 * The list of contact present to the meeting
	 */
	private Set<Contact> mContacts;

	public MeetingImpl(int mUID, Calendar mDate, Set<Contact> mContacts){
		this.mUID = mUID;
		this.mDate = mDate;
		this.mContacts = mContacts;
	}
	
	@Override
	public int getId() {
		return this.mUID;
	}

	@Override
	public Calendar getDate() {
		return this.mDate;
	}

	@Override
	public Set<Contact> getContacts() {
		return this.mContacts;
	}

}
