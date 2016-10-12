package com.waterfall.models;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

import java.util.Date;

@Entity
@NamedQueries({ @NamedQuery(name = "UserModel.findAll", query = "SELECT u FROM UserModel u"),
		@NamedQuery(name = "UserModel.findByUsername", query = "SELECT u FROM UserModel u WHERE u.username LIKE :username"),
		@NamedQuery(name = "UserModel.findByEmail", query = "SELECT u FROM UserModel u WHERE u.email LIKE :email"), 
		@NamedQuery(name = "UserModel.findByCountry", query = "SELECT u FROM UserModel u WHERE u.country LIKE :country"),
		@NamedQuery(name = "UserModel.findByCity", query = "SELECT u FROM UserModel u WHERE u.city LIKE :city"),
		@NamedQuery(name = "UserModel.findByFirstName", query = "SELECT u FROM UserModel u WHERE u.firstName LIKE :firstname"),
		@NamedQuery(name = "UserModel.findByLastName", query = "SELECT u FROM UserModel u WHERE u.lastName LIKE :lastname"),
		@NamedQuery(name = "UserModel.findByGender", query = "SELECT u FROM UserModel u WHERE u.gender LIKE :gender"),
		@NamedQuery(name = "UserModel.findByBirthdate" , query = "SELECT u FROM UserModel u WHERE u.birthdate BETWEEN :enddate AND :startdate")
})

public class UserModel implements Serializable {

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
	
	private List<UserModel> friendsList;

	@OneToMany(mappedBy = "owner")
	private List<DropModel> dropList;
	
	public UserModel() {
		
	}
	public List<DropModel> getDrops() {
		return dropList;
	}

	public void setDrops(List<DropModel> dropModels) {
		this.dropList = dropModels;
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

	public List<UserModel> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(List<UserModel> friendsList) {
		this.friendsList = friendsList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwordhash) {
		this.password = passwordhash;
	}


}