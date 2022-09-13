package com.king.information.main.spider;


import com.king.information.main.spider.weiboSpider.mapper.WeiboMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {

    @Autowired
    WeiboMessageMapper mapper;

    @RequestMapping("/test")
    public void testx(){
        mapper.selectByPrimaryKey("maiwuaehfguisgb");

    }
}
