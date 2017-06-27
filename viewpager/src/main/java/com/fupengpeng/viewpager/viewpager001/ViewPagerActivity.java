package com.fupengpeng.viewpager.viewpager001;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.fupengpeng.viewpager.R;
import com.fupengpeng.viewpager.viewpager001.fragment.VpSimpleFragment;
import com.fupengpeng.viewpager.viewpager001.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewPagerActivity extends FragmentActivity {
    public static final String TAG = "ViewPagerActivity";

    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;

    private List<String> mTitles = Arrays.asList("短信","收藏","推荐");

    private List<VpSimpleFragment> mContents = new ArrayList<VpSimpleFragment>();

    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏默认actionBar
        setContentView(R.layout.activity_view_pager);

        initViews();

        initDatas();

        mViewPager.setAdapter(mAdapter);

//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.e(TAG, "onPageScrolled: "+"001--position="+position+"    positionOffset="+positionOffset
//                        +"       positionOffsetPixels="+positionOffsetPixels+"------------------");
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                Log.e(TAG, "onPageSelected: "+"002    position="+position +"------------------");
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                Log.e(TAG, "onPageScrollStateChanged: "+"003    state="+state+"------------------");
//            }
//        });
        
//        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                if (state == ViewPager.SCROLL_STATE_SETTLING){
//                    Log.e(TAG, "onPageScrollStateChanged: "+"state= "+state+"--------------" );
//                }
//            }
//        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, "onPageScrolled: "+"001--position="+position+"    positionOffset="+positionOffset
                +"       positionOffsetPixels="+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: "+"002    position="+position );
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e(TAG, "onPageScrollStateChanged: "+"003    state="+state );
            }
        });

    }


    private void initDatas() {

        for (String title: mTitles) {
            VpSimpleFragment fragment = VpSimpleFragment.newInstance(title);
            mContents.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mContents.get(position);
            }

            @Override
            public int getCount() {
                return mContents.size();
            }
        };

    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
    }
}
