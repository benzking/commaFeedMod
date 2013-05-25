package com.mod.backend.dao;

import com.mod.backend.model.ItemContent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-20
 * Time: 下午2:52
 * To change this template use File | Settings | File Templates.
 */
public interface ItemContentDao {
    public ItemContent saveOrUpdate(ItemContent content);
    public ItemContent save(ItemContent content);
    public ItemContent update(ItemContent content);
    public void del(String ID);
}
