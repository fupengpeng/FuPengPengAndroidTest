package com.fupengpeng.secondlevellinkage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fupengpeng.secondlevellinkage.adapter.MenuDialogAdapter;
import com.fupengpeng.secondlevellinkage.bean.MenuData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SecondLevelLinkageActivity extends AppCompatActivity {

    Unbinder unbinder;

    @BindView(R.id.lv_one)
    ListView lvOne;
    @BindView(R.id.lv_two)
    ListView lvTwo;

    private static final String TAG = "MainActivity";
    private View view1,view2;
    private MenuDialogAdapter mListView1Adapter, mListView2Adapter;
    private List<View> views = new ArrayList<View>();
    private MenuData resultDate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_level_linkage);
        unbinder = ButterKnife.bind(this);

        initViews();


    }

    private void initViews() {



        //一级
        List<MenuData> listParent= new ArrayList<MenuData>();

        for (int i = 0; i < 10; i++) {
            MenuData meanData = new MenuData();
            meanData.setId(i);
            meanData.setName("商品父级分类"+i);
            meanData.setFlag(i);
            listParent.add(meanData);
        }

        mListView1Adapter = new MenuDialogAdapter(this, listParent);
        mListView1Adapter.setSelectedBackgroundResource(R.drawable.select_white);//选中时
        mListView1Adapter.setHasDivider(false);
        mListView1Adapter.setNormalBackgroundResource(R.color.menudialog_bg_gray);//未选中
        lvOne.setAdapter(mListView1Adapter);// 一级菜单的listview适配器

        Log.e(TAG, "initViews: "+"一二级菜单" );
        views.add(view1);
        views.add(view2);//加载了一二级菜单
        Log.e(TAG, "initViews: "+"viewpager适配器设置");


        lvOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {//一级菜单的listview子条目点击事件
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListView1Adapter != null){
                    Log.e(TAG, "onItemClick: "+"点击对应子条目后，刷新对应子条目的下级菜单数据" );
                    mListView1Adapter.setSelectedPos(position);
                }

                if (mListView2Adapter != null){
                    Log.e(TAG, "onItemClick: "+"重新点击选择子条目时，二级菜单数据的变化" );
                    mListView2Adapter.setSelectedPos(-1);
                }



                MenuData menuData = (MenuData) parent.getItemAtPosition(position);

                if (menuData.id == 0) {//不限
                    Log.e(TAG, "onItemClick: "+"判断是否选择了第一个不限，如果是则直接返回" );
                    if (mListView2Adapter != null) {
                        mListView2Adapter.setData(new ArrayList<MenuData>());
                        mListView2Adapter.notifyDataSetChanged();
                        Log.e(TAG, "onItemClick: "+"判断二级菜单适配器是否为空" );
                    }

                    setResultDate(menuData);//直接返回
                } else {
                    Log.e(TAG, "onItemClick: "+"判断是否选择了第一个不限，如果是则直接返回，如果不是，刷新二级listview的适配器，展示数据" );

                    List<MenuData> listChild= new ArrayList<MenuData>();

                    for (int i = 0; i < 10; i++) {
                        MenuData meanData = new MenuData();
                        meanData.setId(i);
                        meanData.setName("商品子级分类"+i);
                        meanData.setFlag(i);
                        listChild.add(meanData);
                    }

                    if (mListView2Adapter == null) {
                        Log.e(TAG, "onItemClick: "+"判断二级listview适配器是否为空，为空则创建，并设置" );
                        mListView2Adapter = new MenuDialogAdapter(SecondLevelLinkageActivity.this, listChild);
                        mListView2Adapter.setNormalBackgroundResource(R.color.white);
                        lvTwo.setAdapter(mListView2Adapter);//二级菜单listview的适配器
                    } else {
                        Log.e(TAG, "onItemClick: "+"判断二级listview适配器是否为空，为空则创建，并设置，不为空直接设置数据，刷新适配器" );
                        mListView2Adapter.setData(listChild);
                        mListView2Adapter.notifyDataSetChanged();
                    }
//                    mRootView.invalidate();
                }
            }
        });



        //二级
        lvTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {//二级菜单listview子条目的点击事件
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                if (mListView2Adapter != null) {
                    Log.e(TAG, "onItemClick: "+"二级list" +
                            "view适配器不为空，界面变化" );
                    mListView2Adapter.setSelectedPos(position);
                    mListView2Adapter.setSelectedBackgroundResource(R.drawable.select_gray);//选中时
                    MenuData menuData = (MenuData) parent.getItemAtPosition(position);
                    Log.e(TAG, "onItemClick: "+"三级菜单listview子条目点击事件，选定对应的子条目后，将数据传递给home界面" );
                    setResultDate(menuData);
                }
            }
        });


    }

    //传递值
    private void setResultDate(MenuData menuData){
        Log.e(TAG, "setResultDate: "+"将选择的值，传递给home界面" );
        Intent intent=new Intent();
        intent.putExtra("menu",(Serializable)menuData);
        setResult(0, intent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
