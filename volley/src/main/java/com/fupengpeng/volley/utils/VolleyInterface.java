package com.fupengpeng.volley.utils;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by fupengpeng on 2017/5/25 0025.
 */

public abstract class VolleyInterface {
    /**
     * 创建对应的对象，进行绑定
     */
    public Context mContext;
    //请求时的等待框
    private WaitDialog waitDialog;

    //请求成功的监听
    public static Response.Listener<String> mLisener;
    //请求失败的监听
    public static Response.ErrorListener mErrorListener;

    /**
     * 有参构造
     * @param context  上下文
     * @param listener  请求成功的监听
     * @param errorListener  请求失败的监听
     */
    public VolleyInterface(Context context,
                           Response.Listener<String> listener,
                           Response.ErrorListener errorListener)
    {
        /**
         * 绑定对应传递进来的参数
         */
        this.mContext = context;
        this.mLisener = listener;
        this.mErrorListener = errorListener;
    }


    /**
     * 请求成功的监听
     * @return  返回请求成功监听对象
     */
    public Response.Listener<String> loadingListener(){
        mLisener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.e("VolleyInterface", "onResponse: "+"请求成功" );
                onMySuccess(response);
                //提示请求成功
            }
        };

        /**
         * 返回请求成功监听的对象
         */
        return mLisener;
    }

    /**
     * 请求失败的监听
     * @return  返回请求失败监听的对象
     */
    public Response.ErrorListener errorListener(){
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.e("VolleyInterface", "onResponse: "+"请求失败" );
                onMyError(error);
                //提示请求失败
            }
        };
        /**
         * 返回请求失败监听的对象
         */
        return mErrorListener;
    }

    /**
     *请求成功时回调
     * @param result
     */
    public abstract void onMySuccess(String result);

    /**
     * 请求失败时回调
     * @param error
     */
    public abstract void onMyError(VolleyError error);

}
