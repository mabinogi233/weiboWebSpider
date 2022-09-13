package com.king.information.main.spider.weiboSpider;

import com.king.information.main.spider.regexUtils.regex_find_match;
import com.king.information.main.spider.weiboSpider.entry.WeiboComment;
import com.king.information.main.spider.weiboSpider.entry.WeiboMessage;
import com.king.information.main.spider.weiboSpider.entry.WeiboUser;
import com.king.information.main.spider.weiboSpider.mapper.WeiboMessageMapper;
import com.king.information.main.spider.weiboSpider.pageParse.attentionPageParse;
import com.king.information.main.spider.weiboSpider.pageParse.commitPageParse;
import com.king.information.main.spider.weiboSpider.pageParse.userPageParse;
import com.king.information.main.spider.weiboSpider.pageParse.userWeiboPageParse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WeiboSpider implements PageProcessor {
    //配置文件


    private Site site = Site.me();



    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().toString());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(page.getUrl().regex("https://weibo.cn/comment/[\\s\\S]*").get()!=null){
            new commitPageParse().Parse(page);
        }else if(page.getUrl().regex("https://weibo.cn/[\\s\\S]*/info").get()!=null){
            new userPageParse().Parse(page);
        }else if(page.getUrl().regex("https://weibo.cn/[\\s\\S]*/follow").get()!=null){
            new attentionPageParse().Parse(page);
        } else if(page.getUrl().regex("https://weibo.cn/[^/]*").get()!=null) {
            new userWeiboPageParse().Parse(page);
        }
    }


    @Override
    public Site getSite() {
        site.setDomain("weibo.cn");
        site.addCookie("_T_WM","");
        site.addCookie("SUB","");
        site.addCookie("SUBP","");
        site.addCookie("SSOLoginState","");
        //site.addCookie("ALF","");
        //site.addCookie("SCF","");
        return site;
    }

    public static void main(String[] args){


        Spider.create(new WeiboSpider())
            .addPipeline(new daoPipeline())
            .addUrl("https://weibo.cn/7141087529")
                .thread(1).run();


    }

}
