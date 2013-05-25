package com.mod.backend.service;

import com.mod.backend.dao.FeedDao;
import com.mod.backend.dao.JDBC.SubscDaoImp;
import com.mod.backend.dao.SubscCategoryDao;
import com.mod.backend.dao.SubscDao;
import com.mod.backend.model.Feed;
import com.mod.backend.model.SubscCategory;
import com.mod.backend.model.Subscription;
import com.mod.backend.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-22
 * Time: 上午11:29
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SubscriptionService {

    @Autowired
    @Qualifier("feedDaoImp")
    private FeedDao feedDao;
    @Autowired
    @Qualifier("subscCategoryDaoImp")
    private SubscCategoryDao subscCategoryDaoImp;
    @Autowired
    @Qualifier("subscDaoImp")
    private SubscDao subscDao;

    public void subscribe(String userId,String feedURL,String feedTitle, SubscCategory parent){
        Feed feed=feedDao.fetchByURL(feedURL);
        if(feed==null){
            feed=new Feed();
            feed.setUrl(feedURL);
            feed.setLink(feedURL);
            feed.setTitle(feedTitle);
            feed=feedDao.save(feed);
        }

        String categoryID=null;
        if(parent!=null&& StringUtils.isNotBlank(parent.getId())){
            categoryID=parent.getId();
        }
        this.subscribe(userId,feed.getId(),feedTitle,categoryID);
    }
    public void subscribe(User user,Feed feed,String feedTitle, SubscCategory parent){

//        this.subscribe(user.getId(),feed.getId(),feedTitle,parent);
    }

    public void subscribe(String userId,String feedId,String subscTitle, String categoryID){
        if(StringUtils.isBlank(subscTitle)){
            Feed feed=feedDao.fetch(feedId);
            if(StringUtils.isNotBlank(feed.getTitle())){
                subscTitle=feed.getTitle();
            }
        }
        subscDao.save(userId,feedId,subscTitle,categoryID);

    }
}
