package com.varvara.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CrmUser {

	@NotBlank(message = "is required field")
	private String username;

	@NotBlank(message = "is required field")
	private String password;

	@NotBlank(message = "is required field")
	private String firstName;

	@NotBlank(message = "is required field")
	private String lastName;

	@NotBlank(message = "is required field")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	private String email;

	public CrmUser() {
	}


}
