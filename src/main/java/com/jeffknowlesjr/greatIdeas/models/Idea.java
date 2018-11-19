package com.jeffknowlesjr.greatIdeas.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ideas")
public class Idea {
		
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@Size( min = 1, max = 255, message = "Idea name must be between 1-255 characters.")
	private String name;
	
	@Valid
	@NotNull
	private Long creator_id;
	
	@Valid
	@NotNull
	private String creator_name;
	
	@ManyToMany(mappedBy="ideas")
	private List<User> users;
	
	public Idea() {}

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
	
	public Long getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(Long creator_id) {
		this.creator_id = creator_id;
	}

	public String getCreator_name() {
		return creator_name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}