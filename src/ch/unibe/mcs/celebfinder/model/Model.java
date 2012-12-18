package ch.unibe.mcs.celebfinder.model;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;


import ch.unibe.mcs.celebfinder.controller.PMF;

public abstract class Model {

	public boolean save() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
//		Transaction tx = pm.currentTransaction();
//		tx.begin();

		boolean success = true;
		try {
			pm.makePersistent(this);
//			tx.commit();
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		} finally {
			pm.close();
//			if (tx.isActive()) {
//	            tx.rollback();
//	        }
		}
		return success;
	}
}
