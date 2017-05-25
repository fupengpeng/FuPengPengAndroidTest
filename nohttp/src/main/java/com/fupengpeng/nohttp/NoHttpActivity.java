package com.fupengpeng.nohttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fupengpeng.nohttp.base.MyApplication;
import com.fupengpeng.nohttp.base.WaitDialog;
import com.fupengpeng.nohttp.utils.NoHttpRequest;
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

import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
/*
    NoHttp 用法总结
        1.导入依赖
        2.
 */

public class NoHttpActivity extends AppCompatActivity {

    //用于解绑Butterknife
    private Unbinder unbinder;

    //请求时的等待框
    private WaitDialog waitDialog;

    //获取控件
    @BindView(R.id.btn_activity_no_get_string)
    Button btnActivityNoGetString;
    @BindView(R.id.tv_activity_no_get_string)
    TextView tvActivityNoGetString;
    @BindView(R.id.tv_activity_no_get_string_particulars)
    TextView tvActivityNoGetStringParticulars;

    //用来标志请求的what
    private static final int NOHTTP_WHAT = 0x001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_http);
        //绑定
        unbinder = ButterKnife.bind(this);

        //创建等待框
        waitDialog = new WaitDialog(this);

    }
    @OnClick(R.id.btn_activity_no_get_string)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_no_get_string:
                noHttp_Post();
                break;
        }
    }

    /**
     * NoHttp的Get请求
     */
    private void noHttp_Get_String() {
        String url = "http://www.91taoke.com/DayiInterface/getClass?";

        //创建String请求
        Request<String> request = NoHttp.createStringRequest(url);

        MyApplication.getHttpQueues().add(
                NOHTTP_WHAT,
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
                        if (i == NOHTTP_WHAT) {
                            // 服务器响应码。
                            int responseCode = response.getHeaders().getResponseCode();
                            // 这里一定要判断状态码，经测试，404错误时也走这个回调方法
                            if (responseCode == 200) {
                                // 响应结果。
                                String result = response.get();
                                //显示结果
                                tvActivityNoGetString.setText(result);
                                // 拿到请求时设置的tag。
                                Object tag = response.getTag();

                                // 响应头
                                Headers headers = response.getHeaders();

                                String headResult = "响应码：%1$d\n花费时间：%2$d毫秒。";
                                headResult = String.format(Locale.getDefault(), headResult,
                                        headers.getResponseCode(), response.getNetworkMillis());
                                //显示请求详情
                                tvActivityNoGetStringParticulars.setText(headResult);

                            }
                        }

                    }

                    @Override
                    public void onFailed(int i, Response<String> response) {
                        // 请求失败
                        Exception exception = response.getException();
                        if (exception instanceof NetworkError) {// 网络不好
                            Toast.makeText(NoHttpActivity.this, "请检查网络。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof TimeoutError) {// 请求超时
                            Toast.makeText(NoHttpActivity.this, "请求超时，网络不好或者服务器不稳定。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
                            Toast.makeText(NoHttpActivity.this, "未发现指定服务器，清切换网络后重试。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof URLError) {// URL是错的
                            Toast.makeText(NoHttpActivity.this, "URL错误。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof NotFoundCacheError) {
                            // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
                            Toast.makeText(NoHttpActivity.this, "没有找到缓存.", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof ProtocolException) {
                            Toast.makeText(NoHttpActivity.this, "系统不支持的请求方法。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof ParseError) {
                            Toast.makeText(NoHttpActivity.this, "解析数据时发生错误。", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(NoHttpActivity.this, "未知错误。", Toast.LENGTH_SHORT).show();
                        }
                        Logger.e("错误：" + exception.getMessage());

                    }

                    @Override
                    public void onFinish(int i) {
                        // 请求结束，这里关闭dialog
                        if (waitDialog != null && waitDialog.isShowing())
                            waitDialog.dismiss();

                    }
                });

    }

    /**
     * NoHttp的Post请求
     */
    private void noHttp_Post_String() {
        String url = "http://www.91taoke.com/LoginInterface/studentLogin?";
        //创建String请求
        Request<String> request = NoHttp.createStringRequest(url);

        Map<String,String> hashMap = new HashMap<String,String>();
        String tel = "jbyxs101001";
        hashMap.put("tel",tel);
        String password = "111111";
        hashMap.put("password",password);

        request.add(hashMap);


        MyApplication.getHttpQueues().add(
                NOHTTP_WHAT,
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
                        if (i == NOHTTP_WHAT) {
                            // 服务器响应码。
                            int responseCode = response.getHeaders().getResponseCode();
                            // 这里一定要判断状态码，经测试，404错误时也走这个回调方法
                            if (responseCode == 200) {
                                // 响应结果。
                                String result = response.get();
                                //显示结果
                                tvActivityNoGetString.setText(result);
                                // 拿到请求时设置的tag。
                                Object tag = response.getTag();

                                // 响应头
                                Headers headers = response.getHeaders();

                                String headResult = "响应码：%1$d\n花费时间：%2$d毫秒。";
                                headResult = String.format(Locale.getDefault(), headResult,
                                        headers.getResponseCode(), response.getNetworkMillis());
                                //显示请求详情
                                tvActivityNoGetStringParticulars.setText(headResult);

                            }
                        }

                    }

                    @Override
                    public void onFailed(int i, Response<String> response) {
                        // 请求失败
                        Exception exception = response.getException();
                        if (exception instanceof NetworkError) {// 网络不好
                            Toast.makeText(NoHttpActivity.this, "请检查网络。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof TimeoutError) {// 请求超时
                            Toast.makeText(NoHttpActivity.this, "请求超时，网络不好或者服务器不稳定。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
                            Toast.makeText(NoHttpActivity.this, "未发现指定服务器，清切换网络后重试。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof URLError) {// URL是错的
                            Toast.makeText(NoHttpActivity.this, "URL错误。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof NotFoundCacheError) {
                            // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
                            Toast.makeText(NoHttpActivity.this, "没有找到缓存.", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof ProtocolException) {
                            Toast.makeText(NoHttpActivity.this, "系统不支持的请求方法。", Toast.LENGTH_SHORT).show();
                        } else if (exception instanceof ParseError) {
                            Toast.makeText(NoHttpActivity.this, "解析数据时发生错误。", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(NoHttpActivity.this, "未知错误。", Toast.LENGTH_SHORT).show();
                        }
                        Logger.e("错误：" + exception.getMessage());

                    }

                    @Override
                    public void onFinish(int i) {
                        // 请求结束，这里关闭dialog
                        if (waitDialog != null && waitDialog.isShowing())
                            waitDialog.dismiss();

                    }
                });
    }
    /**
     * NoHttp的Get请求（封装之后）
     */
    private void noHttp_Post() {

        String url = "http://www.91taoke.com/LoginInterface/studentLogin?";
        int tag = 5;
        Map<String,String> hashMap = new HashMap<String,String>();
        String tel = "jbyxs101001";
        hashMap.put("tel",tel);
        String password = "111111";
        hashMap.put("password",password);

        NoHttpRequest.RequestPost(NoHttpActivity.this, url, tag, hashMap, new NoHttpRequest.NoHttpInterface() {

            @Override
            public void onMySuccess(Response response) {
                Log.e("成功----------", "onMySuccess: "+"-------------" +response.toString()+"---------------");
            }

            @Override
            public void onMyError(Response errorResponse) {
                Log.e("失败---------", "onMyError: "+"----------"+errorResponse+"-------------") ;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
