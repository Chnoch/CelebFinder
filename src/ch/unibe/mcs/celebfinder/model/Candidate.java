package ch.unibe.mcs.celebfinder.model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
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

	public void addSuggestion() {
		this.suggestions++;
	}

	public Key getKey() {
		return key;
	}

	public CelebImage getImage() {
		return image;
	}

	public void setImage(CelebImage image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Candidate) {
			Candidate other = (Candidate) o;
			return this.person.getKey().equals(other.person.getKey()) && this.getSuggestions()==other.getSuggestions();
		} else {
			return false;
		}
	}

}
