package com.mod;

import com.google.common.collect.Queues;
import com.mod.backend.feed.FeedTaskBus;
import com.mod.backend.feed.FeedUpdater;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-22
 * Time: 下午9:53
 * To change this template use File | Settings | File Templates.
 */

public class WebContent {
    private static ApplicationContext applicationContext = null;
    public final static ExecutorService feedUpTaskBusservice = Executors
            .newSingleThreadExecutor();
    public final static ExecutorService feedUpTaskService = Executors
            .newFixedThreadPool(4);
    public final static BlockingQueue feedUpTaskQueue = Queues.newArrayBlockingQueue(100);
    public static Boolean isUpdateFeed;
    public void init(ApplicationContext applicationContext){
        System.out.println("启动初始化");
        WebContent.applicationContext=applicationContext;
        isUpdateFeed=true;
        FeedTaskBus feedTaskBus=applicationContext.getBean(FeedTaskBus.class);
        feedUpTaskBusservice.submit(feedTaskBus);
        for (int index = 0; index < 4; index++) {
            FeedUpdater feedUpdater=applicationContext.getBean(FeedUpdater.class);
            feedUpTaskService.submit(feedUpdater);
        }
        System.out.println("初始化完毕");
    }

    public void clearApplicationContext() throws Exception {
        applicationContext=null;
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }
    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }
}
