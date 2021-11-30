package com.example.myfffd.models;

public class Post {

    private String title;
    private String desc;
    private String image;
    private String timestamp;
    private String userid;
    private String alias;

    public Post() {
    }

    public Post(final String title, final String desc, final String image, final String timestamp, final String userid, final String alias) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.timestamp = timestamp;
        this.userid = userid;
        this.alias = alias;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(final String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(final String userid) {
        this.userid = userid;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(final String alias) {
        this.alias = alias;
    }
}