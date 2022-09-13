package com.king.information.main.spider.weiboSpider.pageParse;

import com.king.information.main.spider.pageParse;
import com.king.information.main.spider.regexUtils.regex_find_match;
import com.king.information.main.spider.weiboSpider.entry.WeiboUser;
import us.codecraft.webmagic.Page;

public class userPageParse implements pageParse {

    @Override
    public void Parse(Page page) {
        //处理用户微博
        String userInformation = page.getHtml().xpath("body/div[7]").get();

        String _id = regex_find_match.find_first_match("/([^/]*?)/info",page.getUrl().get(),1);
        String Birthday= "";//出生日期。
        Birthday = regex_find_match.find_last_match("生日:([^\"]*?)<",userInformation,1);
        String City= "";//所在城市。

        String Gender= "";//性别。
        Gender = regex_find_match.find_last_match("性别:([^\"]*?)<",userInformation,1);

        String NickName= "";//微博昵称。
        NickName = regex_find_match.find_last_match("昵称:([^\"]*?)<",userInformation,1);

        String Num_Fans= "";//粉丝数量。

        String Num_Follows= "";//关注数量。

        String Num_Tweets= "";//已发微博数量。

        String Province= "";//所在省份。
        Province = regex_find_match.find_last_match("地区:([^\"]*?)<",userInformation,1);
        String Signature= "";//认证
        Signature = regex_find_match.find_last_match("认证信息([^\"]*?)<",userInformation,1);
        String Summary= "";//简介。
        Summary = regex_find_match.find_last_match("简介:([^\"]*?)<",userInformation,1);
        String URL= "";//微博的个人首页。
        URL = page.getUrl().get().replace("/info","");

        WeiboUser user = new WeiboUser();
        user.setId(_id);
        user.setBirthday(Birthday);
        user.setGender(Gender);
        user.setNickname(NickName);
        user.setNumFans(Num_Fans);
        user.setNumFollows(Num_Follows);
        user.setNumTweets(Num_Tweets);
        user.setProvince(Province);
        user.setSignature(Signature);
        user.setSummary(Summary);
        user.setUrl(URL);

        page.putField("user",user);
    }
}
