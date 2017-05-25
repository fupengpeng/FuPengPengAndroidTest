package com.fupengpeng.volley.base;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class MyApplication extends Application {
    //声明全局请求队列
    public static RequestQueue queues;

    /**
     * 这个方法在应用初始化的时候被调用
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //实例化全局请求队列
        queues = Volley.newRequestQueue(getApplicationContext());

    }

    /**
     * 提供一个方法获取请求队列
     * @return
     */
    public static RequestQueue getHttpQueues(){
        return queues;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
