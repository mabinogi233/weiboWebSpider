package com.king.information.main.spider.weiboSpider.mapper;

import com.king.information.main.spider.weiboSpider.entry.WeiboUser;

public interface WeiboUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(WeiboUser record);

    int insertSelective(WeiboUser record);

    WeiboUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WeiboUser record);

    int updateByPrimaryKey(WeiboUser record);
}