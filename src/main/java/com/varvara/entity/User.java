package com.varvara.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "is_enabled")
	private boolean isEnabled;

	@Column(name = "is_blocked")
	private boolean isAccountNonLocked;

	@Column(name = "status")
	private String status;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private java.util.Collection<Role> roles;


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_collections",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "collection_id"))
	private List<Collection> collections;


	@OneToMany(mappedBy = "user",
			cascade = CascadeType.REMOVE)
	private List<Comment> comments;


	public User(String username, String password, String firstName, String lastName, String email, boolean isEnabled, boolean isAccountNonLocked, String status) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isEnabled = isEnabled;
		this.isAccountNonLocked = isAccountNonLocked;
		this.status = status;
	}

	public String getStatus() {
		if (isAccountNonLocked){
			return "ACTIVE";
		} else {
			return "BLOCKED";
		}
	}


	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
