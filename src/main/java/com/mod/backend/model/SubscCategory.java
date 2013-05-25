package com.mod.backend.model;

import java.util.Set;



/**
 * 订阅分类
 * @author mod
 * @since 2013-05-18
 */
public class SubscCategory extends AbstractModel {
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 分类拥有者
	 */
	private User user;
	/**
	 * 分类的父分类
	 */
	private SubscCategory paraent;
	/**
	 * 分类的子分类
	 */
	private Set<SubscCategory> children;
	/**
	 * 在页面上是否展开该分类的树形目录，不包含子分类
	 */
	private boolean collapsed;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SubscCategory getParaent() {
		return paraent;
	}
	public void setParaent(SubscCategory paraent) {
		this.paraent = paraent;
	}
	public Set<SubscCategory> getChildren() {
		return children;
	}
	public void setChildren(Set<SubscCategory> children) {
		this.children = children;
	}
	public boolean isCollapsed() {
		return collapsed;
	}
	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}
	
}
