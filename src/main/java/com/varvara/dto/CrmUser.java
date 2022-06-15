package com.varvara.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
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


}
