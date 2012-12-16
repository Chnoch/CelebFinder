package ch.unibe.mcs.celebfinder.model;

import javax.jdo.PersistenceManager;

import ch.unibe.mcs.celebfinder.controller.PMF;

public abstract class Model {

	
	public boolean save() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		boolean success = true;
		try {
			pm.makePersistent(this);
		} catch(Exception e) {
			success = false;
		} finally {
			pm.close();
		}
		return success;
	}
}
