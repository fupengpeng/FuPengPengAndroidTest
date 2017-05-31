package com.fupengpeng.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 倒计时任务
 */
public class TimerActivity extends AppCompatActivity {

    /**
     * 倒计时50秒
     */
    private int recLen = 50;
    private TextView txtView;
    Timer timer = new Timer();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timer);
        txtView = (TextView)findViewById(R.id.tv_timer);

        /**
         *  task:  执行的任务
         *  5000： 任务执行延迟时间
         *  1000：  任务执行间隔
         */
        timer.schedule(task, 5000, 1000);       // timeTask
    }

    /**
     * 倒计时任务
     */
    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    /**
                     * 递减
                     */
                    recLen--;
                    txtView.setText(""+recLen);
                    if(recLen < 0){//倒计时完成后，执行相关操作
                        timer.cancel();
                        txtView.setVisibility(View.GONE);
                    }
                }
            });
        }
    };
}
