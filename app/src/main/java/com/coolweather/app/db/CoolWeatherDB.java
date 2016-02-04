package com.coolweather.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.app.model.City;
import com.coolweather.app.model.Country;
import com.coolweather.app.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jackson on 2/4/16.
 */
public class CoolWeatherDB {
    /*数据库名*/
    public static final String DB_NAME = "cool_weather";

    /*数据库版本*/
    public static final int VERSION = 1;

    private static CoolWeatherDB coolweatherDB;

    private SQLiteDatabase db;

    /*将构造方法私有化*/
    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /*获取CoolweatherDB的实例*/
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolweatherDB == null) {
            coolweatherDB = new CoolWeatherDB(context);
        }
        return coolweatherDB;
    }

    /*将province数据存储到数据库*/
    public void saveprovince(Province province) {
        if (province != null) {
            ContentValues val = new ContentValues();
            val.put("province_name", province.getProvincename());
            val.put("province_code", province.getProvincecode());
            db.insert("Province", null, val);
        }
    }

    /*从数据库读取全国所有省份的信息*/
    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("province", null, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvincename(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvincecode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return list;
    }

    /*将city数据存储到数据库*/
    public void savecity(City city) {
        if(city != null) {
            ContentValues val = new ContentValues();
            val.put("city_name", city.getCityname());
            val.put("city_code", city.getCitycode());
            val.put("province_id", city.getProvinceid());
            db.insert("City", null, val);
        }
    }

    /*从数据库读取某省下所有的城市*/
    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?", new String[] {String.valueOf(provinceId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
               City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityname(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCitycode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceid(provinceId);
                list.add(city);
            }while(cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return list;
    }

    /*将country数据存储到数据库*/
    public void savecountry(Country country) {
        if(country != null) {
            ContentValues val = new ContentValues();
            val.put("country_name", country.getCountryname());
            val.put("country_code", country.getCountrycode());
            val.put("city_id", country.getCityid());
            db.insert("Country", null, val);
        }
    }

    /*从数据库读取某省下所有的城市*/
    public List<Country> loadCountries(int cityId) {
        List<Country> list = new ArrayList<Country>();
        Cursor cursor = db.query("Country", null, "city_id = ?", new String[] {String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Country country = new Country();
                country.setId(cursor.getInt(cursor.getColumnIndex("id")));
                country.setCountryname(cursor.getString(cursor.getColumnIndex("Country_name")));
                country.setCountrycode(cursor.getString(cursor.getColumnIndex("Country_code")));
                country.setCityid(cityId);
                list.add(country);
            }while(cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return list;
    }

}
