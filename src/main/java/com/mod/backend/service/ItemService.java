package com.mod.backend.service;

import com.mod.backend.dao.JDBC.FeedDaoImp;
import com.mod.backend.dao.JDBC.ItemDaoImp;
import com.mod.backend.dao.JDBC.ItemStatusDaoImp;
import com.mod.backend.dao.JDBC.SubscDaoImp;
import com.mod.backend.model.Item;
import com.mod.backend.model.ItemStatus;
import com.mod.backend.model.Subscription;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-27
 * Time: 上午9:52
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ItemService {
    @Resource
    private SubscDaoImp subscDaoImp;
    @Resource
    private FeedDaoImp feedDaoImp;
    @Resource
    private ItemStatusDaoImp itemStatusDaoImp;
    @Resource
    private ItemDaoImp itemDaoImp;
    public List<Item> query(String cateId ,String feedId,String ReadType){
        List<Item> items=null;
        if(StringUtils.isNotBlank(cateId)){
            items=itemDaoImp.queryForCate(cateId,ReadType);
        }else{
            items=itemDaoImp.queryForFeed(feedId,ReadType);
        }
        return items;
    }
    public void read(String userid,String feedid){
        Subscription Subscription=subscDaoImp.fecth(userid,feedid);
        ItemStatus itemStatus=itemStatusDaoImp.fecth(userid,Subscription.getId());
        itemStatus.setRead("1");
        itemStatusDaoImp.update(itemStatus);
    }
    public void markRead(String userid,String feedid){
        Subscription Subscription=subscDaoImp.fecth(userid,feedid);
        ItemStatus itemStatus=itemStatusDaoImp.fecth(userid,Subscription.getId());
        itemStatus.setRead("1");
        itemStatus.setMarkRead("1");
        itemStatusDaoImp.update(itemStatus);
    }
    public void keepUnread(String userid,String feedid){
        Subscription Subscription=subscDaoImp.fecth(userid,feedid);
        ItemStatus itemStatus=itemStatusDaoImp.fecth(userid,Subscription.getId());
        itemStatus.setRead("0");
        itemStatusDaoImp.update(itemStatus);
    }
    public void starred(String userid,String feedid){
        Subscription Subscription=subscDaoImp.fecth(userid,feedid);
        ItemStatus itemStatus=itemStatusDaoImp.fecth(userid,Subscription.getId());
        itemStatus.setStarred("1");
        itemStatusDaoImp.update(itemStatus);
    }
}
