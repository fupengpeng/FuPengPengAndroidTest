package com.fupengpeng.nohttp.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.fupengpeng.nohttp.base.MyApplication;
import com.fupengpeng.nohttp.base.WaitDialog;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.error.NetworkError;
import com.yolanda.nohttp.error.NotFoundCacheError;
import com.yolanda.nohttp.error.ParseError;
import com.yolanda.nohttp.error.TimeoutError;
import com.yolanda.nohttp.error.URLError;
import com.yolanda.nohttp.error.UnKnownHostError;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.StringRequest;

import java.net.ProtocolException;
import java.util.Locale;
import java.util.Map;

/**
 * Created by fupengpeng on 2017/5/25 0025.
 */
public abstract class NoHttpRequest {

    public static final String TAG = "NoHttpRequest";
    public static Request<String> request;
    public static Context context;

    //请求时的等待框
    private static WaitDialog waitDialog;
    /**
     * post请求
     * @param context  上下文
     * @param url  请求的url
     * @param tag  请求的tag
     * @param params  请求的参数map集合
     */
    public static void RequestPost(final Context context,
                                   String url,
                                   final int tag,
                                   final Map<String,String> params,
                                   final NoHttpInterface noHttpInterface){

        //获取请求队列，把tag标签的请求关闭掉，防止出现重复请求，消耗内存
        MyApplication.getHttpQueues().cancelAll();
        waitDialog = new WaitDialog(context);
        request = NoHttp.createStringRequest(url);
        request.add(params);
        MyApplication.getHttpQueues().add(
                tag,
                request,
                new OnResponseListener<String>() {
                    @Override
                    public void onStart(int i) {
                        // 请求开始，这里可以显示一个dialog
                        if (waitDialog != null && !waitDialog.isShowing())
                            waitDialog.show();
                    }

                    @Override
                    public void onSucceed(int i, Response<String> response) {
                        // 根据what判断是哪个请求的返回，这样就可以用一个OnResponseListener来接受多个请求的结果。
                        if (i == tag) {
                            // 服务器响应码。
                            int responseCode = response.getHeaders().getResponseCode();
                            // 这里一定要判断状态码，经测试，404错误时也走这个回调方法
                            if (responseCode == 200) {
                                // 响应结果。
                                String result = response.get();
                                //显示结果
//                                tvActivityNoGetString.setText(result);
                                noHttpInterface.onMySuccess(response);

                                // 拿到请求时设置的tag。
                                Object tag = response.getTag();

                                // 响应头
                                Headers headers = response.getHeaders();

                                String headResult = "响应码：%1$d\n花费时间：%2$d毫秒。";
                                headResult = String.format(Locale.getDefault(), headResult,
                                        headers.getResponseCode(), response.getNetworkMillis());
                                //显示请求详情
//                                tvActivityNoGetStringParticulars.setText(headResult);

                            }
                        }
                    }

                    @Override
                    public void onFailed(int i, Response<String> response) {
                        // 请求失败
                        Exception exception = response.getException();
                        if (exception instanceof NetworkError) {// 网络不好
                            Toast.makeText(context, "请检查网络。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof TimeoutError) {// 请求超时
                            Toast.makeText(context, "请求超时，网络不好或者服务器不稳定。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
                            Toast.makeText(context, "未发现指定服务器，清切换网络后重试。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof URLError) {// URL是错的
                            Toast.makeText(context, "URL错误。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof NotFoundCacheError) {
                            // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
                            Toast.makeText(context, "没有找到缓存.", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof ProtocolException) {
                            Toast.makeText(context, "系统不支持的请求方法。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof ParseError) {
                            Toast.makeText(context, "解析数据时发生错误。", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "未知错误。", Toast.LENGTH_SHORT).show();
                        }
                        Logger.e("错误：" + exception.getMessage());
                        noHttpInterface.onMyError(response);
                    }

                    @Override
                    public void onFinish(int i) {
                        // 请求结束，这里关闭dialog
                        if (waitDialog != null && waitDialog.isShowing())
                            waitDialog.dismiss();
                    }
                });
//        MyApplication.getHttpQueues().start();
    }

    //
    public interface  NoHttpInterface<T>{
        //请求成功时调用
        public void onMySuccess(Response<T> response);
        //请求失败时调用
        public void onMyError(Response<T> errorResponse);

    }
}
