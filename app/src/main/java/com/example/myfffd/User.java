package com.example.myfffd;

public class User {
    private String fn,sn,em,pw;

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

    //Needed for saving to the database
    public User(String fn, String sn, String em, String pw)
    {
        this.fn = fn;
        this.sn = sn;
        this.em = em;
        this.pw = pw;

    }
    public User()
    {

    }

}
