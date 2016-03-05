package com.coolweather.app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.coolweather.app.R;
import com.coolweather.app.fragment.ChooseAreaFragment;

public class MainContainerActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        if (savedInstanceState != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ChooseAreaFragment())
                    .commit();
        }
    }
}
