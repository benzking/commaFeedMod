package com.mod.frontend.action;

import com.mod.backend.model.Item;
import com.mod.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-27
 * Time: 上午9:21
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/feeds")
public class ItemAction {
    @Resource
    private ItemService itemService;
    public void markRead(String cateId,String feedid){
//        itemService.markRead(userId,feedid);
    }
    public void read(String itemurl){

    }
    public void keepUnread(String itemurl){

    }
    public void starred(String itemurl){

    }
    @RequestMapping(value = "/register")
    public List<Item> query(String cateId,String feedid,String statusType,String pageNum){
        return itemService.query(cateId,feedid,statusType);
    }
}
