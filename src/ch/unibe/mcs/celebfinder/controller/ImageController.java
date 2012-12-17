package ch.unibe.mcs.celebfinder.controller;

import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

import ch.unibe.mcs.celebfinder.model.CelebImage;
import ch.unibe.mcs.celebfinder.model.Person;

public class ImageController {

	public static Blob getRandomImage() {
		return getImageFromID(getRandomImageID());
	}

	public static long getRandomImageID() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		CelebImage image = null;
		try {

		    Extent<CelebImage> e = pm.getExtent(CelebImage.class, true);
		    Iterator<CelebImage> iter=e.iterator();
		    while (iter.hasNext())
		    {
		        image = iter.next();
		        if (Math.random()<0.5)
		        	return image.getKey().getId();
		    }
		    if (image == null){
		    	return 0;
		    }
		    return image.getKey().getId();
		} finally {
			pm.close();
		}
		
	}

	public static Blob getImageFromID(long id) {
		CelebImage image = getCelebImageFromID(id);
		return image.getImage();
	}
	
	public static List<Person> getNamesFromID(long id) {
		CelebImage image = getCelebImageFromID(id);
		return image.getCandidates();
	}
	
	public static CelebImage getCelebImageFromID(long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		CelebImage image;
		try {
			image = pm.getObjectById(CelebImage.class, id);
		} finally {
			pm.close();
		}
		
		return image;
	}
}
