package ch.unibe.mcs.celebfinder.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class User extends Model {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)	
	private Key key;
	
	@Persistent
	private String username;
	
	@Persistent
	private int score;
	
	public User(String username) {
		this.username = username;
		score = 0;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Key getKey() {
		return key;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int addScore(int score) {
		this.score += score;
		return this.score;
	}
	
	
}
