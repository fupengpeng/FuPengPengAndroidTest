package com.fupengpeng.nohttp.base;

import android.app.Application;

import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;


/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class MyApplication extends Application {
    //声明全局请求队列
    public static RequestQueue requestQueue;

    /**
     * 这个方法在应用初始化的时候被调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化NoHttp
        NoHttp.initialize(this);

        // 开始NoHttp的调试模式, 这样就能看到请求过程和日志（可选）
        Logger.setTag("NoHttpSample");
        Logger.setDebug(true);

        //实例化全局请求队列
        requestQueue = NoHttp.newRequestQueue();

    }

    /**
     * 提供一个方法获取请求队列
     * @return
     */
    public static RequestQueue getHttpQueues(){
        return requestQueue;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
