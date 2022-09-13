package com.king.information.main.spider.weiboSpider.mapper;

import com.king.information.main.spider.weiboSpider.entry.WeiboMessage;

public interface WeiboMessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(WeiboMessage record);

    int insertSelective(WeiboMessage record);

    WeiboMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WeiboMessage record);

    int updateByPrimaryKey(WeiboMessage record);
}