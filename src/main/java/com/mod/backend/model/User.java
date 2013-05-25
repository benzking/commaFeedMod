package com.mod.backend.model;

import com.google.common.collect.Sets;
import org.joda.time.DateTime;

import java.util.Set;

public class User extends AbstractModel {
	private String name;
	private String email;
	private String password;
	private boolean disabled;
	private DateTime lastLogin;
	private Set<UserRole> roles;
	public User(){
		roles=Sets.newHashSet();
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
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public DateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(DateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Set<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	
}
