package com.example.myfffd.models;

public class User {
    private String fn;
    private String sn;
    private String em;
    private String pw;
    private String auth_id;
    private String type;
    private String alias;
    private String profile_pic_url;

    public User() {
    }

    public User(String fn, String sn, String em, String pw, String auth_id, String alias, String type, String profile_pic_url) {
        this.fn = fn;
        this.sn = sn;
        this.em = em;
        this.pw = pw;
        this.auth_id = auth_id;
        this.alias = alias;
        this.type = type;
        this.profile_pic_url = profile_pic_url;

    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    public String getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlias() {
        return alias;
    }
    //Needed for saving to the database

    public void setAlias(String alias) {
        this.alias = alias;
    }


}
