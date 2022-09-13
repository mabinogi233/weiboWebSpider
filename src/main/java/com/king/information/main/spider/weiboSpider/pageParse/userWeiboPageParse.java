package com.king.information.main.spider.weiboSpider.pageParse;

import com.king.information.main.spider.pageParse;
import com.king.information.main.spider.regexUtils.regex_find_match;
import com.king.information.main.spider.weiboSpider.entry.WeiboMessage;
import com.king.information.main.spider.weiboSpider.entry.WeiboUser;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class userWeiboPageParse implements pageParse {
    @Override
    public void Parse(Page page) {
        //解析该用户所发的微博
        //获取用户id ， 关注数 粉丝数 微博数
        String uid = page.getHtml().xpath("body/div[@class=\"u\"]/table/tbody/tr/td[2]/div[@class=\"ut\"]").regex("href=\"/([^/]*?)/info").get();
        String like = page.getHtml().xpath("body/div[@class=\"u\"]/div[@class=\"tip2\"]").regex("关注\\[(\\d+)\\]").get();
        String fans = page.getHtml().xpath("body/div[@class=\"u\"]/div[@class=\"tip2\"]").regex("粉丝\\[(\\d+)\\]").get();;
        String conut = page.getHtml().xpath("body/div[@class=\"u\"]/div[@class=\"tip2\"]").regex("微博\\[(\\d+)\\]").get();;
        //System.out.println(uid);
        //System.out.println(like);
        //System.out.println(fans);
        //System.out.println(conut);
        WeiboUser user = new WeiboUser();
        user.setId(uid);
        user.setNumFans(fans);
        user.setNumFollows(like);
        user.setNumTweets(conut);
        page.putField("user_only_num",user);

        //获取关注链接
        for(String like_url : page.getHtml().xpath("body/div[@class=\"u\"]/div[@class=\"tip2\"]").links().all()) {
            if(like_url.matches("[\\s\\S]*/follow")) {
                //System.out.println(like_url);
                page.addTargetRequest(like_url);
            }
        }
        //
        //获取资料链接
        for(String rou_url : page.getHtml().xpath("body/div[@class=\"u\"]/table/tbody/tr/td[2]/div[@class=\"ut\"]").links().all()) {
            if(rou_url.matches("[\\s\\S]*/info")) {
                //System.out.println(rou_url);
                page.addTargetRequest(rou_url);
            }
        }


        //获取全部的评论链接（微博内容详情）
        Selectable page_message = page.getHtml().xpath("body/div[@class=\"c\" and @id]/div/a");
        page_message = page_message.links().regex("https://weibo.cn/comment/[\\s\\S]*");
        //加入评论页面

        page.addTargetRequests(page_message.all());

        //解析页面
        Selectable pages = page.getHtml().xpath("body/div[@class=\"c\" and @id]");
        for (String one_message : pages.all()) {
            WeiboMessage message = new WeiboMessage();
            //找到微博ID
            String _id = regex_find_match.find_first_match(
                    "[\\s\\S]*id=\"([^\"]*)\"[\\s\\S]*",
                    one_message,
                    1);
            //System.out.println(_id);

            //微博被评论的数量。
            String comment = regex_find_match.find_first_match(
                    "评论\\[(\\d+)\\]",
                    one_message,
                    1);
            //System.out.println(comment);

            //微博的内容。
            String Content = "";
            //暂时不需要，（存在全文选项）

            //用户ID。
            //
            String userID = page.getHtml().xpath("body/div[@class=\"u\"]/table/tbody/tr/td[2]/div[@class=\"ut\"]").regex("href=\"/([^/]*?)/info").get();
            //System.out.println(userID);

            //微博被点赞的数量。
            String Like = regex_find_match.find_first_match(
                    "赞\\[(\\d+)\\]",
                    one_message,
                    1);
            //System.out.println(Like);

            //微博发表时间。
            String PubTime = "";
            //发微博的工具（手机类型或者平台）
            String Tools = "";
            Pattern pattern7 = Pattern.compile("<span class=\"ct\">([\\s\\S]*)&nbsp;([\\s\\S]*)</span>");
            Matcher matcher7 = pattern7.matcher(one_message);
            while (matcher7.find()) {
                PubTime = matcher7.group(1);
                Calendar now = Calendar.getInstance();
                PubTime = PubTime.replace("今天",Integer.toString(now.get(Calendar.MONTH) + 1)+"-"+ Integer.toString(now.get(Calendar.DAY_OF_MONTH)));
                if(!PubTime.matches("[\\d]+-[\\d]+-[\\d]+")) {
                    PubTime = Integer.toString(now.get(Calendar.YEAR)) +"-"+ PubTime;
                }
                //System.out.println(PubTime);
                Tools = matcher7.group(2);
                Tools = Tools.replace("来自","");
                //System.out.println(Tools);
                break;
            }

            //微博被转发的数量。
            String Transfer = regex_find_match.find_first_match(
                    "转发\\[(\\d+)\\]",
                    one_message,
                    1);
            //System.out.println(Transfer);

            //微博URL
            String Url = "https://weibo.cn/comment/" +
                    regex_find_match.find_first_match(
                            "https://weibo.cn/comment/([^?]*)[?>;]",
                            one_message,
                            1);
            //System.out.println(Url);

            message.setId(_id);
            message.setComment(comment);
            message.setLike(like);
            message.setPubtime(PubTime);
            message.setTools(Tools);
            message.setTransfer(Transfer);
            message.setUrl(Url);
            message.setUserid(userID);
            message.setContent(Content);

            page.putField("message",message);
        }
        //加入下一页
        //获取上一页和下一页的链接
        Selectable nextpage = page.getHtml().xpath("body/[@id=\"pagelist\"]/form/div/a");
        for(String str :nextpage.all()){
            if(str.matches("[\\s\\S]*下页[\\s\\S]*")){
                String next_page_url = "https://weibo.cn" + str.split("\"")[1];
                //加入请求队列
                page.addTargetRequest(next_page_url);
                break;
            }
        }
    }
}
