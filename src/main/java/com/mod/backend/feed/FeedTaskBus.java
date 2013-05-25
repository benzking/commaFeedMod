package com.mod.backend.feed;

import com.google.common.collect.Queues;
import com.mod.WebContent;
import com.mod.backend.dao.FeedDao;
import com.mod.backend.model.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-22
 * Time: 下午9:42
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class FeedTaskBus implements Runnable{
    /**
     * 每隔多长时间刷新一次队列，查看有没有新的需要更新的feed
     */
    public int FetchInvertal = 60000;
    @Autowired
    @Qualifier("feedDaoImp")
    private FeedDao feedDao;
    @Override
    public void run() {
        List<Feed> tmpls=null;
        while (WebContent.isUpdateFeed) {

            try {

                tmpls = this.getFirstUpdateFeed();
                System.out.println(tmpls.size());
                if (tmpls != null && tmpls.size() > 0) {
                    for(Feed feed:tmpls){
                        WebContent.feedUpTaskQueue.put(feed);
                    }
                    tmpls=null;
                }
                tmpls = this.getNeedUpdateFeed();
                if (tmpls != null && tmpls.size() > 0) {
                    for(Feed feed:tmpls){
                        WebContent.feedUpTaskQueue.put(feed);
                    }
                    tmpls=null;
                }else{
                    // 每隔一秒钟就检测一下是否有新的待处理的数据
                    System.out.println("quene:"+WebContent.feedUpTaskQueue.size());
                    Thread.sleep(FetchInvertal);
                }

            } catch (InterruptedException ex) {
            }
        }
    }
    private List<Feed> getFirstUpdateFeed(){
        return feedDao.getFirstUpdateFeed();
    }
    private List<Feed> getNeedUpdateFeed(){
        return feedDao.getNeedUpdateFeed();
    }
}