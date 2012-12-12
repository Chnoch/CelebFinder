package ch.unibe.mcs.celebfinder.shared.model;

import java.net.URI;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Person {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String mFirstName;
	@Persistent
	private String mLastName;
	@Persistent
	private URI mLink;

	public Person(String firstName, String lastName) {
		mFirstName = firstName;
		mLastName = lastName;
	}

	public Person(String firstName, String lastName, URI link) {
		mFirstName = firstName;
		mLastName = lastName;
		mLink = link;
	}
	
	public Key getKey() {
		return key;
	}

	public String getFirstName() {
		return mFirstName;
	}

	public void setFirstName(String mFirstName) {
		this.mFirstName = mFirstName;
	}

	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String mLastName) {
		this.mLastName = mLastName;
	}

	public URI getLink() {
		return mLink;
	}

	public void setLink(URI mLink) {
		this.mLink = mLink;
	}
}
