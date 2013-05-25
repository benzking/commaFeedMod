package com.mod.backend.dao;

import com.mod.backend.model.SubscCategory;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-22
 * Time: 上午11:08
 * To change this template use File | Settings | File Templates.
 */
public interface SubscCategoryDao {
    public SubscCategory fecth(String userID,String subscCategoryName,SubscCategory parent);
    public SubscCategory save(SubscCategory subscCategory);

}
