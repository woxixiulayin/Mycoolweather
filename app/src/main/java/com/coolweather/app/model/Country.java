package com.coolweather.app.model;

/**
 * Created by Jackson on 2/4/16.
 */
public class Country {
    private int id;
    private String countryname;
    private String countrycode;
    private int cityid;

    public int getId() {
        return id;
    }

    public String getCountryname() {
        return countryname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public int getCityid() {
        return cityid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

}
