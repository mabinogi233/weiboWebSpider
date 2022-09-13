package com.king.information.main.spider.weiboSpider.entry;

public class WeiboUser {
    private String id;

    private String url;

    private String summary;

    private String signature;

    private String province;

    private String numTweets;

    private String numFollows;

    private String numFans;

    private String nickname;

    private String gender;

    private String birthday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getNumTweets() {
        return numTweets;
    }

    public void setNumTweets(String numTweets) {
        this.numTweets = numTweets == null ? null : numTweets.trim();
    }

    public String getNumFollows() {
        return numFollows;
    }

    public void setNumFollows(String numFollows) {
        this.numFollows = numFollows == null ? null : numFollows.trim();
    }

    public String getNumFans() {
        return numFans;
    }

    public void setNumFans(String numFans) {
        this.numFans = numFans == null ? null : numFans.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }
}