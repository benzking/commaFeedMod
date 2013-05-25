package com.mod.backend.dao;

import com.mod.backend.model.ItemStatus;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-20
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public interface ItemStatusDao {
    public void save(ItemStatus status);
    public ItemStatus fecth(String itemId,String subscId);
}
