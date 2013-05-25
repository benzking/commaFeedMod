package com.mod.backend.dao.JDBC;

import com.google.common.collect.Maps;
import com.mod.backend.dao.ItemContentDao;
import com.mod.backend.dao.ItemDao;
import com.mod.backend.model.Item;
import com.mod.backend.model.ItemContent;
import com.mod.util.FENETKeyGenerator;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-20
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ItemDaoImp implements ItemDao {
    @Resource
    private ItemContentDaoIpm contentDao;
    @Resource(name="jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void saveOrUpdate(Item item) {
        Item itemTls=this.fecth(item.getGuid());

        if(itemTls!=null){
            System.out.println("UPDATE::"+item.getContent().getTitle());
            item.setId(itemTls.getId());
            this.update(item);
            item.getContent().setId(itemTls.getId());
            contentDao.update(item.getContent());
        }else {
            System.out.println("SAVE::"+item.getTitle());
            ItemContent content=item.getContent();
            content=contentDao.save(content);
            item.getContent().setId(content.getId());
            this.save(item);
        }
    }

    @Override
    public void saveOrUpdate(List<Item> items) {
        for(Item item:items){
            this.saveOrUpdate(item);
        }
    }

    @Override
    public void save(Item item)  {

        String sql="INSERT INTO item(ID,GUID,GUIDHASH,CONTENTID,URL,TITLE,UPDATED,LR_SJ,XG_SJ) " +
                  " VALUES(:ID,:GUID,:GUIDHASH,:CONTENTID,:URL,:TITLE, " +
                  " to_timestamp(:UPDATED,'yyyy-mm-dd hh24:mi:ss'), " +
                  " to_timestamp(:LR_SJ,'yyyy-mm-dd hh24:mi:ss'), " +
                  " to_timestamp(:XG_SJ,'yyyy-mm-dd hh24:mi:ss') ) ";
        HashMap<String,Object> paramMap= Maps.newHashMap();
        paramMap.put("ID", FENETKeyGenerator.getKey());
        paramMap.put("GUID",item.getGuid());
        paramMap.put("GUIDHASH", DigestUtils.md5Hex(item.getGuid()));
        paramMap.put("URL",item.getUrl());
        paramMap.put("TITLE",item.getTitle());
        paramMap.put("CONTENTID",item.getContent().getId());
        paramMap.put("UPDATED", item.getUpdated().toString("yyyy-MM-dd HH:mm:ss"));
        paramMap.put("XG_SJ", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        paramMap.put("LR_SJ", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        jdbcTemplate.update(sql, paramMap);

    }

    @Override
    public void save(List<Item> items) throws NoSuchAlgorithmException {
        for(Item item:items){
            this.save(item);
        }
    }

    @Override
    public void update(Item item) {

        String sql="update item set TITLE=:TITLE,URL=:URL , " +
                " XG_SJ=to_timestamp(:XG_SJ,'yyyy-mm-dd hh24:mi:ss'), " +
                " UPDATED=to_timestamp(:UPDATED,'yyyy-mm-dd hh24:mi:ss')  " +
                " where ID=:ID ";
        HashMap<String,Object> paramMap= Maps.newHashMap();
        paramMap.put("ID", item.getId());
        paramMap.put("URL",item.getUrl());
        paramMap.put("TITLE",item.getTitle());
        paramMap.put("UPDATED", item.getUpdated().toString("yyyy-MM-dd HH:mm:ss"));
        paramMap.put("XG_SJ", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        jdbcTemplate.update(sql,paramMap);

    }

    @Override
    public void del(String itemId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Item fecthByURI(String guid) {
        Item item=null;
        String sql="SELECT item.ID,item.GUID,item.GUIDHASH,item.CONTENTID, " +
                "   item.URL,item.TITLE,item.LR_SJ,item.XG_SJ " +
                " FROM item " +
                " WHERE item.GUIDHASH=:GUIDHASH ";
        Map<String,Object> paramMap=Maps.newHashMap();
        paramMap.put("GUIDHASH",DigestUtils.md5Hex(guid));
        try{
            item=(Item)jdbcTemplate.queryForObject(sql,paramMap,new ItemRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
        return item;
    }


    public Item fecth(String URI) {
        Item item=null;
        String sql="SELECT item.ID,item.GUID,item.GUIDHASH,item.CONTENTID, " +
                "   item.URL,item.TITLE,item.LR_SJ,item.XG_SJ " +
                " FROM item " +
                " WHERE item.GUIDHASH=:GUIDHASH ";
        Map<String,Object> paramMap=Maps.newHashMap();
        paramMap.put("GUIDHASH",DigestUtils.md5Hex(URI));
        try{
            item=(Item)jdbcTemplate.queryForObject(sql,paramMap,new ItemRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }

        return item;
    }


    class ItemRowMapper implements RowMapper{
        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item1=new Item();
            item1.setId(rs.getString("ID"));
            item1.setGuid(rs.getString("GUID"));
            item1.setGuidHash(rs.getString("GUIDHASH"));
            item1.setUrl(rs.getString("URL"));
            item1.setTitle(rs.getString("TITLE"));
            item1.setLR_SJ(new DateTime(rs.getDate("LR_SJ")));
            item1.setXG_SJ(new DateTime(rs.getDate("XG_SJ")));
            ItemContent itemContent=new ItemContent();
            itemContent.setId(rs.getString("CONTENTID"));
            item1.setContent(itemContent);
            return item1;
        }
    }
}
