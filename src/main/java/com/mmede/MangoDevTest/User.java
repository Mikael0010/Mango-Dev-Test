package com.mmede.MangoDevTest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity 
public class User {

	private @Id @GeneratedValue long id;
	private String username;
	private String password; //Should be hashed or something but I'm not doing it for this

	private User() {}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	@Override   //This is probably useless but I'm keeping it in just in case.
	public String toString() {
		return "User{" +
			", username='" + username + '\'' +
			", password='" + password + '\'' +
			'}';
	}
}