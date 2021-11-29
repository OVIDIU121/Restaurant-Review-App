package com.example.myfffd.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class StreetFood implements Parcelable {

    private String name;
    private String location;
    private String profile_picture;
    private String description;
    private boolean vegetarian;
    private float rating;
    private Map<String, String> review;
    public StreetFood() {
    }

    public StreetFood(String name, String location, String profile_picture, String description, boolean vegetarian, float rating, Map<String, String> review) {
        this.name = name;
        this.location = location;
        this.profile_picture = profile_picture;
        this.description = description;
        this.vegetarian = vegetarian;
        this.rating = rating;
        this.review = review;
    }


    protected StreetFood(Parcel in) {
        name = in.readString();
        location = in.readString();
        profile_picture = in.readString();
        description = in.readString();
        vegetarian = in.readByte() != 0;
        rating = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(profile_picture);
        dest.writeString(description);
        dest.writeByte((byte) (vegetarian ? 1 : 0));
        dest.writeFloat(rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StreetFood> CREATOR = new Creator<StreetFood>() {
        @Override
        public StreetFood createFromParcel(Parcel in) {
            return new StreetFood(in);
        }

        @Override
        public StreetFood[] newArray(int size) {
            return new StreetFood[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
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
