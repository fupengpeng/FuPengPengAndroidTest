package com.fupengpeng.designmodel.singleton.learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fupengpeng.designmodel.R;

public class SingletonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);

        emperor();

    }
    @SuppressWarnings("all")
    private void emperor() {

        Emperor emperor001 = Emperor.getInstance();
        emperor001.emperorInfo();


        Emperor emperor002 = Emperor.getInstance();
        emperor001.emperorInfo();

        Emperor emperor003 = Emperor.getInstance();
        emperor001.emperorInfo();

        Emperor emperor004 = Emperor.getInstance();
        emperor001.emperorInfo();
    }
}
