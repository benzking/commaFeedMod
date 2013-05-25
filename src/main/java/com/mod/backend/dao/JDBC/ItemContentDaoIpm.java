package com.mod.backend.dao.JDBC;

import com.google.common.collect.Maps;
import com.mod.backend.dao.ItemContentDao;
import com.mod.backend.dao.ItemDao;
import com.mod.backend.model.Item;
import com.mod.backend.model.ItemContent;
import com.mod.util.FENETKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-20
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ItemContentDaoIpm  implements ItemContentDao {
    @Resource(name="jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public ItemContent saveOrUpdate(ItemContent content) {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }



    @Override
    public ItemContent save(ItemContent content) {

        String sql="INSERT INTO ITEMCONTENT(ID,TITLE,CONTENT) " +
                "VALUES(:ID,:TITLE,:CONTENT)";
        HashMap<String,Object> paramMap= Maps.newHashMap();
        String contentid=FENETKeyGenerator.getKey();
        content.setId(contentid);
        paramMap.put("ID", content.getId());
        paramMap.put("TITLE",content.getTitle());
        paramMap.put("CONTENT",content.getContent());
        jdbcTemplate.update(sql, paramMap);
        return content;
    }

    @Override
    public void del(String ID) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ItemContent update(ItemContent content) {
        String sql="UPDATE ITEMCONTENT SET TITLE=:TITLE,CONTENT=:CONTENT " +
                " WHERE ID=:ID ";
        HashMap<String,Object> paramMap= Maps.newHashMap();

        paramMap.put("ID",content.getId());
        paramMap.put("TITLE",content.getTitle());
        paramMap.put("CONTENT",content.getContent());
        jdbcTemplate.update(sql, paramMap);
        return content;
    }
}
