package com.example.myfffd.models;

public class Restaurant {

    private String name;
    private String city;
    private String street;
    private String tel;
    private String postcode;
    private String profile_picture;
    private String description;
    private int rating;

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public Restaurant() {    }

    public Restaurant(String name, String city, String street, String tel, String postcode, String profile_picture, String description, int rating) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.tel = tel;
        this.postcode = postcode;
        this.profile_picture = profile_picture;
        this.description = description;
        this.rating = rating;
    }


}
