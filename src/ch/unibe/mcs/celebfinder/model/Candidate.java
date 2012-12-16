package ch.unibe.mcs.celebfinder.model;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Candidate extends Model {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private CelebImage image;

	@Persistent
	private Person person;

	@Persistent
	private int suggestions;

	public Candidate(CelebImage image, Person person) {
		this.image = image;
		this.person = person;
		suggestions = 1;
	}

	public CelebImage getImage() {
		return image;
	}

	public void setImage(CelebImage image) {
		this.image = image;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(int suggestions) {
		this.suggestions = suggestions;
	}

	public Key getKey() {
		return key;
	}

}
