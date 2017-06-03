package com.fupengpeng.popupwindow;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class PopupWindowActivity extends AppCompatActivity {
    public static final String TAG = "PopupWindowActivity";

    AddressEditPopupWindow addressEditPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_window);

        TextView textViewPopupWindow = (TextView) findViewById(R.id.tv_popup_window);
        textViewPopupWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//实例化SelectPicPopupWindow
                addressEditPopupWindow = new AddressEditPopupWindow(PopupWindowActivity.this, itemsOnClick);
                //显示窗口
                addressEditPopupWindow.showAtLocation(PopupWindowActivity.this.findViewById(R.id.activity_popup_window),
                        Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
                //设置layout在PopupWindow中显示的位置
            }
        });

        TextView textViewDialog = (TextView) findViewById(R.id.tv_dialog);
        textViewDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PopupWindowActivity.this);
//                builder.setIcon(R.drawable.ic_launcher);//添加ICON
//                builder.setTitle("退出");                //添加标题
//                builder.setMessage("你确定要离开吗？");     //添加MSG

                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.change_password, null);

                builder.setView(view);//添加自定义View
                builder.create();
                builder.show();
            }
        });
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            addressEditPopupWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_address_edit_popup_window_delete:
                    Log.e(TAG, "onClick: "+"删除收货地址" );
                    break;
                case R.id.btn_address_edit_popup_window_save:
                    Log.e(TAG, "onClick: "+"保存收货地址" );

                    break;
                default:
                    break;
            }


        }

    };
}
