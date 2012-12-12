package ch.unibe.mcs.celebfinder.shared.model;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class CelebImage {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Blob image;
	
	@Persistent
	private List<Person> candidates;
	
	@Persistent
	private Person confirmedPerson;
	
	public CelebImage(Blob image) {
		this.image = image;
		this.candidates = new ArrayList<Person>();
	}
	
	/**
	 * Adds a candidate for the image
	 * @param candidate
	 * @return true if candidate added to the list, false if the candidate is already in the list
	 */
	public boolean addCandidate(Person candidate) {
		if (!candidates.contains(candidate)) {
			candidates.add(candidate);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Removes a candidate for the image
	 * @param candidate
	 * @return true if candidate removed from the list, false if the candidate is not in the list
	 */
	public boolean removeCandidate(Person candidate) {
		if (candidates.contains(candidate)) {
			candidates.remove(candidate);
			return true;
		} else {
			return false;
		}
	}
	
	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Person getConfirmedPerson() {
		return confirmedPerson;
	}

	public void setConfirmedPerson(Person confirmedPerson) {
		this.confirmedPerson = confirmedPerson;
	}

	public Key getKey() {
		return key;
	}
	

}
