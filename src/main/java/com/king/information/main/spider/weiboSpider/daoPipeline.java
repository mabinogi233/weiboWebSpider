package com.king.information.main.spider.weiboSpider;


import com.king.information.main.spider.weiboSpider.entry.WeiboComment;
import com.king.information.main.spider.weiboSpider.entry.WeiboMessage;
import com.king.information.main.spider.weiboSpider.entry.WeiboUser;
import com.king.information.main.spider.weiboSpider.mapper.WeiboCommentMapper;
import com.king.information.main.spider.weiboSpider.mapper.WeiboMessageMapper;
import com.king.information.main.spider.weiboSpider.mapper.WeiboUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class daoPipeline implements Pipeline{


    WeiboCommentMapper weiboCommentMapper;


    WeiboMessageMapper weiboMessageMapper;


    WeiboUserMapper weiboUserMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        WeiboUser user1 = (WeiboUser)resultItems.get("user");
        WeiboMessage message1 = (WeiboMessage)resultItems.get("message");
        WeiboComment comment1 = (WeiboComment)resultItems.get("comment");
        WeiboUser user2 = (WeiboUser)resultItems.get("user_only_num");
        WeiboMessage message2 = (WeiboMessage)resultItems.get("message_only_content");
        try {
            if (user1 != null) {
                System.out.println("一条个人信息 无数据");
                System.out.println(user1.getId());
                System.out.println(user1.getGender());
                System.out.println(user1.getNickname());
                System.out.println(user1.getBirthday());
                System.out.println(user1.getProvince());
                System.out.println(user1.getSignature());
                System.out.println(user1.getSummary());
                System.out.println(user1.getUrl());


            }
            if (user2 != null) {
                System.out.println("一条个人信息 只有数据");
                System.out.println(user2.getNumFans());
                System.out.println(user2.getNumFollows());
                System.out.println(user2.getNumTweets());

            }
            if (message1 != null) {
                System.out.println("一条微博，无内容");
                System.out.println(message1.getId());
                System.out.println(message1.getComment());
                System.out.println(message1.getLike());
                System.out.println(message1.getPubtime());
                System.out.println(message1.getTools());
                System.out.println(message1.getTransfer());
                System.out.println(message1.getUrl());
                System.out.println(message1.getUserid());

            }
            if (message2 != null) {
                System.out.println("一条微博，只有内容");
                System.out.println(message2.getId());
                System.out.println(message2.getContent());

            }
            if (comment1 != null) {
                System.out.println("一条评论");
                System.out.println(comment1.getWeiboid());
                System.out.println(comment1.getUserid());
                System.out.println(comment1.getCommit());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
