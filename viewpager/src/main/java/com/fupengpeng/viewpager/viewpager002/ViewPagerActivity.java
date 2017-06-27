package com.fupengpeng.viewpager.viewpager002;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.fupengpeng.viewpager.R;
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

public class ViewPagerActivity extends AppCompatActivity {


    public static final String TAG = "ViewPagerActivity002";
    @BindView(R.id.tv_ershiwu)
    TextView tvErshiwu;
    @BindView(R.id.tv_ershiliu)
    TextView tvErshiliu;
    @BindView(R.id.tv_ershiqi)
    TextView tvErshiqi;
    @BindView(R.id.tv_ershiba)
    TextView tvErshiba;
    @BindView(R.id.tv_ershijiu)
    TextView tvErshijiu;


    private ViewPager mViewPager;


    private List<Fragment> mContents = new ArrayList<Fragment>();

    private List<TextView> mTextViews = new ArrayList<TextView>();

    private FragmentPagerAdapter mAdapter;
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

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏默认actionBar
        setContentView(R.layout.activity_view_pager2);
        ButterKnife.bind(this);

        initViews();

        initDatas();

        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, "onPageScrolled: " + "001--position=" + position + "    positionOffset=" + positionOffset
                        + "       positionOffsetPixels=" + positionOffsetPixels + "------------------");
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: " + "002    position=" + position + "------------------");
                pos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e(TAG, "onPageScrollStateChanged: " + "003    state=" + state + "------------------");
                if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    hideFontColor();
                    mTextViews.get(pos).setTextColor(0xffff0000);

                }
            }
        });


    }

    private void hideFontColor() {

        tvErshiwu.setTextColor(0xff000000);
        tvErshiliu.setTextColor(0xff000000);
        tvErshiqi.setTextColor(0xff000000);
        tvErshiba.setTextColor(0xff000000);
        tvErshijiu.setTextColor(0xff000000);

    }

    private void initDatas() {

        personCenterFragment = new PersonCenterFragment();
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        shareFragment = new ShareFragment();
        shoppingCartFragment = new ShoppingCartFragment();

        mContents.add(homeFragment);
        mContents.add(classifyFragment);
        mContents.add(shareFragment);
        mContents.add(shoppingCartFragment);
        mContents.add(personCenterFragment);

        mTextViews.add(tvErshiwu);
        mTextViews.add(tvErshiliu);
        mTextViews.add(tvErshiqi);
        mTextViews.add(tvErshiba);
        mTextViews.add(tvErshijiu);

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





//    /**
//     * 图片资源id
//     */
//    public int[]mPicture={R.drawable.adware_style_applist,R.drawable.adware_style_banner,R.drawable.adware_style_creditswall};
//    ImageView mTvFristPoint;//第一个点
//    ImageView mTvSecondPoint;//第二个点
//    ImageView mTvThridPoint;//第三个点
//    Button mBtnPass;
//    ImageView []mTvArray=new ImageView[3];
//    String PREFRENCE_NAME="prefrence_setting";//SharedPreferences方法参数
//    SharedPreferences preference;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onCreate(savedInstanceState);
//        /**
//         * 1.找到控件
//         * 2.数据源
//         * 3.拿到适配器
//         * 4.适配器和控件绑定       setAdapter
//         */
//        setContentView(R.layout.activity_guide);//加载布局
////		LogWapper.e("abc", "----"+getLocalClassName());//打印当前类名   getLocalClassName();
//        if (!getData()) {
//            Intent intent=new Intent(this, HomeActivity.class);
//            startActivity(intent);
//            finish();
//        }
////		if (true) {//有引导页
////
////		}else {//没有
////			Intent intent=new Intent(this, HomeActivity.class);
////			startActivity(intent);
////			finish();
////		}
////		init();
//    }
//    /**
//     * 操作SharedPreferences中的数据
//     */
//    private boolean getData(){
//		/*
//		 * 1.拿到SharedPreferences的对象
//		 *     getSharedPreferences是ContextWrapper
//		 *
//		 *     name:文件名
//		 *     mode:访问以及读写操作权限
//		 * 2.读数据
//		 */
//		/*
//		 *第一次进来读取数据，如果没有东西
//		 */
//        preference=getSharedPreferences(PREFRENCE_NAME, MODE_PRIVATE);
//		/*
//		 * key：所要读的数据对应的key值        dif
//		 */
//        boolean isFirst=preference.getBoolean("is_first", true);//拿到值
//        return isFirst;
//    }
//    /**
//     * 初始化控件和绑定事件
//     */
//    @Override
//    void initView() {
//        //1.初始化控件
//        ViewPager pager=(ViewPager) findViewById(R.id.vp_guide);
//        mTvFristPoint=(ImageView) findViewById(R.id.tv_guide_first);
//        mTvSecondPoint=(ImageView) findViewById(R.id.tv_guide_second);
//        mTvThridPoint=(ImageView) findViewById(R.id.tv_guide_thrid);
//        mTvArray[0]=mTvFristPoint;
//        mTvArray[1]=mTvSecondPoint;
//        mTvArray[2]=mTvThridPoint;
//        mBtnPass=(Button) findViewById(R.id.btn_guide_pass);
//        //2.数据源
//        ArrayList<ImageView> listImg=new ArrayList<ImageView>();
//        for(int i=0;i<mPicture.length;i++){
//            ImageView img=new ImageView(this);
//            img.setImageResource(mPicture[i]);
//            listImg.add(img);
//        }
//        //3.适配器 PagerAdapter  抽象类
//        //    1.新建Adapter
//        //    2.
//        //    3.
//        //    4.
//        GuidePagerAdapter adapter=new GuidePagerAdapter(listImg);
//        pager.setAdapter(adapter);
//        pager.addOnPageChangeListener(this);//监听页面切换
////		   pager.setOnPageChangeListener(this);
//        //View.INVISIBLE   View.GONE 不能响应监听
//        mBtnPass.setOnClickListener(this);
//    }
//    /**
//     * 页面滚动状态的一个监听
//     * 1    滑动
//     * 2    弹性运动    复位
//     * 0    停止
//     */
//    @Override
//    public void onPageScrollStateChanged(int arg0) {
//    }
//    /**
//     * arg0      所操作页面的下标
//     * arg1      所滑动的偏移百分比[0,1);
//     * arg2      滑动的偏移像素
//     */
//    @Override
//    public void onPageScrolled(int arg0, float arg1, int arg2) {
////		LogWapper.e("onPageScrolled---"+arg0, arg1+"----"+arg2);
//    }
//    /**
//     * 所选择的页面
//     * 切换图片
//     */
//    @Override
//    public void onPageSelected(int arg0) {
//        for(ImageView tv:mTvArray){
//            mBtnPass.setVisibility(View.GONE);
//            tv.setImageResource(R.drawable.adware_style_default);
//        }
//        mTvArray[arg0].setImageResource(R.drawable.adware_style_selected);
//        if(arg0==2){
//            mBtnPass.setVisibility(View.VISIBLE);
//        }
////		LogWapper.e("abc", "arg0="+arg0)
////		if (arg0==0) {//选择第0个,其它两个灰色
////			mTvFristPoint.setImageResource(R.drawable.adware_style_selected);
////			mTvSecondPoint.setImageResource(R.drawable.adware_style_default);
////			mTvThridPoint.setImageResource(R.drawable.adware_style_default);
////			mBtnPass.setVisibility(View.GONE);
////		}else if (arg0==1) {//选择第1个，其它两个灰色
////			mTvFristPoint.setImageResource(R.drawable.adware_style_default);
////			mTvSecondPoint.setImageResource(R.drawable.adware_style_selected);
////			mTvThridPoint.setImageResource(R.drawable.adware_style_default);
////			mBtnPass.setVisibility(View.GONE);
////		}else{//选择第2个，其它两个灰色
////			mTvFristPoint.setImageResource(R.drawable.adware_style_default);
////			mTvSecondPoint.setImageResource(R.drawable.adware_style_default);
////			mTvThridPoint.setImageResource(R.drawable.adware_style_selected);
////			//
////			mBtnPass.setVisibility(View.VISIBLE);
////		}
//    }
//    @Override
//    public void onClick(View v) {
//		/*
//		 * 跳转SharedPreferences
//		 *     写数据
//		 *         1.拿到编辑器
//		 */
//        Editor edt=preference.edit();
//        edt.putBoolean("is_first", false);
//        edt.commit();
//        // 跳转
//        Intent intent=new Intent(this, HomeActivity.class);
//        startActivity(intent);
//        finish();
////		Intent intent=new Intent();
////		intent.setClass(this, HomeActivity.class);
////		startActivity(intent);
////		finish();
//    }
//}




}
