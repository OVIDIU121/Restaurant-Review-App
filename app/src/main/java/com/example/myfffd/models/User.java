package com.example.myfffd.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The type User.
 */
public class User implements Parcelable {
    private String fn;
    private String sn;
    private String em;
    private String pw;
    private String auth_id;
    private String type;
    private String alias;
    private String profile_pic_url;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param fn              the fn
     * @param sn              the sn
     * @param em              the em
     * @param pw              the pw
     * @param auth_id         the auth id
     * @param alias           the alias
     * @param type            the type
     * @param profile_pic_url the profile pic url
     */
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

    /**
     * Instantiates a new User.
     *
     * @param in the in
     */
    protected User(Parcel in) {
        fn = in.readString();
        sn = in.readString();
        em = in.readString();
        pw = in.readString();
        auth_id = in.readString();
        type = in.readString();
        alias = in.readString();
        profile_pic_url = in.readString();
    }

    /**
     * The constant CREATOR.
     */
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     * Gets profile pic url.
     *
     * @return the profile pic url
     */
    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    /**
     * Sets profile pic url.
     *
     * @param profile_pic_url the profile pic url
     */
    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    /**
     * Gets auth id.
     *
     * @return the auth id
     */
    public String getAuth_id() {
        return auth_id;
    }

    /**
     * Sets auth id.
     *
     * @param auth_id the auth id
     */
    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }

    /**
     * Gets fn.
     *
     * @return the fn
     */
    public String getFn() {
        return fn;
    }

    /**
     * Sets fn.
     *
     * @param fn the fn
     */
    public void setFn(String fn) {
        this.fn = fn;
    }

    /**
     * Gets sn.
     *
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * Sets sn.
     *
     * @param sn the sn
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * Gets em.
     *
     * @return the em
     */
    public String getEm() {
        return em;
    }

    /**
     * Sets em.
     *
     * @param em the em
     */
    public void setEm(String em) {
        this.em = em;
    }

    /**
     * Gets pw.
     *
     * @return the pw
     */
    public String getPw() {
        return pw;
    }

    /**
     * Sets pw.
     *
     * @param pw the pw
     */
    public void setPw(String pw) {
        this.pw = pw;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets alias.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }
    //Needed for saving to the database

    /**
     * Sets alias.
     *
     * @param alias the alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fn);
        dest.writeString(sn);
        dest.writeString(em);
        dest.writeString(pw);
        dest.writeString(auth_id);
        dest.writeString(type);
        dest.writeString(alias);
        dest.writeString(profile_pic_url);
    }
}
