package com.fupengpeng.drawerslide;

import android.media.Image;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import static android.R.interpolator.linear;
import static com.fupengpeng.drawerslide.R.id.iv_right;
import static com.fupengpeng.drawerslide.R.id.ll_right;

public class DrawerSlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_slide);

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        final LinearLayout linearLayoutLeft = (LinearLayout) findViewById(R.id.ll_left);
        final LinearLayout linearLayoutRight = (LinearLayout) findViewById(R.id.ll_right);
        ImageView imageViewLeft = (ImageView) findViewById(R.id.iv_left);
        ImageView imageViewRight = (ImageView) findViewById(iv_right);

        imageViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(linearLayoutLeft);
            }
        });

        imageViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(linearLayoutRight);
            }
        });
    }
}
