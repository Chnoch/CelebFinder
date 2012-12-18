package ch.unibe.mcs.celebfinder.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.Extent;
import javax.jdo.FetchGroup;
import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Blob;

import ch.unibe.mcs.celebfinder.model.Candidate;
import ch.unibe.mcs.celebfinder.model.CelebImage;
import ch.unibe.mcs.celebfinder.model.Person;

public class ImageController {

	public static Blob getRandomImage() {
		return getImageFromID(getRandomImageID());
	}

	public static long getRandomImageID() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.getFetchPlan().setGroup(FetchGroup.ALL);
		try {

			Extent<CelebImage> e = pm.getExtent(CelebImage.class, true);
			List<CelebImage> images = new ArrayList<CelebImage>();
			Iterator<CelebImage> iter = e.iterator();
			while (iter.hasNext()) {
				images.add(iter.next());
			}

			CelebImage image;
			boolean done = true;
			do {
				done = true;
				int index = (int) (Math.random() * (double) images.size());
				image = images.get(index);
				if (image.getCandidates() == null) {
					done = false;
				} else if (image.getCandidates().size() == 0) {
					done = false;
				}
			} while (!done);

			return image.getKey().getId();
		} finally {
			pm.close();
		}

	}

	public static Blob getImageFromID(long id) {
		CelebImage image = getCelebImageFromID(id);
		return image.getImage();
	}

	public static Set<Person> getNamesFromID(long id) {
		CelebImage image = getCelebImageFromID(id);
		return image.getCandidates();
	}

	public static List<Person> getCandidatePersonsFromID(long id) {
		CelebImage image = getCelebImageFromID(id);
		List<Person> persons = new ArrayList<Person>();
		if (image.getCandidates() != null) {
			for (Person candidate : image.getCandidates()) {
				persons.add(candidate);
			}
		}
		return persons;
	}

	public static CelebImage getCelebImageFromID(long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.getFetchPlan().setGroup(FetchGroup.ALL);
		CelebImage image;
		try {
			image = pm.getObjectById(CelebImage.class, id);
		} finally {
			pm.close();
		}

		return image;
	}
}
