package com.king.information.main.spider.regexUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex_find_match {
    //返回所有匹配的字符串
    public static List<String> find_all_match(String regex,String input,int group_id){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> list = new ArrayList<String>();
        while(matcher.find()){
            list.add(matcher.group(group_id));
        }
        return list;
    }
    public static String find_first_match(String regex,String input,int group_id){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> list = new ArrayList<String>();
        while(matcher.find()){
            list.add(matcher.group(group_id));
        }
        if(list.size()>0) {
            return list.get(0);
        }else{
            return "";
        }
    }
    public static String find_last_match(String regex,String input,int group_id){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String s = "";
        while(matcher.find()){
            s = matcher.group(group_id);
        }
        return s;
    }
}
