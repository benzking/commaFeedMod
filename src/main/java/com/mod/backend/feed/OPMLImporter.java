package com.mod.backend.feed;


import com.mod.backend.dao.SubscCategoryDao;
import com.mod.backend.model.SubscCategory;
import com.mod.backend.model.User;
import com.mod.backend.service.SubscriptionService;
import com.sun.syndication.feed.opml.Opml;
import com.sun.syndication.feed.opml.Outline;
import com.sun.syndication.io.WireFeedInput;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.StringReader;
import java.util.List;

@Service
public class OPMLImporter {
    @Autowired
    @Qualifier("subscCategoryDaoImp")
    private SubscCategoryDao subscCategoryDao;

    @Autowired
    @Qualifier("subscriptionService")
    private SubscriptionService subscriptionService;

	public void importOpml(User user, String xml) {

		WireFeedInput input = new WireFeedInput();

		try {
            System.out.println("1");
			Opml feed = (Opml) input.build(new File("E:\\Downloads\\subscriptions.xml"));
            System.out.println(feed);
			List<Outline> outlines = (List<Outline>) feed.getOutlines();
            System.out.println(outlines);
			for (Outline outline : outlines) {
                System.out.println(outline);
				handleOutline(user, outline, null);
			}
		} catch (Exception e) {
            e.printStackTrace();
		}

	}


	private void handleOutline(User user, Outline outline, SubscCategory parent) {
        System.out.println(outline);
		if (StringUtils.isEmpty(outline.getType())) {
			SubscCategory category = subscCategoryDao.fecth(user.getId()
                    ,outline.getText(), parent);
			if (category == null) {
				category = new SubscCategory();
				category.setName(outline.getText());
				category.setParaent(parent);
				category.setUser(user);
                subscCategoryDao.save(category);
			}

			List<Outline> children = outline.getChildren();
			for (Outline child : children) {
				handleOutline(user, child, category);
			}
		} else {
            System.out.println(user.getId()+"**"+outline.getXmlUrl()
                    +"**"+outline.getText()+"**"+parent);
            subscriptionService.subscribe(user.getId(),outline.getXmlUrl()
                    ,outline.getText(),parent);
		}
	}
}
