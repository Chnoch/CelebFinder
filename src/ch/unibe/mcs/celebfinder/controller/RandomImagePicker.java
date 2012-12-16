package ch.unibe.mcs.celebfinder.controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

import ch.unibe.mcs.celebfinder.model.CelebImage;
import ch.unibe.mcs.celebfinder.model.Person;

public class RandomImagePicker {

	public static Blob getRandomImage() {
		return getImageFromID(getRandomImageID());
	}

	public static long getRandomImageID() {
		return 1;
	}

	public static Blob getImageFromID(Object id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		CelebImage image;
		try {
			image = pm.getObjectById(CelebImage.class, id);
		} finally {
			pm.close();
		}
		return image.getImage();
	}

	public static List<Person> getNamesFromID(long id) {
		return null;
	}
}
