package ch.unibe.mcs.celebfinder.controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.users.User;

import ch.unibe.mcs.celebfinder.model.CelebUser;

public class UserController {

	public static CelebUser getCelebUserFromAuth(User user) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query q = pm.newQuery(CelebUser.class,
					"username=='" + user.getEmail() + "'");
			List<CelebUser> users = (List<CelebUser>) q.execute();
			if (users.size() == 0) {
				return null;
			} else {
				return users.get(0);
			}
		} catch (Exception e) {
			// No user available
			return null;
		} finally {
			pm.close();
		}
	}
	
	public static List<CelebUser> getHighscore() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query q = pm.newQuery(CelebUser.class);
			q.setOrdering("score desc");
			q.setRange(0,9);
			List<CelebUser> users = (List<CelebUser>) q.execute();
			return users;
		} catch (Exception e) {
			// No highscore available
			return null;
		} finally {
			pm.close();
		}
	}

}
