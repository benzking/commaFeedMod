package com.mod.backend.model;

import java.util.Set;

import org.joda.time.DateTime;

import com.google.common.collect.Sets;
/**
 * 条目主要情况？
 * @author mod
 * @since 2013-05-18
 */
public class Item extends AbstractModel {
	/**
	 * guid,主键？
	 */
	private String guid;
	/**
	 * guidHash,主键？
	 */
	private String guidHash;
	/**
	 * 一个订阅的内容可以拥有一个订阅Set?
	 */
    private String title;

    private Feed feed;
	/**
	 * 条目的内容
	 */
	private ItemContent content;
	/**
	 * 条目的实际地址
	 */
	private String url;
    /**
     * 发布日期
     */
    private DateTime updated;
	/**
	 * 录入日期？
	 */
	private DateTime LR_SJ;
	/**
	 * 更新日期？
	 */
	private DateTime XG_SJ;

	/**
	 * 条目状态？
	 */
	private ItemStatus status;

	public Item(){

	}

    public DateTime getUpdated() {
        return updated;
    }

    public void setUpdated(DateTime updated) {
        this.updated = updated;
    }

    public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getGuidHash() {
		return guidHash;
	}
	public void setGuidHash(String guidHash) {
		this.guidHash = guidHash;
	}
	public Feed getFeed() {
		return feed;
	}
	public void setFeed(Feed feed) {
		this.feed = feed;
	}
	public ItemContent getContent() {
		return content;
	}
	public void setContent(ItemContent content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

    public DateTime getLR_SJ() {
        return LR_SJ;
    }

    public DateTime getXG_SJ() {
        return XG_SJ;
    }

    public void setXG_SJ(DateTime XG_SJ) {
        this.XG_SJ = XG_SJ;
    }

    public void setLR_SJ(DateTime LR_SJ) {
        this.LR_SJ = LR_SJ;
    }

    public ItemStatus getStatus() {
		return status;
	}
	public void setStatus(ItemStatus status) {
		this.status = status;
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
