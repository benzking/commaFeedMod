package com.mod.backend.model;
/**
 * 条目状态
 * @author mod
 * @since 2013-05-18
 */
public class ItemStatus extends AbstractModel {
	/**
	 * 
	 */
	private Subscription subscription;
	/**
	 * 对应条目
	 */
	private Item item;
	/**
	 * 是否已读
	 */
	private String read;
	/**
	 * 是否标记（GR中的加星标）
	 */
	private String starred;
    /**
     * 是否使用标记已读
     */
    private String markRead;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getMarkRead() {
        return markRead;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public void setMarkRead(String markRead) {
        this.markRead = markRead;
    }

    public String getStarred() {
		return starred;
	}
	public void setStarred(String starred) {
		this.starred = starred;
	}
	
}
