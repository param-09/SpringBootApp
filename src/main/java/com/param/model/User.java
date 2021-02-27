package com.param.model;

import javax.persistence.Column;		
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="userdata")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name="username")
	@NotNull
	@Pattern(regexp="[a-zA-Z]{3,20}",message="Invalid Username")
	@Size(min=3, message="Username field should not be empty")
	private String username;
	
	
	@Column(name="password")
	@NotNull
	@Size(min=4,message="Password Field Should Not Be Empty")
	private String password;
	
	
	@Column(name="contact")
	@NotNull
	@Pattern(regexp="[1-9][0-9]{9}",message="Invalid Contact Number")
	@Size(min=9,max=10,message="Enter valid Number")
	private String contact;
	
	
	@Column(name="email")
	@NotEmpty
	@Size(max=50,message="Email Field Should Not Be Empty")
	private String email;
	
	
	@Column(name="address")
	@NotEmpty
	@Size(max=260,message="Address Field Should Not Be Empty")
	private String address;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
