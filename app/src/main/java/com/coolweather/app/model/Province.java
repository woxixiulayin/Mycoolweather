package com.coolweather.app.model;

/**
 * Created by Jackson on 2/4/16.
 */
public class Province {
    private  int id;
    private String provincename;
    private String provincecode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getProvincename(){
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode;
    }
}
