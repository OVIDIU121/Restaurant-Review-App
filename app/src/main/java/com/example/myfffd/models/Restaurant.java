package com.example.myfffd.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class Restaurant implements Parcelable {

    private String name;
    private String city;
    private String street;
    private String tel;
    private String postcode;
    private String profile_picture;
    private String description;
    private float rating;
    private Map<String, String> review;

    public Restaurant() {
    }

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

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Map<String, String> getReview() {
        return review;
    }

    public void setReview(Map<String, String> review) {
        this.review = review;
    }
}

