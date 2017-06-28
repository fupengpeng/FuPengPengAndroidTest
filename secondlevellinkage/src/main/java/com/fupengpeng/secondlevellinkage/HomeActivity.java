package com.fupengpeng.secondlevellinkage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fupengpeng.secondlevellinkage.bean.MenuData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = "HomeActivity";
    private static final int REQUEST_CODE = 100;//请求码

    @BindView(R.id.tv_activity_home_button)
    TextView tvActivityHomeButton;
    @BindView(R.id.tv_activity_home_classification)
    TextView tvActivityHomeClassification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_activity_home_button)
    public void onViewClicked() {

        //第一个选择
        tvActivityHomeButton = (TextView) findViewById(R.id.tv_activity_home_button);
        tvActivityHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: "+"001" );
                startActivityForResult(new Intent(HomeActivity.this, SecondLevelLinkageActivity.class),REQUEST_CODE);
            }
        });

    }

    //跳转回掉
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE:
                if (data !=null){
                    Log.e(TAG, "onActivityResult: "+"接收选择的返回值，并设置" );
                    MenuData menuData= (MenuData) data.getSerializableExtra("menu");
                    if (menuData!=null)
                        tvActivityHomeClassification.setText(menuData.name);
                }
                break;
            default:

        }
    }
}
