package com.fupengpeng.volley.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.fupengpeng.volley.base.MyApplication;

import java.util.Map;

/**
 * Created by fupengpeng on 2017/5/25 0025.
 */

public class VolleyRequest {
    public static StringRequest stringRequest;
    public static Context context;


    public static void RequestGet(Context mContext,
                                  String url,
                                  String tag,
                                  VolleyInterface volleyInterface){
        //把tag标签的请求关闭掉，防止出现重复请求，消耗内存
        MyApplication.getHttpQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET,url,
                volleyInterface.loadingListener(),
                volleyInterface.errorListener());
        //设置tag标签（tag传递进来的）

        stringRequest.setTag(tag);
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 2, 1.0f));
        //将请求添加到队列
        MyApplication.getHttpQueues().add(stringRequest);
        MyApplication.getHttpQueues().start();

    }

    /**
     * post请求
     * @param context  上下文
     * @param url  请求的url
     * @param tag  请求的tag
     * @param params  请求的参数map集合
     * @param volleyInterface  请求监听接口
     */
    public static void RequestPost(Context context,
                                   String url,
                                   String tag,
                                   final Map<String,String> params,
                                   VolleyInterface volleyInterface){
        //获取请求队列，把tag标签的请求关闭掉，防止出现重复请求，消耗内存
        MyApplication.getHttpQueues().cancelAll(tag);

        stringRequest = new StringRequest(url,
                volleyInterface.loadingListener(),
                volleyInterface.errorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 2, 1.0f));
        //添加队列
        MyApplication.getHttpQueues().add(stringRequest);
        MyApplication.getHttpQueues().start();
    }
}
