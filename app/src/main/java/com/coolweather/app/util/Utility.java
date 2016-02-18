package com.coolweather.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.Country;
import com.coolweather.app.model.Province;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jackson on 2/4/16.
 */
public class Utility {
    /*解析处理服务器返回的省级数据*/
    public static boolean handleProvinceResponse(CoolWeatherDB coolWeatherDB, String response){
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0){
                for(String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvincecode(array[0]);
                    province.setProvincename(array[1]);
                    coolWeatherDB.saveprovince(province);
                }
                return true;
            }
        }
        return false;
    }

    /*解析处理服务器返回的市级数据*/
    public static boolean handleCityResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId){
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0){
                for(String p : allCities) {
                    String[] array = p.split("\\|");
                    City c = new City();
                    c.setCitycode(array[0]);
                    c.setCityname(array[1]);
                    c.setProvinceid(provinceId);
                    coolWeatherDB.savecity(c);
                }
                return true;
            }
        }
        return false;
    }

    /*解析处理服务器返回的县级数据*/
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] allCountries = response.split(",");
            if(allCountries != null && allCountries.length > 0) {
                for (String c : allCountries) {
                    String[] array = c.split("\\|");
                    Country country = new Country();
                    country.setCountrycode(array[0]);
                    country.setCountryname(array[1]);
                    country.setCityid(cityId);
                    Log.d("Mylog", country.getCountryname());
                    coolWeatherDB.savecountry(country);
                }
                return true;
            }
        }
        return false;
    }

    /*解析服务器返回的天气数据并存储在本地*/
    public static void handleWeatherresponse(Context context, String response) {
        try {
            Log.d("mylog", response.toString());
            JSONObject jsonObject = new JSONObject(response);
            JSONObject weatherInfo = jsonObject.getJSONObject("weatherinfo");
            String cityName = weatherInfo.getString("city");
            String weatherCode = weatherInfo.getString("cityid");
            String temp1 = weatherInfo.getString("temp1");
            String temp2 = weatherInfo.getString("temp2");
            String weatherDesp = weatherInfo.getString("weather");
            String publishtime = weatherInfo.getString("ptime");
            saveWeatherInfo(context, cityName, weatherCode, temp1, temp2, weatherDesp, publishtime);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void saveWeatherInfo(Context context, String cityname, String weathercode, String temp1,
                                       String temp2,String weatherdesp, String publishtime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("city_selected", true);
        editor.putString("cityName", cityname);
        editor.putString("weatherCode", weathercode);
        editor.putString("temp1", temp1);
        editor.putString("temp2", temp2);
        editor.putString("weatherDesp", weatherdesp);
        editor.putString("publishTime", publishtime);
        editor.putString("current_date", sdf.format(new Date()));
        editor.commit();
    }
}
