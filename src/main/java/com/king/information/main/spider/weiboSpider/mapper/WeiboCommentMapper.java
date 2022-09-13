package com.king.information.main.spider.weiboSpider.mapper;

import com.king.information.main.spider.weiboSpider.entry.WeiboComment;

public interface WeiboCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(WeiboComment record);

    int insertSelective(WeiboComment record);

    WeiboComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WeiboComment record);

    int updateByPrimaryKey(WeiboComment record);
}