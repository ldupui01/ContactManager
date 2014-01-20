package agenda;

public class ContactImpl implements Contact {
	
	/**
	* Contact's ID (unique)
	*/
	private int cUID;
	
	/**
	* Contact's name (probably unique, but maybe not)
	*/
	private String cName;
	
	/**
	* Contact's notes that the user may want to save about them.
	*/
	private String cNote;
	
	public int hashId;
	
	public ContactImpl(int cUID, String cName, String cNote){
		this.cUID = cUID;
		this.cName = cName;
		this.cNote = cNote;
		this.hashId = this.hashCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cUID;
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
		ContactImpl other = (ContactImpl) obj;
		if (cUID != other.cUID)
			return false;
		return true;
	}
	
	public boolean equals(int id){
		if(this.cUID == id)
			return true;
		return false;
	}


	@Override
	public int getId() {
		return cUID;
	}
	
	@Override
	public String getName() {
		return cName;
	}

	@Override
	public String getNotes() {
		return cNote;
	}

	@Override
	public void addNotes(String note) {
		this.cNote= cNote + " " + note;

	}

}
