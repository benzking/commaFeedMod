package com.mod.backend.model;

import java.util.Set;

/**
 * 
 * @author mod
 * @since 2013-05-18
 */
public class Subscription extends AbstractModel {
	/**
	 * 
	 */
	private User user;
	/**
	 * 对应订阅
	 */
	private Feed feed;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 分类
	 */
	private SubscCategory category;

    public SubscCategory getCategory() {
        return category;
    }

    public void setCategory(SubscCategory category) {
        this.category = category;
    }

    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Feed getFeed() {
		return feed;
	}
	public void setFeed(Feed feed) {
		this.feed = feed;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
}
