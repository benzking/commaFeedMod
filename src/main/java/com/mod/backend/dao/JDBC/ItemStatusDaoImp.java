package com.mod.backend.dao.JDBC;

import com.google.common.collect.Maps;
import com.mod.backend.dao.ItemStatusDao;
import com.mod.backend.model.ItemStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Resource;
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
public class ItemStatusDaoImp implements ItemStatusDao {
    @Resource(name="jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public void save(ItemStatus status) {
        //To change body of implemented methods use File | Settings | File Templates.
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
        status=jdbcTemplate.queryForObject(sql,paramMap,new RowMapper<ItemStatus>() {
            @Override
            public ItemStatus mapRow(ResultSet resultSet, int i) throws SQLException {
                ItemStatus status1=new ItemStatus();
                status1.setId(resultSet.getString("ID"));
                //status1.setId(resultSet.getString("ITEMID"));
                //status1.setS(resultSet.getString("SUBSCID"));
                status1.setRead(resultSet.getString("ISREAD"));
                status1.setStarred(resultSet.getString("STARRED"));
                status1.setMarkRead(resultSet.getString("MARKREAD"));
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        return status;
    }

}
