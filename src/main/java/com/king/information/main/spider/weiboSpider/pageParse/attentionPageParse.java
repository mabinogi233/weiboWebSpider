package com.king.information.main.spider.weiboSpider.pageParse;

import com.king.information.main.spider.pageParse;
import us.codecraft.webmagic.Page;

import java.util.HashSet;
import java.util.Set;

public class attentionPageParse implements pageParse {

    @Override
    public void Parse(Page page) {
        //处理关注界面
        Set<String> set = new HashSet<String>(page.getHtml().links().all());
        for(String s:set){
            if(s.matches("https://weibo.cn/[^/?]*")) {
                //System.out.println(s);
                page.addTargetRequest(s);
            }
        }
        // /html/body/div[@class=\"pa\"]/form/div/a[1]
        String next_page_url =
                page.getHtml().xpath("/html/body/div[@class=\"pa\"]/form/div/a[1]").links().get();
        if(page.getHtml().xpath("/html/body/div[@class=\"pa\"]/form/div").get().matches("[\\s\\S]*下页[\\s\\S]*")){
            //System.out.println(next_page_url);
            page.addTargetRequest(next_page_url);
        }
    }
}
