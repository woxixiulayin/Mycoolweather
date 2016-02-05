package com.coolweather.app.util;

/**
 * Created by Jackson on 2/4/16.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
