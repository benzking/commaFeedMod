package com.mod.backend;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-19
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class HttpResult {
    private String content;
    private String lastModifiedSince;
    private String eTag;
    /**
     * 获取请求结果用时
     */
    private long duration;

    public String getLastModifiedSince() {
        return lastModifiedSince;
    }

    public void setLastModifiedSince(String lastModifiedSince) {
        this.lastModifiedSince = lastModifiedSince;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
