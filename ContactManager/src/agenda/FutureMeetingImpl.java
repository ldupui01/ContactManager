package agenda;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import java.util.HashSet;

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting, Serializable {

	public FutureMeetingImpl(int mUID, Calendar mDate, Set<Contact> mContacts){
		super(mUID, mDate, mContacts);
	}
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Calendar getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Contact> getContacts() {
		// TODO Auto-generated method stub
		return null;
	}

}
