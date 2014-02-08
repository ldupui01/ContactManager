package agenda;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MeetingImpl implements Meeting, Serializable {
	
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
	
	private int index;


	public MeetingImpl(int mUID, Calendar mDate, Set<Contact> mContacts){
		this.mUID = mUID;
		this.mDate = mDate;
		this.mContacts = mContacts;
		this.index = this.getSetIndex();
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
		//Set<Contact> set = new HashSet<Contact>();
		//return set;
		return this.mContacts;
	}
	
	public int getSetIndex(){
		this.index = this.mDate.get(Calendar.YEAR)*1000 + (mDate.get(Calendar.MONTH)+1)*100 + mDate.get(Calendar.DAY_OF_MONTH);
		return this.index; 
	}

}
