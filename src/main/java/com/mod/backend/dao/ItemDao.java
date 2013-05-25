package com.mod.backend.dao;

import com.mod.backend.model.Item;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-20
 * Time: 下午12:05
 * To change this template use File | Settings | File Templates.
 */
public interface ItemDao {

    public void saveOrUpdate(Item item);
    public void saveOrUpdate(List<Item> items);
    public void save(Item item);
    public void save(List<Item> items) throws NoSuchAlgorithmException;
    public void update(Item item);
    public void del(String itemId);
    public Item fecthByURI(String guid);

}
