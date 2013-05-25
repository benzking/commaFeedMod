package com.mod.backend.dao.JDBC;

import com.google.common.collect.Maps;
import com.mod.backend.dao.SubscDao;
import com.mod.backend.model.Subscription;
import com.mod.util.FENETKeyGenerator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
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
    public List fecth(String userId) {
        String sql="";
        return null;
    }

    @Override
    public List fecth(String userId, String CategID) {
        return null;
    }

}
