package com.mod.backend.feed;


import com.mod.backend.HttpGetter;
import com.mod.backend.HttpResult;
import com.mod.backend.dao.FeedDao;
import com.mod.backend.dao.ItemDao;
import com.mod.backend.dao.JDBC.ItemDaoImp;
import com.mod.backend.model.Feed;
import com.sun.syndication.io.FeedException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-19
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FeedFecther {
    @Autowired
    @Qualifier("itemDaoImp")
    private ItemDao itemDao;
    @Autowired
    @Qualifier("feedDaoImp")
    private FeedDao feedDao;
    //@Transactional(propagation =Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
    public void fetch(String feedUrl,String lastModified,String eTag) {
        try{


        HttpGetter getter=new HttpGetter();
        DateTime lastupdated=DateTime.now();
        HttpResult result =getter.getBinary(feedUrl,lastModified,eTag);
        if (result==null||result.getContent() == null) {
            System.out.println(feedUrl+"::Feed content is empty.");
            return ;
        }

        FeedParser parser=new FeedParser();


        List items=parser.parse(feedUrl,result.getContent());
        System.out.println("items.size():"+items.size());
        itemDao.saveOrUpdate(items);
        Feed feed=new Feed();
        feed.setUrl(feedUrl);
        feed.setLastUpdated(lastupdated);
        feed.setEtagHeader(result.geteTag());
        feed.setLastModifiedHeader(result.getLastModifiedSince());

        feedDao.update(feed);
        }catch (IOException e){
            e.printStackTrace();
        }catch (FeedException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
