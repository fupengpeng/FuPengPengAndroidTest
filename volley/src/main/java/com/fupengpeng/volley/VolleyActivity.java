package com.fupengpeng.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.fupengpeng.volley.base.MyApplication;
import com.fupengpeng.volley.utils.VolleyInterface;
import com.fupengpeng.volley.utils.VolleyRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Volley网络请求的使用
 */
public class VolleyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
//        volley_Get_String();
//        volley_Get_JSONObject();
//        volley_Post_String();
//        volley_Post_JSONObject();
//        volley_Get_Request();
        volley_Post_Request();
    }

    private void volley_Post_Request() {
        String url = "http://www.91taoke.com/LoginInterface/studentLogin?";
        Map<String,String> hashMap = new HashMap<String,String>();
        String tel = "jbyxs101001";
        hashMap.put("tel",tel);
        String password = "111111";
        hashMap.put("password",password);

        VolleyRequest.RequestPost(this,
                url,
                "volley_Post_Request",
                hashMap,
                new VolleyInterface(
                        this,
                        VolleyInterface.mLisener,
                        VolleyInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        Toast.makeText(VolleyActivity.this,"----"+result,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        Toast.makeText(VolleyActivity.this,"----请求失败",Toast.LENGTH_LONG).show();
                    }
        });
    }

    private void volley_Get_Request() {
        String url = "http://www.91taoke.com/DayiInterface/getClass?";
        VolleyRequest.RequestGet(this,
                url,
                "abcRequest",
                new VolleyInterface(
                        this,
                        VolleyInterface.mLisener,
                        VolleyInterface.mErrorListener
                ) {
                    @Override
                    public void onMySuccess(String result) {
                        Toast.makeText(VolleyActivity.this,"----"+result,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        Toast.makeText(VolleyActivity.this,"----请求失败",Toast.LENGTH_LONG).show();
                    }
        });
    }

    private void volley_Post_JSONObject() {
        String url = "http://www.91taoke.com/LoginInterface/studentLogin?";
        Map<String,String> hashMap = new HashMap<String,String>();
        String tel = "jbyxs101001";
        hashMap.put("tel",tel);
        String password = "111111";
        hashMap.put("password",password);
        JSONObject jsonObject = new JSONObject(hashMap);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(VolleyActivity.this,"----"+response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this,"----请求失败",Toast.LENGTH_LONG).show();
            }
        });
        request.setTag("abcPostJSONObject");
        MyApplication.getHttpQueues().add(request);
    }

    private void volley_Post_String() {
        String url = "http://www.91taoke.com/LoginInterface/studentLogin?";

        StringRequest request = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(VolleyActivity.this,"----"+response,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this,"----请求失败",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> hashMap = new HashMap<String,String>();
                String tel = "jbyxs101001";
                hashMap.put("tel",tel);
                String password = "111111";
                hashMap.put("password",password);
                return hashMap;
            }
        };
        request.setTag("abcPostString");
        MyApplication.getHttpQueues().add(request);
                }

    private void volley_Get_JSONObject() {
        String url = "http://www.91taoke.com/DayiInterface/getClass?";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url,
                null,//JSONObject对象
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(VolleyActivity.this,"----"+response.toString(),Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VolleyActivity.this,"----请求失败",Toast.LENGTH_LONG).show();
                    }
                });
        request.setTag("abcGetJSONObject");
        MyApplication.getHttpQueues().add(request);
    }

    private void volley_Get_String() {
        String url = "http://www.91taoke.com/DayiInterface/getClass?";
        StringRequest request = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(VolleyActivity.this,"----"+response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this,"----请求失败",Toast.LENGTH_LONG).show();
            }
        });
        request.setTag("abcGetString");
        MyApplication.getHttpQueues().add(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //根据标签取消网络请求，防止出现内存泄露
        MyApplication.getHttpQueues().cancelAll("abcPostJSONObject");
    }
}
