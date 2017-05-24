package com.fupengpeng.sugarorm.base;

import com.orm.SugarContext;

/**
 * Created by fupengpeng on 2017/5/24 0024.
 */

public class Application extends android.app.Application{
    /**
     * 这个方法在应用初始化的时候被调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
