package com.mod.backend.feed;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.mod.backend.model.Item;
import com.mod.backend.model.ItemContent;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-19
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
public class FeedParser {
    /**
     * guava 函数式编程
     */
    private static final Function<SyndContent, String> CONTENT_TO_STRING = new Function<SyndContent, String>() {
        public String apply(SyndContent content) {
            return content.getValue();
        }
    };

    public List parse(String feedUrl,String xml) throws FeedException, UnsupportedEncodingException {
        String encoding = FeedUtils.guessEncoding(xml.getBytes());
        String xmlString = FeedUtils.trimInvalidXmlCharacters(new String(xml.getBytes(), encoding));
        System.out.println(feedUrl+":sEncoding:"+encoding);
        InputSource source = new InputSource(new StringReader(xmlString));
        SyndFeed rss = new SyndFeedInput().build(source);
//        fetchedFeed.setTitle(rss.getTitle());
        List<Item> items=new ArrayList<Item>();
        List<SyndEntry> entries = rss.getEntries();
        for (SyndEntry entry : entries) {

            Item item=new Item();
            item.setGuid(entry.getUri());
            item.setGuidHash(entry.getUri());
            item.setUrl(entry.getLink());
            item.setUpdated(this.getUpdateDate(entry));
            ItemContent content = new ItemContent();
            content.setContent(this.getContent(entry));
            content.setTitle(entry.getTitle());
            item.setContent(content);
            items.add(item);
        }
        return items;
    }

    /**
     * 条目更新时间，条目原更新时间为空，就用条目发布时间，都为空，返回当前时间
     * @param item
     * @return
     */
    private DateTime getUpdateDate(SyndEntry item) {

        DateTime date=null;
        if(item.getUpdatedDate()!=null){
            date= new DateTime(item.getUpdatedDate());
        }
        if (date == null) {
            date = new DateTime(item.getPublishedDate());
        }

        if (date == null) {
            date = DateTime.now();
        }

        return date;
    }

    /**
     * 获取条目内容，如果内容为空，获取简要描述当作内容，都为空返回
     * @param item 条目
     * @return 条目内容
     */
    private String getContent(SyndEntry item){
        String content=null;
        if(item.getContents().isEmpty()){
            //
            if(StringUtils.isNotBlank(item.getDescription().getValue())){
                return item.getDescription().getValue();
            }
            return null;
        }
        content = StringUtils.join(Collections2.transform(
                item.getContents(), CONTENT_TO_STRING),
                SystemUtils.LINE_SEPARATOR);

        return content;

    }
}
