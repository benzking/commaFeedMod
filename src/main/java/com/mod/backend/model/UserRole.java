package com.mod.backend.model;


/**
 * 用户权限
 * @author mod
 *
 */
public class UserRole extends AbstractModel {
	public static enum Role {
		USER, ADMIN, NONE
	}
	private User user;
	private Role role;
	public UserRole() {

	}

	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
