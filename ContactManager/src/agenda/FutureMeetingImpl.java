package agenda;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting, Serializable {

	private static final long serialVersionUID = 1827847545467820279L;
	
	public FutureMeetingImpl(int mUID, Calendar mDate, Set<Contact> mContacts){
		super(mUID, mDate, mContacts);
	}

}
