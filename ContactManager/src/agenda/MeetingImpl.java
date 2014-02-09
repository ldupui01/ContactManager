package agenda;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

public class MeetingImpl implements Meeting, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1944747545461982279L;

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
	public int hashCode() {
		final int prime = 97;
		int result = 3;
		result = prime * result + mUID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingImpl other = (MeetingImpl) obj;
		if (mUID != other.mUID)
			return false;
		return true;
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
