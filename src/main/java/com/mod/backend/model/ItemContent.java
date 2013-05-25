package com.mod.backend.model;
/**
 * 条目实际内容
 * @author mod
 *
 */
public class ItemContent extends AbstractModel {
	/**
	 * 条目标题
	 */
	private String title;
	/**
	 * 条目内容
	 */
	private String content;
	/**
	 * 条目附件地址
	 */
	private String enclosureUrl;
	/**
	 * 条目附件类型
	 */
	private String enclosureType;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEnclosureUrl() {
		return enclosureUrl;
	}
	public void setEnclosureUrl(String enclosureUrl) {
		this.enclosureUrl = enclosureUrl;
	}
	public String getEnclosureType() {
		return enclosureType;
	}
	public void setEnclosureType(String enclosureType) {
		this.enclosureType = enclosureType;
	}
	
}
