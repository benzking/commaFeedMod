package com.mod.backend.dao;

import com.mod.backend.model.Subscription;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-20
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
public interface SubscDao {
    public Subscription save(Subscription subscrption);
    public Subscription save(String userId,String feedId,String subscTitle, String categoryID);
    public List query(String userId);
    public List query(String userId,String CategID);
    public Subscription fecth(String userId,String CategID);

}
