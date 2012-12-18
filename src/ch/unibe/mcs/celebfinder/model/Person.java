package ch.unibe.mcs.celebfinder.model;

import java.net.URI;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Person extends Model {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String firstName;

	@Persistent
	private String lastName;
	
	@Persistent
	private int suggestions;
	
	@Persistent
	private CelebImage image;
	
	@Persistent
	private URI link;
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		suggestions = 0;
	}

	public Person(CelebImage image, String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.image = image;
		suggestions = 0;
	}

	public Person(String firstName, String lastName, URI link) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.link = link;
	}

	public Key getKey() {
		return key;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String mFirstName) {
		this.firstName = mFirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String mLastName) {
		this.lastName = mLastName;
	}

	public URI getLink() {
		return link;
	}

	public void setLink(URI mLink) {
		this.link = mLink;
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

	public CelebImage getImage() {
		return image;
	}

	public void setImage(CelebImage image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Person) {
			Person other = (Person) o;
			if (this.firstName.equals(other.getFirstName())
					&& this.lastName.equals(other.getLastName())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}
}
