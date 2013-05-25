package com.mod.backend.dao;

import com.mod.backend.model.Feed;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-20
 * Time: 下午4:01
 * To change this template use File | Settings | File Templates.
 */
public interface FeedDao {
    public Feed save(Feed feed);
    public Feed fetch(String feedid);
    public Feed fetchByURL(String URL);
    public List<Feed> getFirstUpdateFeed();
    public List<Feed> getNeedUpdateFeed();
    public Feed update(Feed feed);
}
