package test;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import agenda.ContactManagerImpl;

public class Essai {

	public static void main(String[] args) {
		Essai ex = new Essai();
		ex.launch();
	}

	private void launch() {
		ContactManagerImpl cmi = new ContactManagerImpl();
		cmi.readTextFile();
		
	}

}
