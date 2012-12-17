package ch.unibe.mcs.celebfinder.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.FetchGroup;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

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

			int index = (int) (Math.random() * (double) images.size());
			
			return images.get(index).getKey().getId();
		} finally {
			pm.close();
		}

	}

	public static Blob getImageFromID(long id) {
		CelebImage image = getCelebImageFromID(id);
		return image.getImage();
	}

	public static List<Candidate> getNamesFromID(long id) {
		CelebImage image = getCelebImageFromID(id);
		return image.getCandidates();
	}

	public static List<Person> getCandidatePersonsFromID(long id) {
		CelebImage image = getCelebImageFromID(id);
		List<Person> persons = new ArrayList<Person>();
		for (Candidate candidate : image.getCandidates()) {
			persons.add(candidate.getPerson());
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
