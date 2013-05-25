package com.mod.backend.dao.JDBC;

import com.google.common.collect.Maps;
import com.mod.backend.dao.FeedDao;
import com.mod.backend.model.Feed;
import com.mod.util.FENETKeyGenerator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-20
 * Time: 下午4:06
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FeedDaoImp implements FeedDao {
    @Resource(name="jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public Feed save(Feed feed) {

        String sql="INSERT INTO feed(ID,URL,URLHASH,LINK,TITLE,XY_BJ) " +
                "VALUES(:ID,:URL,:URLHASH,:LINK,:TITLE,'1') ";
        feed.setId(FENETKeyGenerator.getKey());
        Map<String,Object> paraMap= Maps.newHashMap();
        paraMap.put("ID",FENETKeyGenerator.getKey());
        paraMap.put("URL",feed.getUrl());
        paraMap.put("URLHASH", DigestUtils.md5Hex(feed.getUrl()));
        paraMap.put("LINK", feed.getUrl());
        if(StringUtils.isBlank(feed.getTitle())){
            feed.setTitle("");
        }
        paraMap.put("TITLE", feed.getTitle());
        jdbcTemplate.update(sql,paraMap);
        return feed;
    }
    @Override
    public Feed fetch(String feedid) {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Feed fetchByURL(String URL) {
        Feed feed=null;
        String URLHash=DigestUtils.md5Hex(URL);
        String sql="select * from feed where URLHASH=:URLHASH ";
        Map<String,Object> paraMap= Maps.newHashMap();
        paraMap.put("URLHASH",URLHash);

        try{
        feed=(Feed)jdbcTemplate.queryForObject(sql,paraMap,new FeedRowMapper());
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
        return feed;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Feed> getFirstUpdateFeed(){
        List<Feed> feeds=null;
        try{

            String sql="select * from feed where lastmodifiedheader is null and XY_BJ='1'";
            feeds=jdbcTemplate.query(sql,new FeedRowMapper());
            System.out.println(feeds.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return feeds;
    }
    public List<Feed> getNeedUpdateFeed(){
        List<Feed> feeds=null;
        try{

            String sql="select * from feed where lastmodifiedheader is not null and XY_BJ='1'";
            feeds=jdbcTemplate.query(sql,new FeedRowMapper());
            System.out.println(feeds.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return feeds;
    }
    @Override
    public Feed update(Feed feed) {
        try{
            String sql="update  feed set etagheader=:etagheader ,lastmodifiedheader=:lastmodifiedheader, " +
                    " lastupdated=to_timestamp(:lastupdated,'yyyy-mm-dd hh24:mi:ss') " +
                    " where urlhash=:urlhash ";
            feed.setId(FENETKeyGenerator.getKey());
            Map<String,Object> paraMap= Maps.newHashMap();

            paraMap.put("urlhash", DigestUtils.md5Hex(feed.getUrl()));
            paraMap.put("etagheader", feed.getEtagHeader());
            paraMap.put("lastmodifiedheader", feed.getLastModifiedHeader());
            paraMap.put("lastupdated", feed.getLastUpdated().toString("yyyy-MM-dd HH:mm:ss"));
            jdbcTemplate.update(sql,paraMap);
        }catch (Exception e){
            e.printStackTrace();
        }

        return feed;
    }

    class FeedRowMapper implements RowMapper {

        @Override
        public Feed mapRow(ResultSet rs, int rowNum) throws SQLException {
            Feed feed=new Feed();
            feed.setId(rs.getString("ID"));
            feed.setUrl(rs.getString("URL"));
            feed.setUrlHash(rs.getString("URLHASH"));
            feed.setLink(rs.getString("LINK"));
            feed.setLastUpdated(new DateTime(rs.getDate("LASTUPDATED")));
            feed.setLastUpdateSuccess(new DateTime(rs.getDate("LASTUPDATESUCCESS")));
            feed.setMessage(rs.getString("MESSAGE"));
            feed.setErrorCount(rs.getInt("ERROTCOUNT"));
            feed.setEtagHeader(rs.getString("ETAGHEADER"));
            return feed;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
