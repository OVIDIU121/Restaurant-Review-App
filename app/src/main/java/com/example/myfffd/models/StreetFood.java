package com.example.myfffd.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * The type Street food.
 */
public class StreetFood implements Parcelable {

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<StreetFood> CREATOR = new Parcelable.Creator<StreetFood>() {
        @Override
        public StreetFood createFromParcel(Parcel in) {
            return new StreetFood(in);
        }

        @Override
        public StreetFood[] newArray(int size) {
            return new StreetFood[size];
        }
    };
    private String name;
    private String location;
    private String profile_picture;
    private String description;
    private boolean vegetarian;
    private float rating;
    private Map<String, String> review;

    /**
     * Instantiates a new Street food.
     */
    public StreetFood() {
    }


    /**
     * Instantiates a new Street food.
     *
     * @param name            the name
     * @param location        the location
     * @param profile_picture the profile picture
     * @param description     the description
     * @param vegetarian      the vegetarian
     * @param rating          the rating
     * @param review          the review
     */
    public StreetFood(String name, String location, String profile_picture, String description, boolean vegetarian, float rating, Map<String, String> review) {
        this.name = name;
        this.location = location;
        this.profile_picture = profile_picture;
        this.description = description;
        this.vegetarian = vegetarian;
        this.rating = rating;
        this.review = review;
    }

    /**
     * Instantiates a new Street food.
     *
     * @param in the in
     */
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
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
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
     * Is vegetarian boolean.
     *
     * @return the boolean
     */
    public boolean isVegetarian() {
        return vegetarian;
    }

    /**
     * Sets vegetarian.
     *
     * @param vegetarian the vegetarian
     */
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
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
