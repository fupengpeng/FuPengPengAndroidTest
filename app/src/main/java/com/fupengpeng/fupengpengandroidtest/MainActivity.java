package com.fupengpeng.fupengpengandroidtest;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fupengpeng.fupengpengandroidtest.widget.CityPicker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAddress(MainActivity.this);
//                List<ProvinceModel> provinceList = JSON.parseArray(getJson(MainActivity.this.getApplicationContext()), ProvinceModel.class);
//                Log.e("aaa" , provinceList.get(0).getCityList().get(0).getDistrictList().get(0).getZipcode());

            }
        });



    }

    public String getJson(Context context) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager asset = context.getAssets();
            BufferedReader bf  = new BufferedReader(new InputStreamReader(asset.open("simple_cities_pro_city_dis.json")));

            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
                Log.d("AAA", line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }



    private void selectAddress(Context context) {
        CityPicker cityPicker = new CityPicker.Builder(context)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#ffffff")
//                .titleTextColor("#696969")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("山东省")
                .city("济宁市")
                .district("任城区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                tvAddress.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }

            @Override
            public void onSelected(Object... Object) {

            }
        });
    }

}
