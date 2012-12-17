package ch.unibe.mcs.celebfinder.model;

import java.util.ArrayList;
import java.util.List;

import ch.unibe.mcs.celebfinder.controller.UserController;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class CelebImage extends Model {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private Blob image;

	@Persistent(mappedBy = "image")
	private List<Candidate> candidates;

	@Persistent
	private Person confirmedPerson;

	public CelebImage(Blob image) {
		this.image = image;
		this.candidates = new ArrayList<Candidate>();
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
		Candidate selectedCandidate = null;
		if (this.candidates == null) {
			this.candidates = new ArrayList<Candidate>();
			createNew = false;
		}

		for (Candidate candidate : this.candidates) {
			if (candidate.getSuggestions() > highestScore) {
				highestScore = candidate.getSuggestions();
			}
			if (candidate.getPerson() != null) {
				if (candidate.getPerson().equals(person)) {
					candidate.addSuggestion();
					person.save();
					candidate.save();
					this.save();
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
			Candidate candidate = new Candidate(this, person);

			candidates.add(candidate);
			person.save();
			candidate.save();
			this.save();
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

	public List<Candidate> getCandidates() {
		return candidates;
	}

}
