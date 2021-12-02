package com.example.myfffd.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * The type Restaurant.
 */
public class Restaurant implements Parcelable {

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {
        /**
         * @param in
         * @return
         */
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        /**
         * @param size
         * @return
         */
        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
    private String name;
    private String city;
    private String street;
    private String tel;
    private String postcode;
    private String profile_picture;
    private String description;
    private float rating;
    private Map<String, String> review;

    /**
     * Instantiates a new Restaurant.
     */
    public Restaurant() {
    }

    /**
     * Instantiates a new Restaurant.
     *
     * @param name            the name
     * @param city            the city
     * @param street          the street
     * @param tel             the tel
     * @param postcode        the postcode
     * @param profile_picture the profile picture
     * @param description     the description
     * @param rating          the rating
     * @param review          the review
     */
    public Restaurant(String name, String city, String street, String tel, String postcode, String profile_picture, String description, float rating, Map<String, String> review) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.tel = tel;
        this.postcode = postcode;
        this.profile_picture = profile_picture;
        this.description = description;
        this.rating = rating;
        this.review = review;
    }

    /**
     * Instantiates a new Restaurant.
     *
     * @param in the in
     */
    protected Restaurant(Parcel in) {
        name = in.readString();
        city = in.readString();
        street = in.readString();
        tel = in.readString();
        postcode = in.readString();
        profile_picture = in.readString();
        description = in.readString();
        rating = in.readFloat();
    }

    /**
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(city);
        dest.writeString(street);
        dest.writeString(tel);
        dest.writeString(postcode);
        dest.writeString(profile_picture);
        dest.writeString(description);
        dest.writeFloat(rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets tel.
     *
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * Sets tel.
     *
     * @param tel the tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Gets postcode.
     *
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets postcode.
     *
     * @param postcode the postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Gets profile picture.
     *
     * @return the profile picture
     */
    public String getProfile_picture() {
        return profile_picture;
    }

    /**
     * Sets profile picture.
     *
     * @param profile_picture the profile picture
     */
    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * Gets review.
     *
     * @return the review
     */
    public Map<String, String> getReview() {
        return review;
    }

    /**
     * Sets review.
     *
     * @param review the review
     */
    public void setReview(Map<String, String> review) {
        this.review = review;
    }
}

