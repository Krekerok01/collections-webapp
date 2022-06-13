package com.varvara.user;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CrmUser {

	@NotBlank(message = "Required field")
	private String username;

	@NotBlank(message = "Required field")
	private String password;

	@NotBlank(message = "Required field")
	private String firstName;

	@NotBlank(message = "Required field")
	private String lastName;

	@NotBlank
	@Email(regexp = "[a-zA-Z0-9]+@[a-zA-Z]+.[a-zA-Z]+", message = "It must be a valid email and end with 'gmail.com'")
	private String email;

	public CrmUser() {

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CrmUser{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
