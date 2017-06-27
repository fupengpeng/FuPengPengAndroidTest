package com.fupengpeng.viewpager.viewpager003;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.fupengpeng.viewpager.R;
import com.fupengpeng.viewpager.viewpager001.fragment.VpSimpleFragment;
import com.fupengpeng.viewpager.viewpager001.view.ViewPagerIndicator;
import com.fupengpeng.viewpager.viewpager003.fragment.ClassifyFragment;
import com.fupengpeng.viewpager.viewpager003.fragment.HomeFragment;
import com.fupengpeng.viewpager.viewpager003.fragment.PersonCenterFragment;
import com.fupengpeng.viewpager.viewpager003.fragment.ShareFragment;
import com.fupengpeng.viewpager.viewpager003.fragment.ShoppingCartFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {


    @BindView(R.id.tv_title_activity_title)
    TextView tvTitleActivityTitle;

    @BindView(R.id.ll_activity_main_parent)
    LinearLayout llActivityMainParent;

    @BindView(R.id.iv_activity_main_home)
    ImageView ivActivityMainHome;
    @BindView(R.id.tv_activity_main_home)
    TextView tvActivityMainHome;
    @BindView(R.id.ll_activity_main_home)
    LinearLayout llActivityMainHome;

    @BindView(R.id.iv_activity_main_classify)
    ImageView ivActivityMainClassify;
    @BindView(R.id.tv_activity_main_classify)
    TextView tvActivityMainClassify;
    @BindView(R.id.ll_activity_main_classify)
    LinearLayout llActivityMainClassify;

    @BindView(R.id.iv_activity_main_shopping_cart)
    ImageView ivActivityMainShoppingCart;
    @BindView(R.id.tv_activity_main_shopping_cart)
    TextView tvActivityMainShoppingCart;
    @BindView(R.id.ll_activity_main_shopping_cart)
    LinearLayout llActivityMainShoppingCart;

    @BindView(R.id.iv_activity_main_share)
    ImageView ivActivityMainShare;
    @BindView(R.id.tv_activity_main_share)
    TextView tvActivityMainShare;
    @BindView(R.id.ll_activity_main_share)
    LinearLayout llActivityMainShare;

    @BindView(R.id.iv_activity_main_person_center)
    ImageView ivActivityMainPersonCenter;
    @BindView(R.id.tv_activity_main_person_center)
    TextView tvActivityMainPersonCenter;
    @BindView(R.id.ll_activity_main_person_center)
    LinearLayout llActivityMainPersonCenter;


    @BindView(R.id.iv_title_activity_right)
    ImageView ivTitleActivityRight;

    @BindView(R.id.vp_activity_main_parent)
    ViewPager vpActivityMainParent;


    /**
     * 主界面Fragment
     */
    private HomeFragment homeFragment;
    /**
     * 分类Fragment
     */
    private ClassifyFragment classifyFragment;
    /**
     * 分享Fragment
     */
    private ShareFragment shareFragment;
    /**
     * 购物车Fragment
     */
    private ShoppingCartFragment shoppingCartFragment;
    /**
     * 个人中心Fragment
     */
    private PersonCenterFragment personCenterFragment;

    private Intent intent;

    /**
     * 设置展示Fragment的参数
     */
    private int setFragment = 100;

    /**
     * 主界面
     */
    private static final int HOME = 1;
    /**
     * 分类
     */
    private static final int CLASSIFY = 2;
    /**
     * 分享
     */
    private static final int SHARE = 3;
    /**
     * 购物车
     */
    private static final int SHOPPING_CART = 4;
    /**
     * 个人中心
     */
    private static final int PERSON_CENTER = 5;


    /**
     * 用于对Fragment的管理
     */
    FragmentManager fragmentManager;
    FragmentTransaction transaction;


    private ViewPager mViewPager;

    private List<String> mTitles = Arrays.asList("短信","收藏","推荐");

    private List<Fragment> mContents = new ArrayList<Fragment>();

    private FragmentPagerAdapter mAdapter;


    public static final String TAG = "OrderCenterActivity";
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏默认actionBar

        setContentView(R.layout.activity_main);




        ButterKnife.bind(this);



        initViews();

        initDatas();

        //添加一个FragmentTransaction的实例
        fragmentManager = getSupportFragmentManager();
        // 开启一个Fragment事务
        transaction = fragmentManager.beginTransaction();

        mViewPager.setAdapter(mAdapter);

//        questionList();


    }


    @OnClick({R.id.ll_activity_main_home,
            R.id.ll_activity_main_classify,
            R.id.ll_activity_main_shopping_cart,
            R.id.ll_activity_main_share,
            R.id.ll_activity_main_person_center,
            R.id.iv_title_activity_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_activity_main_home:
                setTabSelection(1);
                break;
            case R.id.ll_activity_main_classify:
                setTabSelection(2);
                break;
            case R.id.ll_activity_main_share:
                setTabSelection(3);
                break;
            case R.id.ll_activity_main_shopping_cart:
                setTabSelection(4);
                break;
            case R.id.ll_activity_main_person_center:
                setTabSelection(5);
                break;
            case R.id.iv_title_activity_right:
                break;
        }
    }


    /**
     * 根据传入的index参数来设置选中的Fragment。
     */
    private void setTabSelection(int index) {
//        Log.e(TAG, "setTabSelection: " );
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        //添加一个FragmentTransaction的实例
        fragmentManager = getSupportFragmentManager();
        // 开启一个Fragment事务
        transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);

        switch (index) {
            /**
             * 首页
             */
            case HOME:
                tvActivityMainHome.setTextColor(0xffff0000);
                tvTitleActivityTitle.setText("首页");
                if (homeFragment == null) {
                    transaction.add(R.id.vp_activity_main_parent, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            /**
             * 分类界面
             */
            case CLASSIFY:
                tvActivityMainClassify.setTextColor(0xffff0000);
                tvTitleActivityTitle.setText("分类");
                if (classifyFragment == null) {
                    transaction.add(R.id.vp_activity_main_parent, classifyFragment);
                } else {
                    transaction.show(classifyFragment);
                }
                break;
            /**
             * 分享界面
             */
            case SHARE:
                tvTitleActivityTitle.setText("分享");
                tvActivityMainShare.setTextColor(0xffff0000);
                if (shareFragment == null) {
                    transaction.add(R.id.vp_activity_main_parent, shareFragment);
                } else {
                    transaction.show(shareFragment);
                }
                break;
            /**
             * 购物车界面
             */
            case SHOPPING_CART:
                tvTitleActivityTitle.setText("购物车");
                tvActivityMainShoppingCart.setTextColor(0xffff0000);
                if (shoppingCartFragment == null) {
                    transaction.add(R.id.vp_activity_main_parent, shoppingCartFragment);
                } else {
                    transaction.show(shoppingCartFragment);
                }
                break;
            /**
             * 个人中心界面
             */
            case PERSON_CENTER:
                tvTitleActivityTitle.setText("个人中心");
                tvActivityMainPersonCenter.setTextColor(0xffff0000);
                if (personCenterFragment == null) {

                    transaction.add(R.id.vp_activity_main_parent, personCenterFragment);
                } else {
                    transaction.show(personCenterFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。（字体颜色和view背景颜色）
     */
    private void clearSelection() {
        tvActivityMainHome.setTextColor(0xff000000);

        tvActivityMainClassify.setTextColor(0xff000000);

        tvActivityMainShare.setTextColor(0xff000000);

        tvActivityMainShoppingCart.setTextColor(0xff000000);

        tvActivityMainPersonCenter.setTextColor(0xff000000);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     * 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
//        Log.e(TAG, "hideFragments: " );
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (classifyFragment != null) {
            transaction.hide(classifyFragment);
        }
        if (shareFragment != null) {
            transaction.hide(shareFragment);
        }
        if (shoppingCartFragment != null) {
            transaction.hide(shoppingCartFragment);
        }
        if (personCenterFragment != null) {
            transaction.hide(personCenterFragment);
        }
    }

    /**
     * 初始化展示和点击筛选之后的fragment展示
     */
    private void questionList() {
        Intent intent = this.getIntent();
        setFragment = intent.getIntExtra("setFragment", 0);
        Log.e(TAG, "questionList: --------" + setFragment);
        switch (setFragment) {
            /**
             * 首页界面
             */
            case 100:
                setTabSelection(HOME);
                break;
            /**
             * 分类界面
             */
            case 200:
                setTabSelection(CLASSIFY);
                break;
            /**
             * 分享界面
             */
            case 300:
                setTabSelection(SHARE);
                break;
            /**
             * 购物车界面
             */
            case 400:
                setTabSelection(SHOPPING_CART);
                break;
            /**
             * 个人中心界面
             */
            case 500:
                setTabSelection(PERSON_CENTER);
                break;
            /**
             * 默认首页界面
             */
            default:
                setTabSelection(HOME);
                break;
        }
    }




    private void initDatas() {
        personCenterFragment = new PersonCenterFragment();
        homeFragment = new HomeFragment();
        classifyFragment  = new ClassifyFragment();
        shareFragment = new ShareFragment();
        shoppingCartFragment = new ShoppingCartFragment();

        mContents.add(homeFragment);
        mContents.add(classifyFragment);
        mContents.add(shareFragment);
        mContents.add(shoppingCartFragment);
        mContents.add(personCenterFragment);

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
    }




}
