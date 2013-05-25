package com.mod.backend.dao.JDBC;

import com.google.common.collect.Maps;
import com.mod.backend.dao.SubscCategoryDao;
import com.mod.backend.model.SubscCategory;
import com.mod.util.FENETKeyGenerator;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-22
 * Time: 上午11:11
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SubscCategoryDaoImp implements SubscCategoryDao{
    @Resource(name="jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public SubscCategory fecth(String userID, String subscCategoryName, SubscCategory parent) {
        SubscCategory subscCategory=null;

        String sql="SELECT ID,USERID,NAME,PARAENTID,collapesd " +
                " FROM CATEGORY " +
                " WHERE USERID=:USERID " +
                " AND NAME=:NAME " ;
        HashMap<String,Object> paramMap= Maps.newHashMap();
        paramMap.put("USERID",userID);
        paramMap.put("NAME",subscCategoryName);

        if(parent!=null&& StringUtils.isNotBlank(parent.getId())){
            sql=sql+" AND PARAENTID=:PARAENTID ";
            paramMap.put("PARAENTID",parent.getId());

        }else{
            sql=sql+" AND PARAENTID is null ";

        }

        try{
            subscCategory=(SubscCategory)jdbcTemplate.queryForObject(sql,paramMap,new SubscRowMapper());
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
        return subscCategory;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SubscCategory save(SubscCategory subscCategory) {
        String sql =" insert into category(id,NAME,userid,paraentid,collapesd) " +
                "values(:ID,:NAME,:USERID,:PARAENTID,:COLLAPESD) ";
        HashMap<String,Object> paramMap= Maps.newHashMap();
        paramMap.put("ID", FENETKeyGenerator.getKey());
        paramMap.put("NAME", subscCategory.getName());
        paramMap.put("USERID",subscCategory.getUser().getId() );
        if(subscCategory.getParaent()==null){
            paramMap.put("PARAENTID", null);
        }else{
            paramMap.put("PARAENTID", subscCategory.getParaent().getId());
        }
        paramMap.put("COLLAPESD", "1");
        jdbcTemplate.update(sql,paramMap);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    class SubscRowMapper implements RowMapper {

        @Override
        public SubscCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
            SubscCategory subscCategory=new SubscCategory();
            subscCategory.setId(rs.getString("ID"));
            subscCategory.setName(rs.getString("NAME"));

            return subscCategory;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
