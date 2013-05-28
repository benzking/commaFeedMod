package com.mod.backend.dao.JDBC;

import com.google.common.collect.Maps;
import com.mod.backend.dao.SubscDao;
import com.mod.backend.model.Feed;
import com.mod.backend.model.SubscCategory;
import com.mod.backend.model.Subscription;
import com.mod.backend.model.User;
import com.mod.util.FENETKeyGenerator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-20
 * Time: 下午4:07
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SubscDaoImp implements SubscDao {
    @Resource(name="jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public Subscription save(Subscription subscrption) {
        String sql="INSERT INTO subscription(ID,FEEDID,USERID,TITLE,CATEGORYID) " +
                " VALUES(:ID,:FEEDID,:CATEGORYID,:TITLE,:CATEGORYID) ";
        return subscrption;
    }
    @Override
    public Subscription save(String userId,String feedId,String subscTitle, String categoryID) {
        String sql="INSERT INTO subscription(ID,FEEDID,USERID,TITLE,CATEGORYID) " +
                " VALUES(:ID,:FEEDID,:USERID,:TITLE,:CATEGORYID) ";
        HashMap<String,Object> paramMap= Maps.newHashMap();
        paramMap.put("ID", FENETKeyGenerator.getKey());
        paramMap.put("FEEDID", userId);
        paramMap.put("USERID", feedId);
        paramMap.put("TITLE", subscTitle);
        paramMap.put("CATEGORYID", categoryID);
        jdbcTemplate.update(sql,paramMap);
        return null;
    }
    @Override
    public List query(String userId) {
        String sql="";
        return null;
    }

    @Override
    public List query(String userId, String CategID) {
        return null;
    }
    @Override
    public Subscription fecth(String userId, String feedId) {
        String sql="select * from subscription where userid=:userid and feedid=:feedid ";
        HashMap<String,Object> paramMap= Maps.newHashMap();
        paramMap.put("userid", userId);
        paramMap.put("feedid", feedId);
        jdbcTemplate.query(sql,paramMap,new statusRowMapper());
        return null;
    }
    class statusRowMapper implements RowMapper{

        @Override
        public Subscription mapRow(ResultSet rs, int rowNum) throws SQLException {
            Subscription subscription=new Subscription();
            subscription.setId(rs.getString("ID"));
            subscription.setTitle(rs.getString("title"));
            SubscCategory sc=new SubscCategory();
            sc.setId(rs.getString("categoryid"));
            subscription.setCategory(sc);
            Feed feed=new Feed();
            feed.setId(rs.getString("feedid"));
            subscription.setFeed(feed);
            User user=new User();
            user.setId(rs.getString("userid"));
            subscription.setUser(user);
            return subscription;
        }
    }
}
