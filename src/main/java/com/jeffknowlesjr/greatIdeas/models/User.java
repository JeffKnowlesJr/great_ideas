package com.jeffknowlesjr.greatIdeas.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@Size( min = 1, max = 255, message = "Name must be between 1-255 characters.")
	private String name;
	
	@Email( message = "Invalid email format. Example: example@example.com.")
	@Size( min = 1, message = "Email must be present. Example: example@example.com.")
	private String email;
	
	@Size( min = 1, message = "Password must be at least one character.")
	private String password;
	
	@Size( min = 1, message = "Passwords must match.")
	@Transient
	private String confirmPassword;
	
	@ManyToMany
	@JoinTable(name="user_idea")
	private List<Idea> ideas;
	
	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Idea> getIdeas() {
		return ideas;
	}

	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
	}
	
}

