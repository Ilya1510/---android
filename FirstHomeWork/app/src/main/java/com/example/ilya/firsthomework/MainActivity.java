package com.example.ilya.firsthomework;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fm.beginTransaction().replace(R.id.fragment, new SimpleFragment(), SimpleFragment.TAG).commit();
        }
    }


    @Override
    public void onClick(View v) {
        System.out.println("onClick MainActivity");
    }
}
