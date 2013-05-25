package com.mod.test;

import com.mod.backend.feed.OPMLImporter;
import com.mod.backend.model.User;
import com.mod.util.SpringContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-22
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TestImpo {

    public void testImpo()  {
        try{
            System.out.println("Start");
            FileInputStream inputstream = new FileInputStream("E:\\Downloads\\subscriptions.opml");
            StringBuffer buffer = new StringBuffer();
            String line; // 用来保存每行读取的内容
            BufferedReader bufferreader = new BufferedReader(new InputStreamReader(
                    inputstream));
            line = bufferreader.readLine(); // 读取第一行
            while (line != null) { // 如果 line 为空说明读完了
                buffer.append(line); // 将读到的内容添加到 buffer 中
                 buffer.append("\n"); // 添加换行符
                line = bufferreader.readLine(); // 读取下一行

            }
            // 将读到 buffer 中的内容写出来
            inputstream.close();
            String filetext = buffer.toString();
//            System.out.println(filetext);
            ApplicationContext ctx=null;
            ctx = SpringContextHolder.getApplicationContext();
            OPMLImporter OPMLImporter= (OPMLImporter) ctx.getBean(OPMLImporter.class);
            User user=new User();
            user.setId("001");
            String opml=filetext;
            OPMLImporter.importOpml(user,opml);
            System.out.println("End");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
