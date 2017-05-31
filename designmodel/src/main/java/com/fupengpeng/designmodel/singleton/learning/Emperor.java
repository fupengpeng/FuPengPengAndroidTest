package com.fupengpeng.designmodel.singleton.learning;

import android.util.Log;

/**
 * Created by fupengpeng on 2017/5/27 0027.
 * 单例
 */

public class Emperor {
    public static final String TAG = "Emperor";
    //声明一个单例对象
    private static Emperor emperor = null;
    //只提供一个私有的构造函数
    private Emperor(){}
    //实例化单例对象
    public static Emperor getInstance(){
        if (emperor == null){
            emperor = new Emperor();
        }
        return emperor;
    }

    public static void emperorInfo(){
        Log.e(TAG, "emperorInfo: "+"独一无二" );
    }
}
