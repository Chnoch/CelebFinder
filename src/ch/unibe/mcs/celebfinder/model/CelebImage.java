package ch.unibe.mcs.celebfinder.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.unibe.mcs.celebfinder.controller.UserController;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class CelebImage extends Model {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private Blob image;

	@Persistent(mappedBy="image")
	private Set<Person> candidates;

	@Persistent
	private Person confirmedPerson;

	public CelebImage(Blob image) {
		this.image = image;
		this.candidates = new HashSet<Person>();
	}

	/**
	 * Adds a candidate for the image
	 * 
	 * @param candidate
	 */
	public boolean addCandidate(Person person) {
		if (person == null) {
			return false;
		}
		int highestScore = 0;
		boolean found = false;
		boolean createNew = true;
		Person selectedCandidate = null;
		if (this.candidates == null) {
			this.candidates = new HashSet<Person>();
			createNew = false;
		}

		for (Person candidate : this.candidates) {
			if (candidate.getSuggestions() > highestScore) {
				highestScore = candidate.getSuggestions();
			}
			if (candidate != null) {
				if (candidate.equals(person)) {
					candidate.addSuggestion();
//					candidate.save();
					found = true;
					selectedCandidate = candidate;
				}
			}
		}

		if (found) {
			if (selectedCandidate.getSuggestions() < highestScore) {
				return false;
			} else {
				return true;
			}
		}

		if (createNew) {
			person.setImage(this);
			candidates.add(person);
//			candidate.save();
			return true;
		}
		return false;
	}

	/**
	 * Removes a candidate for the image
	 * 
	 * @param candidate
	 * @return true if candidate removed from the list, false if the candidate
	 *         is not in the list
	 */
	// public boolean removeCandidate(Person candidate) {
	// if (candidates.contains(candidate)) {
	// candidates.remove(candidate);
	// return true;
	// } else {
	// return false;
	// }
	// }

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

	public Set<Person> getCandidates() {
		return candidates;
	}

}
