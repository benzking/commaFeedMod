package com.mod.backend.dao.JDBC;

import com.google.common.collect.Maps;
import com.mod.backend.dao.ItemStatusDao;
import com.mod.backend.model.ItemStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-20
 * Time: 下午3:58
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ItemStatusDaoImp implements ItemStatusDao {
    @Resource(name="jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public void save(ItemStatus status) {
       String sql="UPDATE SET READ=:READ,STARRED=:STARRED,MARKREAD=:MARKREAD WHERE ID=:ID ";
        Map<String,Object> paramMap= Maps.newHashMap();
        paramMap.put("ID",status);
        paramMap.put("READ",status.getRead());
        paramMap.put("STARRED",status.getStarred());
        paramMap.put("MARKREAD",status.getMarkRead());
        jdbcTemplate.update(sql,paramMap);
    }

    public void update(ItemStatus status) {
        String sql="UPDATE SET READ=:READ,STARRED=:STARRED,MARKREAD=:MARKREAD WHERE ID=:ID ";
        Map<String,Object> paramMap= Maps.newHashMap();
        paramMap.put("ID",status);
        paramMap.put("READ",status.getRead());
        paramMap.put("STARRED",status.getStarred());
        paramMap.put("MARKREAD",status.getMarkRead());
        jdbcTemplate.update(sql,paramMap);
    }
    public ItemStatus fecth(String id) {
        ItemStatus status=null;
        String sql="SELECT itemstatus.ID,itemstatus.ITEMID,itemstatus.SUBSCID, " +
                " itemstatus.ISREAD,itemstatus.STARRED,itemstatus.MARKREAD " +
                " FROM itemstatus " +
                " WHERE itemstatus.ID=:ID ";
        Map<String,Object> paramMap= Maps.newHashMap();
        paramMap.put("ITEMID",id);
        status=(ItemStatus)jdbcTemplate.queryForObject(sql,paramMap,new ItemStatusRowMapper());
        return status;
    }
    public void saveAll() {
        String sql="select ITEM.ID as ITEMID,sub.ID as SUBSCID  from subscribe sub " +
                " left join item item on item.FEEDID=sub.FEEDID " +
                " left join user user on user.ID=sub.USERID " +
                " where user.XY_BJ ='1' " +
                " and not exists (select 1 from itemstatus sta where sta.SUBSCID=sub.ID and sta.ITEMID=item.ID)";
    }
    @Override
    public ItemStatus fecth(String itemId, String subscId) {
        ItemStatus status=null;
        String sql="SELECT itemstatus.ID,itemstatus.ITEMID,itemstatus.SUBSCID, " +
                " itemstatus.ISREAD,itemstatus.STARRED,itemstatus.MARKREAD " +
                " FROM itemstatus " +
                " WHERE itemstatus.ITEMID=:ITEMID " +
                "   AND itemstatus.SUBSCID=:SUBSCID";
        Map<String,Object> paramMap= Maps.newHashMap();
        paramMap.put("ITEMID",itemId);
        paramMap.put("SUBSCID",subscId);
        status=(ItemStatus)jdbcTemplate.queryForObject(sql,paramMap,new ItemStatusRowMapper());
        return status;
    }

    class ItemStatusRowMapper implements RowMapper{

        @Override
        public ItemStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
            ItemStatus status1=new ItemStatus();
            status1.setId(rs.getString("ID"));
            //status1.setId(rs.getString("ITEMID"));
            //status1.setS(rs.getString("SUBSCID"));
            status1.setRead(rs.getString("ISREAD"));
            status1.setStarred(rs.getString("STARRED"));
            status1.setMarkRead(rs.getString("MARKREAD"));
            return status1;
        }
    }

}
