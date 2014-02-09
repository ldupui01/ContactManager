package agenda;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1933217545452892279L;
	/**
	* A meeting that was held in the past.
	*
	* It includes your notes about what happened and what was agreed.
	*/
	
	private String mNote;

	
	public PastMeetingImpl(int mUID, Calendar mDate, Set<Contact> mContacts){
		super(mUID, mDate, mContacts);
		this.mNote = "";
	}
	
	public PastMeetingImpl(int mUID, Calendar mDate, Set<Contact> mContacts, String mNote){
		super(mUID, mDate, mContacts);
		this.mNote = mNote;
	}
	
	public PastMeetingImpl(Meeting m, String mNote){
		super(m.getId(), m.getDate(), m.getContacts());
		this.mNote = mNote;
	}
	
	@Override
	public String getNotes() {
		return mNote;
	}
}
