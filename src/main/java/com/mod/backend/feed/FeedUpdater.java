package com.mod.backend.feed;

import com.mod.WebContent;
import com.mod.backend.model.Feed;

import com.sun.syndication.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-22
 * Time: 下午8:30
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class FeedUpdater implements Runnable {
    @Autowired
    private FeedFecther fecther;
    @Override
    public void run() {
        Feed feed=null;
        while (WebContent.isUpdateFeed) {
            if(WebContent.feedUpTaskQueue.size() > 0){
                try {

                    feed=(Feed)WebContent.feedUpTaskQueue.take();
                } catch (InterruptedException e) {

                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    continue;
                }
                if(feed!=null){
                    fecther.fetch(feed.getUrl(),feed.getLastModifiedHeader(),feed.getEtagHeader());

                }
            }
        }
    }
}

