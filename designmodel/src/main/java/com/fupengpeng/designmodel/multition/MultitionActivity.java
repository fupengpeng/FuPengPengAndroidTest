package com.fupengpeng.designmodel.multition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fupengpeng.designmodel.R;

public class MultitionActivity extends AppCompatActivity {
    public static final String TAG = "MultitionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multition);
        multition();
        
    }

    @SuppressWarnings("all")
    private void multition() {
        int ministerNum =10; //10个大臣
        for(int i=0;i<ministerNum;i++){
            Emperor emperor = Emperor.getInstance();
            Log.e(TAG, "multition: "+ "第"+(i+1)+"个大臣参拜的是：");

            emperor.emperorInfo();
        }
    }
}
