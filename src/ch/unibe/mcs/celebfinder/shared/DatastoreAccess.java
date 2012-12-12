package ch.unibe.mcs.celebfinder.shared;

import java.util.List;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

import ch.unibe.mcs.celebfinder.shared.model.Person;

public class DatastoreAccess {

	public static long getRandomImageID() {
		return 0;
	}

	public static Blob getImageFromID(long id) {
//		DatastoreService datastore = DatastoreServiceFactory
//				.getDatastoreService();
//		Key imageKey = KeyFactory.createKey("Images", id);
//		Query query = new Query("Images", imageKey);
//		List<Entity> images = datastore.prepare(query).asList(
//				FetchOptions.Builder.withLimit(1));
//		for (Entity e : images) {
//			return (Blob) e.getProperty("image");
//		}
//		return null;
		return null;
	}

	public static List<Person> getNamesFromID(long id) {
//		DatastoreService datastore = DatastoreServiceFactory
//				.getDatastoreService();
//		Key imageKey = KeyFactory.createKey("Images", id);
//		// Run an ancestor query to ensure we see the most up-to-date
//		// view of the Greetings belonging to the selected Guestbook.
//		Query query = new Query("Images", imageKey);
//		List<Entity> images = datastore.prepare(query).asList(
//				FetchOptions.Builder.withLimit(1));
//		for (Entity e : images) {
//			return (List<Person>) e.getProperty("names");
//		}
//		return null;
		return null;
	}
}
