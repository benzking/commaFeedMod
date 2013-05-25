package com.mod.backend.model;

import java.util.Set;

import org.joda.time.DateTime;

import com.google.common.collect.Sets;
/**
 * 一个订阅地址
 * @author mod
 * @since 2013-05-18
 */
public class Feed extends AbstractModel {
	/**
	 * RSS订阅地址（？）
	 */
	private String url;
	/**
	 * ????
	 */
    private String title;
	private String urlHash;
	/**
	 * RSS订阅地址
	 */
	private String link;
	/**
	 * 上次尝试获取RSS内容时间（无论是否更新成功）
	 */
	private DateTime lastUpdated;
	/**
	 * 上次成功获取RSS内容时间
	 */
	private DateTime lastUpdateSuccess;
	private String message;
	private int errorCount;
	/**
	 * 应该是删除时间
	 */
	private DateTime disableUtil;
    private String lastModifiedHeader;
	/**
	 * tag？，可能是分类
	 */
	private String etagHeader;

	/**
	 * 该订阅的内容Set
	 */
	private Set<Item> items;
	/**
	 * 一对多关系的订阅？
	 */
	private Set<Subscription> subdcriptions;

	public Feed(){
		items=Sets.newHashSet();
//		subdcriptions=Sets.newHashSet();
	}

    public String getLastModifiedHeader() {
        return lastModifiedHeader;
    }

    public void setLastModifiedHeader(String lastModifiedHeader) {
        this.lastModifiedHeader = lastModifiedHeader;
    }

    public DateTime getLastUpdateSuccess() {
        return lastUpdateSuccess;
    }

    public void setLastUpdateSuccess(DateTime lastUpdateSuccess) {
        this.lastUpdateSuccess = lastUpdateSuccess;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlHash() {
		return urlHash;
	}
	public void setUrlHash(String urlHash) {
		this.urlHash = urlHash;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public DateTime getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(DateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public DateTime getDisableUtil() {
		return disableUtil;
	}
	public void setDisableUtil(DateTime disableUtil) {
		this.disableUtil = disableUtil;
	}
	public String getEtagHeader() {
		return etagHeader;
	}
	public void setEtagHeader(String etagHeader) {
		this.etagHeader = etagHeader;
	}
	public Set<Item> getEntries() {
		return items;
	}
	public void setEntries(Set<Item> entries) {
		this.items = entries;
	}
	public Set<Subscription> getSubdcriptions() {
		return subdcriptions;
	}
	public void setSubdcriptions(Set<Subscription> subdcriptions) {
		this.subdcriptions = subdcriptions;
	}
	
	
	
}
