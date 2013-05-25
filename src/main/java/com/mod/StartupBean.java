package com.mod;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-22
 * Time: 下午9:47
 * To change this template use File | Settings | File Templates.
 */
public class StartupBean implements ApplicationContextAware, DisposableBean {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //To change body of implemented methods use File | Settings | File Templates.
//        System.out.println("233");
        new WebContent().init(applicationContext);
    }

    @Override
    public void destroy() throws Exception {
        new WebContent().clearApplicationContext();
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
