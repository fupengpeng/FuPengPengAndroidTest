package com.fupengpeng.listmenu.widget;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.fupengpeng.listmenu.R;
import com.fupengpeng.listmenu.model.CityModel;
import com.fupengpeng.listmenu.model.DistrictModel;
import com.fupengpeng.listmenu.model.ProvinceModel;
import com.fupengpeng.listmenu.widget.wheel.OnWheelChangedListener;
import com.fupengpeng.listmenu.widget.wheel.WheelView;
import com.fupengpeng.listmenu.widget.wheel.adapters.ArrayWheelAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 省市区三级选择
 * 作者：liji on 2015/12/17 10:40
 * 邮箱：lijiwork@sina.com
 */
public class CityPicker implements CanShow, OnWheelChangedListener {
    
    private Context context;
    
    private PopupWindow popwindow;
    
    private View popview;
    
    private WheelView mViewProvince;
    
    private WheelView mViewCity;
    
    private WheelView mViewDistrict;

    private RelativeLayout mRelativeTitleBg;
    
    private TextView mTvOK;

    private TextView mTvTitle;

    private TextView mTvCancel;
    


    /**
     * 所有省
     */
    protected ProvinceModel[] mProvinceData;




    /**
     * key - 省 value - 市
     */
    protected Map<ProvinceModel, CityModel[]> mCitisDataMap = new HashMap<ProvinceModel, CityModel[]>();





    /**
     * key - 市 values - 区
     */
    protected Map<CityModel, DistrictModel[]> mDistrictDataMap = new HashMap<CityModel, DistrictModel[]>();



    /**
     * key - 区 values - 邮编
     */
    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();
    
//    /**
//     * 当前省的名称
//     */
//    protected String mCurrentProviceName;

    /**
     * 当前省
     */
    protected ProvinceModel mCurrentProvice;

    /**
     * 当前市的名称
     */
    protected String mCurrentCityName;

    /**
     * 当前市
     */
    protected CityModel mCurrentCity;
    
    /**
     * 当前区的名称
     */
    protected String mCurrentDistrictName = "";

    /**
     * 当前区
     */
    protected DistrictModel mCurrentDistrict;
    
    /**
     * 当前区的邮政编码
     */
    protected String mCurrentZipCode = "";
    
    private OnCityItemClickListener listener;
    private final EditText mEtAddress;

    public interface OnCityItemClickListener {

        void onSelected(ProvinceModel mCurrentProvice, CityModel mCurrentCity, DistrictModel mCurrentDistrict, String mCurrentZipCode);
    }
    
    public void setOnCityItemClickListener(OnCityItemClickListener listener) {
        this.listener = listener;
    }
    
    /**
     * Default text color
     */
    public static final int DEFAULT_TEXT_COLOR = 0xFF585858;
    
    /**
     * Default text size
     */
    public static final int DEFAULT_TEXT_SIZE = 18;
    
    // Text settings
    private int textColor = DEFAULT_TEXT_COLOR;
    
    private int textSize = DEFAULT_TEXT_SIZE;
    
    /**
     * 滚轮显示的item个数
     */
    private static final int DEF_VISIBLE_ITEMS = 5;
    
    // Count of visible items
    private int visibleItems = DEF_VISIBLE_ITEMS;
    
    /**
     * 省滚轮是否循环滚动
     */
    private boolean isProvinceCyclic = true;
    
    /**
     * 市滚轮是否循环滚动
     */
    private boolean isCityCyclic = true;
    
    /**
     * 区滚轮是否循环滚动
     */
    private boolean isDistrictCyclic = true;
    
    /**
     * item间距
     */
    private int padding = 5;
    

    /**
     * Color.BLACK
     */
    private String cancelTextColorStr = "#000000";
    

    /**
     * Color.BLUE
     */
    private String confirmTextColorStr = "#0000FF";

    /**
     * 标题背景颜色
     */
    private String titleBackgroundColorStr="#E9E9E9";
    
    /**
     * 第一次默认的显示省份，一般配合定位，使用
     */
    private String defaultProvinceName = "江苏";

