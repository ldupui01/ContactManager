/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import agenda.ContactManagerImpl;

/**
 * @author Ludo
 *
 */
public class CmiTest {

	/**
	 * Test method for {@link agenda.ContactManagerImpl#readTextFile()}.
	 */
	private String text;
	
	@Before
	public void settingVariable(){
		text = "alal";
	}
	
	@Test
	public void testReadTextFile() {
		ContactManagerImpl cmi = new ContactManagerImpl();
		//String result = cmi.readTextFile();
		
		//assertNotNull("Textfile not read!", result);
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#addFutureMeeting(java.util.Set, java.util.Calendar)}.
	 */
	@Test
	public void testAddFutureMeeting() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#getPastMeeting(int)}.
	 */
	@Test
	public void testGetPastMeeting() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#getFutureMeeting(int)}.
	 */
	@Test
	public void testGetFutureMeeting() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#getMeeting(int)}.
	 */
	@Test
	public void testGetMeeting() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#getFutureMeetingList(agenda.Contact)}.
	 */
	@Test
	public void testGetFutureMeetingListContact() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#getFutureMeetingList(java.util.Calendar)}.
	 */
	@Test
	public void testGetFutureMeetingListCalendar() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#getPastMeetingList(agenda.Contact)}.
	 */
	@Test
	public void testGetPastMeetingList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#addNewPastMeeting(java.util.Set, java.util.Calendar, java.lang.String)}.
	 */
	@Test
	public void testAddNewPastMeeting() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#addMeetingNotes(int, java.lang.String)}.
	 */
	@Test
	public void testAddMeetingNotes() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#addNewContact(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddNewContact() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#getContacts(int[])}.
	 */
	@Test
	public void testGetContactsIntArray() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#getContacts(java.lang.String)}.
	 */
	@Test
	public void testGetContactsString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link agenda.ContactManagerImpl#flush()}.
	 */
	@Test
	public void testFlush() {
		fail("Not yet implemented");
	}

}
