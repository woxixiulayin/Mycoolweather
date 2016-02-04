package com.coolweather.app.model;

/**
 * Created by Jackson on 2/4/16.
 */
public class City {
    private int id;
    private String cityname;
    private String citycode;
    private int provinceid;

    public int getId() {
        return id;
    }

    public String getCityname() {
        return cityname;
    }

    public String getCitycode() {
        return citycode;
    }

    public int getProvinceid() {
        return provinceid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public void setProvinceid(int provinceid) {
        this.provinceid = provinceid;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }
}