    /**
     * 第一次默认的显示省份，一般配合定位，使用
     */
    private ProvinceModel defaultProvince;
    
    /**
     * 第一次默认得显示城市，一般配合定位，使用
     */
    private String defaultCityName = "常州";

    /**
     * 第一次默认得显示城市，一般配合定位，使用
     */
    private CityModel defaultCity;
    
    /**
     * 第一次默认得显示，一般配合定位，使用
     */
    private String defaultDistrictName = "新北区";

    /**
     * 第一次默认得显示，一般配合定位，使用
     */
    private DistrictModel defaultDistrict;
    
    /**
     * 两级联动
     */
    private boolean showProvinceAndCity = false;

    /**
     * 标题
     */
    private String mTitle="选择地区";
    
    private CityPicker(Builder builder) {
        this.textColor = builder.textColor;
        this.textSize = builder.textSize;
        this.visibleItems = builder.visibleItems;
        this.isProvinceCyclic = builder.isProvinceCyclic;
        this.isDistrictCyclic = builder.isDistrictCyclic;
        this.isCityCyclic = builder.isCityCyclic;
        this.context = builder.mContext;
        this.padding = builder.padding;
        this.mTitle=builder.mTitle;
        this.titleBackgroundColorStr=builder.titleBackgroundColorStr;
        this.confirmTextColorStr = builder.confirmTextColorStr;
        this.cancelTextColorStr = builder.cancelTextColorStr;
        
        this.defaultDistrictName = builder.defaultDistrictName;
        this.defaultCityName = builder.defaultCityName;
        this.defaultProvinceName = builder.defaultProvinceName;
        
        this.showProvinceAndCity = builder.showProvinceAndCity;
        
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        popview = layoutInflater.inflate(R.layout.pop_citypicker, null);
        
        mViewProvince = (WheelView) popview.findViewById(R.id.id_province);
        mViewCity = (WheelView) popview.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) popview.findViewById(R.id.id_district);
        mEtAddress = (EditText) popview.findViewById(R.id.et_address);
        mRelativeTitleBg = (RelativeLayout) popview.findViewById(R.id.rl_title);
        mTvOK = (TextView) popview.findViewById(R.id.tv_confirm);
        mTvTitle = (TextView) popview.findViewById(R.id.tv_title);
        mTvCancel = (TextView) popview.findViewById(R.id.tv_cancel);


