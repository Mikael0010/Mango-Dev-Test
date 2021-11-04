package com.mmede.MangoDevTest;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity 
public class User {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	private @Id String username;
	private @JsonIgnore String password; //Should be hashed or something but I'm not doing it for this
	private String roles[]={"ROLE_USER"};

	private User() {}

	public User(String username, String password) {
		this.username = username;
		this.password = PASSWORD_ENCODER.encode(password);
	}
	
	public String[] getRoles() {
		return roles;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password= PASSWORD_ENCODER.encode(password);
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