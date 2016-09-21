package com.waterfall.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

import org.eclipse.persistence.internal.oxm.schema.model.All;

import java.util.ArrayList;
import java.util.Date;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username LIKE :username"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email LIKE :email") })

public class User implements Serializable {

	private static final long serialVersionUID = 5461470282886888730L;

	@Id
	private Long userid;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	private String city;

	private String email;

	private String firstName;

	private String gender;

	private String lastName;

	private String password;

	private String username;

	private String country;

	@OneToMany(mappedBy = "owner")
	private List<Droplet> droplets;

	public User() {
	}
	
	

	public List<Droplet> getDroplets() {
		return droplets;
	}



	public void setDroplets(List<Droplet> droplets) {
		this.droplets = droplets;
	}



	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}