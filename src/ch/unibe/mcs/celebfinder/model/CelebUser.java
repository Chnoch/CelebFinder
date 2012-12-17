package ch.unibe.mcs.celebfinder.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class CelebUser extends Model {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)	
	private Key key;
	
	@Persistent
	private String username;
	
	@Persistent
	private String nickname;
	
	@Persistent
	private int score;
	
	public CelebUser(String username, String nickname) {
		this.username = username;
		this.nickname = nickname;
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
	
	public void addScore(int score) {
		this.score += score;
		this.save();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
