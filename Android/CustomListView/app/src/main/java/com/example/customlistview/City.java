package com.example.customlistview;

public class City {

    private String cityName;
    private int cityImage;
    private String slogan;

    public City(String cityName, int cityImage, String slogan) {
        this.cityName = cityName;
        this.cityImage = cityImage;
        this.slogan = slogan;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCityImage() {
        return cityImage;
    }

    public String getSlogan() {
        return slogan;
    }
}
