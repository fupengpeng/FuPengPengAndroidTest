package com.fupengpeng.nohttp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yolanda.nohttp.BasicBinary;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnUploadListener;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import java.io.File;

public class UpLoadActivity extends AppCompatActivity {

    //请求队列
    private RequestQueue requestQueue;

    //用来标志请求的what
    private static final int NOHTTP_WHAT = 0x001;
    //单个文件上传监听的标志
    private final int WHAT_UPLOAD_SINGLE = 0x011;

    //TextView：上传状态
    private TextView tvResult;
    //ProgressBar：进度条
    private ProgressBar pbProgress;
    //点击上传
    private Button btnActivityNoHttpSchangChuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load);

        //创建队列
        requestQueue = NoHttp.newRequestQueue();

        //初始化控件
        initViews();
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        btnActivityNoHttpSchangChuan = (Button) findViewById(R.id.btn_activity_no_http_shangchuan);
        tvResult = (TextView) findViewById(R.id.tv_activity_no_http_shangchuanzhuangtai);
        pbProgress = (ProgressBar) findViewById(R.id.prb_activity_no_http_shangchuanjindu);

        btnActivityNoHttpSchangChuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 上传文件
     *
     * @param view
     */
    public void doClick(View view) {
        //创建请求
        Request<String> request = NoHttp.createStringRequest("http://www.91taoke.com/LoginInterface/updateUserInfo?", RequestMethod.POST);

        // 添加普通参数。
        request.add("uid", "jbyxs101001");
        request.add("Head_image","");
        request.add("Filename","");
        request.add("nickname","");
        request.add("realname","");
        request.add("sex","");
        request.add("banji","");
        /*
        uid：用户ID
Head_image：用户头像 （如果不传值，不修改，下同）
Filename：头像文件名称
nickname：昵称
realname：真实姓名
sex：性别
banji：年级
         */


        // FileBinary用法
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/NoHttpSample/image1.jpg";
        BasicBinary binary = new FileBinary(new File(filePath));

        /**
         * 监听上传过程，如果不需要监听就不用设置。
         * 第一个参数：what，what和handler的what一样，会在回调被调用的回调你开发者，
         *            作用是一个Listener可以监听多个文件的上传状态。
         * 第二个参数： 监听器。
         */
        binary.setUploadListener(WHAT_UPLOAD_SINGLE, uploadListener);

        //将BasicBinary加入request
        // 添加1个文件
        request.add("image0", binary);
        // 添加2个文件
        //request.add("image1", binary1);

        //将request加入requestQueue
        requestQueue.add(NOHTTP_WHAT, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int i) {
            }

            @Override
            public void onSucceed(int i, Response<String> response) {
                Toast.makeText(UpLoadActivity.this, "" + response.get(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailed(int i, Response<String> response) {
            }

            @Override
            public void onFinish(int i) {
            }
        });
    }

    /**
     * 文件上传监听。
     */
    private OnUploadListener uploadListener = new OnUploadListener() {

        @Override
        public void onStart(int what) {// 这个文件开始上传。
            tvResult.setText("上传中...");
        }

        @Override
        public void onCancel(int what) {// 这个文件的上传被取消时。
            tvResult.setText("上传被取消");
        }

        @Override
        public void onProgress(int what, int progress) {// 这个文件的上传进度
            pbProgress.setProgress(progress);
            tvResult.setText("上传 " + (progress) + "%");
        }

        @Override
        public void onFinish(int what) {// 文件上传完成
            tvResult.setText("上传完成");
        }

        @Override
        public void onError(int what, Exception exception) {// 文件上传发生错误。
            tvResult.setText("上传出错");
        }
    };
}
