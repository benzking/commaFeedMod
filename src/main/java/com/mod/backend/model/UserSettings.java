package com.mod.backend.model;
/**
 * 用户设置
 * @author mod
 * @since 2013-05-18
 */
public class UserSettings extends AbstractModel {
	public enum ReadingMode {
		all, unread
	}

	public enum ReadingOrder {
		asc, desc
	}

	public enum ViewMode {
		title, expanded
	}
	/**
	 * 设置对应用户
	 */
	private User user;
	/**
	 * 显示条目方式,枚举型:全部,已读
	 */
	private ReadingMode readingMode;
	/**
	 * 条目显示排序方式,枚举型:顺序,倒序
	 */
	private ReadingMode readingOrder;
	/**
	 * 条目显示方式,枚举型:标题，全文
	 */
	private ViewMode viewMode;
	/**
	 * 用户界面语言
	 */
	private String language;
	/**
	 * 
	 */
	private boolean showRead;
	/**
	 * 是否显示
	 */
	private boolean scrollMarks;
	/**
	 * 是否显示分享按钮
	 */
	private boolean socialButtons;
	/**
	 * 自定义CSS
	 */
	private String customCss;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ReadingMode getReadingMode() {
		return readingMode;
	}
	public void setReadingMode(ReadingMode readingMode) {
		this.readingMode = readingMode;
	}
	public ReadingMode getReadingOrder() {
		return readingOrder;
	}
	public void setReadingOrder(ReadingMode readingOrder) {
		this.readingOrder = readingOrder;
	}
	public ViewMode getViewMode() {
		return viewMode;
	}
	public void setViewMode(ViewMode viewMode) {
		this.viewMode = viewMode;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public boolean isShowRead() {
		return showRead;
	}
	public void setShowRead(boolean showRead) {
		this.showRead = showRead;
	}
	public boolean isScrollMarks() {
		return scrollMarks;
	}
	public void setScrollMarks(boolean scrollMarks) {
		this.scrollMarks = scrollMarks;
	}
	public boolean isSocialButtons() {
		return socialButtons;
	}
	public void setSocialButtons(boolean socialButtons) {
		this.socialButtons = socialButtons;
	}
	public String getCustomCss() {
		return customCss;
	}
	public void setCustomCss(String customCss) {
		this.customCss = customCss;
	}
	
}
