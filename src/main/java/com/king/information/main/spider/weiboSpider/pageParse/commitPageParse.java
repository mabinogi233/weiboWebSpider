package com.king.information.main.spider.weiboSpider.pageParse;

import com.king.information.main.spider.pageParse;
import com.king.information.main.spider.regexUtils.regex_find_match;
import com.king.information.main.spider.weiboSpider.entry.WeiboComment;
import com.king.information.main.spider.weiboSpider.entry.WeiboMessage;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class commitPageParse implements pageParse {
    @Override
    public void Parse(Page page) {
        //处理一条带评论的微博页面
        String test = page.getHtml().xpath("body/div/div/span[@class=\"ctt\"]").regex("<span[^>]*>([\\s\\S]*)</").get();

        String m_id = "M_"+ regex_find_match.find_first_match(
                "https://weibo.cn/comment/([^?]*)",
                page.getUrl().get(),
                1);
        //System.out.println(m_id);

        //内容
        Pattern pattern_test = Pattern.compile("(<a[^>]*>)");
        Matcher matcher_test = pattern_test.matcher(test);
        if(matcher_test.find()){
            test = matcher_test.replaceAll("");
        }
        test = test.replace("</a>","");
        //System.out.println(test);

        WeiboMessage message = new WeiboMessage();
        message.setId(m_id);
        message.setContent(test);
        page.putField("message_only_content",message);


        //爬取评论
        for(String pl :page.getHtml().xpath("body/div[@class=\"c\" and @id").regex("id=\"C_[\\d]*\">([\\s\\S]*)").all()) {
            String pl_text = "";
            String pl_userId = "";
            //爬取内容
            Pattern pattern_pl = Pattern.compile("<span class=\"ctt\">([\\s\\S]*?)</span>");
            Matcher matcher_pl = pattern_pl.matcher(pl);
            if (matcher_pl.find()) {
                pl_text = matcher_pl.group(1);
                //去除内容的<>标签
                Pattern pattern_pl_test = Pattern.compile("(<[^>]*>)");
                Matcher matcher_pl_test = pattern_pl_test.matcher(pl_text);
                if (matcher_pl_test.find()) {
                    pl_text = matcher_pl_test.replaceAll("");
                }
                pl_text = pl_text.replace("</[^>]*>", "");
                //输出评论内容
                //System.out.println(pl_text);
            }
            //爬取评论ID
            pl_userId = "https://weibo.cn/"+regex_find_match.find_first_match(
                    "<a href=\"([^\"]*?)\">",
                    pl,
                    1).replace("/u","");
            //System.out.println(pl_userId);

            WeiboComment comment = new WeiboComment();
            comment.setWeiboid(m_id);
            comment.setUserid(pl_userId);
            comment.setCommit(pl_text);
            page.putField("comment",comment);
        }
        //加入下一页
        //获取上一页和下一页的链接
        Selectable nextpage = page.getHtml().xpath("body/[@id=\"pagelist\"]/form/div/a");
        for(String str :nextpage.all()){
            if(str.matches("[\\s\\S]*下页[\\s\\S]*")){
                String next_page_url = page.getHtml().xpath("body/[@id=\"pagelist\"]/form/div/a[1]").links().get();
                //加入请求队列
                page.addTargetRequest(next_page_url);
                break;
            }
        }
    }
}