        popwindow = new PopupWindow(popview, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popwindow.setBackgroundDrawable(new ColorDrawable(0x80000000));
        popwindow.setAnimationStyle(R.style.AnimBottom);
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(true);
        popwindow.setFocusable(true);


        /**
         * 设置标题背景颜色
         */
        if (!TextUtils.isEmpty(this.titleBackgroundColorStr)) {
            mRelativeTitleBg.setBackgroundColor(Color.parseColor(this.titleBackgroundColorStr));
        }

        /**
         * 设置标题
         */
        if (!TextUtils.isEmpty(this.mTitle)){
            mTvTitle.setText(this.mTitle);
        }

        //设置确认按钮文字颜色
        if (!TextUtils.isEmpty(this.confirmTextColorStr)) {
            mTvOK.setTextColor(Color.parseColor(this.confirmTextColorStr));
        }

        //设置取消按钮文字颜色
        if (!TextUtils.isEmpty(this.cancelTextColorStr)) {
            mTvCancel.setTextColor(Color.parseColor(this.cancelTextColorStr));
        }

        
        //只显示省市两级联动
        if (this.showProvinceAndCity) {
            mViewDistrict.setVisibility(View.GONE);
        }
        else {
            mViewDistrict.setVisibility(View.VISIBLE);
        }
        
        //初始化城市数据
        initProvinceData(context);
        
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
        mTvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showProvinceAndCity) {
                    listener.onSelected(mCurrentProvice,mCurrentCity,null,mCurrentZipCode);

                }
                else {
                    listener.onSelected(mCurrentProvice,mCurrentCity,mCurrentDistrict,mCurrentZipCode);
                }
                hide();
            }
        });

    }
    
    public static class Builder {
        /**
         * Default text color
         */
        public static final int DEFAULT_TEXT_COLOR = 0xFF585858;
        
        /**
         * Default text size
         */
        public static final int DEFAULT_TEXT_SIZE = 18;
        
        // Text settings
        private int textColor = DEFAULT_TEXT_COLOR;
        
        private int textSize = DEFAULT_TEXT_SIZE;
        
        /**
         * 滚轮显示的item个数
         */
        private static final int DEF_VISIBLE_ITEMS = 5;
        
        // Count of visible items
        private int visibleItems = DEF_VISIBLE_ITEMS;
        
        /**
         * 省滚轮是否循环滚动
         */
        private boolean isProvinceCyclic = true;
        
        /**
         * 市滚轮是否循环滚动
         */
        private boolean isCityCyclic = true;
        
        /**
         * 区滚轮是否循环滚动
         */
        private boolean isDistrictCyclic = true;
        
        private Context mContext;
        
        /**
         * item间距
         */
        private int padding = 5;
        

        /**
         * Color.BLACK
         */
        private String cancelTextColorStr = "#000000";
        

        /**
         * Color.BLUE
         */
        private String confirmTextColorStr = "#0000FF";

        /**
         * 标题背景颜色
         */
        private String titleBackgroundColorStr="#E9E9E9";
        
        /**
         * 第一次默认的显示省份，一般配合定位，使用
         */
        private String defaultProvinceName = "江苏";
        
        /**
         * 第一次默认得显示城市，一般配合定位，使用
         */
        private String defaultCityName = "常州";
        
        /**
         * 第一次默认得显示，一般配合定位，使用
         */
        private String defaultDistrictName = "新北区";

        /**
         * 标题
         */
        private String mTitle="选择地区";
        
        /**
         * 两级联动
         */
        private boolean showProvinceAndCity = false;
        
        public Builder(Context context) {
            this.mContext = context;
        }

        /**
         * 设置标题背景颜色
         * @param colorBg
         * @return
         */
        public Builder titleBackgroundColor(String colorBg){
            this.titleBackgroundColorStr=colorBg;
            return this;
        }

        /**
         * 设置标题
         * @param mtitle
         * @return
         */
        public Builder title(String mtitle){
            this.mTitle=mtitle;
            return this;
        }

        /**
         * 是否只显示省市两级联动
         * @param flag
         * @return
         */
        public Builder onlyShowProvinceAndCity(boolean flag) {
            this.showProvinceAndCity = flag;
            return this;
        }
        
        /**
         * 第一次默认的显示省份，一般配合定位，使用
         * @param defaultProvinceName
         * @return
         */
        public Builder province(String defaultProvinceName) {
            this.defaultProvinceName = defaultProvinceName;
            return this;
        }
        
        /**
         * 第一次默认得显示城市，一般配合定位，使用
         * @param defaultCityName
         * @return
         */
        public Builder city(String defaultCityName) {
            this.defaultCityName = defaultCityName;
            return this;
        }
        
        /**
         * 第一次默认地区显示，一般配合定位，使用
         * @param defaultDistrictName
         * @return
         */
        public Builder district(String defaultDistrictName) {
            this.defaultDistrictName = defaultDistrictName;
            return this;
        }
        
        //        /**
        //         * 确认按钮文字颜色
        //         * @param color
        //         * @return
        //         */
        //        public Builder confirTextColor(int color) {
        //            this.confirmTextColor = color;
        //            return this;
        //        }
        
        /**
         * 确认按钮文字颜色
         * @param color
         * @return
         */
        public Builder confirTextColor(String color) {
            this.confirmTextColorStr = color;
            return this;
        }
        
        //        /**
        //         * 取消按钮文字颜色
        //         * @param color
        //         * @return
        //         */
        //        public Builder cancelTextColor(int color) {
        //            this.cancelTextColor = color;
        //            return this;
        //        }
        
        /**
         * 取消按钮文字颜色
         * @param color
         * @return
         */
        public Builder cancelTextColor(String color) {
            this.cancelTextColorStr = color;
            return this;
        }
        
        /**
         * item文字颜色
         * @param textColor
         * @return
         */
        public Builder textColor(int textColor) {
            this.textColor = textColor;
            return this;
        }
        
        /**
         * item文字大小
         * @param textSize
         * @return
         */
        public Builder textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }
        
        /**
         * 滚轮显示的item个数
         * @param visibleItems
         * @return
         */
        public Builder visibleItemsCount(int visibleItems) {
            this.visibleItems = visibleItems;
            return this;
        }
        
        /**
         * 省滚轮是否循环滚动
         * @param isProvinceCyclic
         * @return
         */
        public Builder provinceCyclic(boolean isProvinceCyclic) {
            this.isProvinceCyclic = isProvinceCyclic;
            return this;
        }
        
        /**
         * 市滚轮是否循环滚动
         * @param isCityCyclic
         * @return
         */
        public Builder cityCyclic(boolean isCityCyclic) {
            this.isCityCyclic = isCityCyclic;
            return this;
        }
        
        /**
         * 区滚轮是否循环滚动
         * @param isDistrictCyclic
         * @return
         */
        public Builder districtCyclic(boolean isDistrictCyclic) {
            this.isDistrictCyclic = isDistrictCyclic;
            return this;
        }
        
        /**
         * item间距
         * @param itemPadding
         * @return
         */
        public Builder itemPadding(int itemPadding) {
            this.padding = itemPadding;
            return this;
        }
        
        public CityPicker build() {
            CityPicker cityPicker = new CityPicker(this);
            return cityPicker;
        }
        
    }




    /**
     * 设置默认展示的省市区县
     *     根据设置的默认省份直接定位到该省份
     */
    private void setUpData() {
        int provinceDefault = -1;
        if (defaultProvince != null && mProvinceData.length > 0) {
            for (int i = 0; i < mProvinceData.length; i++) {
                if (mProvinceData[i].getId() == defaultProvince.getId()) {
                    provinceDefault = i;
                    break;
                }
            }
        }
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<ProvinceModel>(context, mProvinceData);
        mViewProvince.setViewAdapter(arrayWheelAdapter);
        //获取所设置的省的位置，直接定位到该位置
        if (-1 != provinceDefault) {
            mViewProvince.setCurrentItem(provinceDefault);
        }
        // 设置可见条目数量
        mViewProvince.setVisibleItems(visibleItems);
        mViewCity.setVisibleItems(visibleItems);
        mViewDistrict.setVisibleItems(visibleItems);
        mViewProvince.setCyclic(isProvinceCyclic);
        mViewCity.setCyclic(isCityCyclic);
        mViewDistrict.setCyclic(isDistrictCyclic);
        arrayWheelAdapter.setPadding(padding);
        arrayWheelAdapter.setTextColor(textColor);
        arrayWheelAdapter.setTextSize(textSize);


        updateCitys();
        updateArea();
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
    


    /**
     * 解析省市区的XML数据
     */

    protected void initProvinceData(Context context) {
        List<ProvinceModel> provinceList = null;
//        AssetManager asset = context.getAssets();
        try {
//            InputStream input = asset.open("province_data.xml");
//            // 创建一个解析xml的工厂对象
//            SAXParserFactory spf = SAXParserFactory.newInstance();
//            // 解析xml
//            SAXParser parser = spf.newSAXParser();
//            XmlParserHandler handler = new XmlParserHandler();
//            parser.parse(input, handler);
//            input.close();
//            // 获取解析出来的数据
//            provinceList = handler.getDataList();

            provinceList = JSON.parseArray(getJson(context),ProvinceModel.class);


            //*/ 初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProvice = provinceList.get(0);
                // TODO: 2018/3/20 0020 省份及其名称设置 ---- 001
                mCurrentProvice = provinceList.get(0);
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCity = cityList.get(0);
                    mCurrentCity = cityList.get(0);
                    List<DistrictModel> districtList = cityList.get(0).getCityList();
                    mCurrentDistrict = districtList.get(0);
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            //*/
            mProvinceData = new ProvinceModel[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceData[i] = provinceList.get(i);
                List<CityModel> cityList = provinceList.get(i).getCityList();
                CityModel[] cityNames = new CityModel[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j);
                    List<DistrictModel> districtList = cityList.get(j).getCityList();
                    DistrictModel[] distrinctNameArray = new DistrictModel[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(),
                                districtList.get(k).getZipcode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel;
                    }
                    // 市-区/县的数据，保存到mDistrictDataMap
                    mDistrictDataMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDataMap
                mCitisDataMap.put(provinceList.get(i), cityNames);
            }
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
        finally {

        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateArea() {
        int pCurrent = mViewCity.getCurrentItem();


        // TODO: 2018/3/20 0020 省份及其名称设置 ---- 002
        mCurrentCity = mCitisDataMap.get(mCurrentProvice)[pCurrent];
        DistrictModel[] districtmodels =  mDistrictDataMap.get(mCurrentCity);



        if (districtmodels == null) {
            districtmodels = new DistrictModel[] { null };
        }

        int districtDefault = -1;
        if (defaultDistrict != null && districtmodels.length > 0) {
            for (int i = 0; i < districtmodels.length; i++) {
                if (districtmodels[i].getId() == defaultDistrict.getId()) {
                    districtDefault = i;
                    break;
                }
            }
        }

        ArrayWheelAdapter districtWheel = new ArrayWheelAdapter<DistrictModel>(context, districtmodels);
        // 设置可见条目数量
        districtWheel.setTextColor(textColor);
        districtWheel.setTextSize(textSize);
        mViewDistrict.setViewAdapter(districtWheel);
        if (-1 != districtDefault) {
            mViewDistrict.setCurrentItem(districtDefault);
            //获取默认设置的区
            mCurrentDistrict = defaultDistrict;
        }
        else {
            mViewDistrict.setCurrentItem(0);
            //获取第一个区名称
            mCurrentDistrict = mDistrictDataMap.get(mCurrentCity)[0];

        }
        districtWheel.setPadding(padding);
        //获取第一个区名称
        mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrict);

    }


    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCitys() {
        int pCurrent = mViewProvince.getCurrentItem();

        mCurrentProvice = mProvinceData[pCurrent];
        CityModel[] citys = mCitisDataMap.get(mCurrentProvice);
        if (citys == null) {
            citys = new CityModel[] { null };
        }

        int cityDefault = -1;
        if (defaultCity != null && citys.length > 0) {
            for (int i = 0; i < citys.length; i++) {
                if (citys[i].getId() == defaultCity.getId()) {
                    cityDefault = i;
                    break;
                }
            }
        }

        ArrayWheelAdapter cityWheel = new ArrayWheelAdapter<CityModel>(context, citys);
        // 设置可见条目数量
        cityWheel.setTextColor(textColor);
        cityWheel.setTextSize(textSize);
        mViewCity.setViewAdapter(cityWheel);
        if (-1 != cityDefault) {
            mViewCity.setCurrentItem(cityDefault);
        }
        else {
            mViewCity.setCurrentItem(0);
        }

        cityWheel.setPadding(padding);
        updateArea();
    }
    
    @Override
    public void setType(int type) {
    }
    
    @Override
    public void show() {
        if (!isShow()) {
            setUpData();
            popwindow.showAtLocation(popview, Gravity.BOTTOM, 0, 0);
        }
    }
    
    @Override
    public void hide() {
        if (isShow()) {
            popwindow.dismiss();
        }
    }
    
    @Override
    public boolean isShow() {
        return popwindow.isShowing();
    }
    
    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {

            updateCitys();
        }
        else if (wheel == mViewCity) {
            updateArea();
        }
        else if (wheel == mViewDistrict) {

            mCurrentDistrict = mDistrictDataMap.get(mCurrentCity)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrict);

        }
    }
}
