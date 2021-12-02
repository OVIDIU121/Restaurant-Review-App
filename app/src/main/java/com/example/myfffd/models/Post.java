package com.example.myfffd.models;

/**
 * The type Post.
 */
public class Post {

    private String title;
    private String desc;
    private String image;
    private String timestamp;
    private String userid;
    private String alias;

    /**
     * Instantiates a new Post.
     */
    public Post() {
    }

    /**
     * Instantiates a new Post.
     *
     * @param title     the title
     * @param desc      the desc
     * @param image     the image
     * @param timestamp the timestamp
     * @param userid    the userid
     * @param alias     the alias
     */
    public Post(final String title, final String desc, final String image, final String timestamp, final String userid, final String alias) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.timestamp = timestamp;
        this.userid = userid;
        this.alias = alias;
    }


    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Sets desc.
     *
     * @param desc the desc
     */
    public void setDesc(final String desc) {
        this.desc = desc;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(final String image) {
        this.image = image;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public String getTimestamp() {
        return this.timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets userid.
     *
     * @return the userid
     */
    public String getUserid() {
        return this.userid;
    }

    /**
     * Sets userid.
     *
     * @param userid the userid
     */
    public void setUserid(final String userid) {
        this.userid = userid;
    }

    /**
     * Gets alias.
     *
     * @return the alias
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * Sets alias.
     *
     * @param alias the alias
     */
    public void setAlias(final String alias) {
        this.alias = alias;
    }
}