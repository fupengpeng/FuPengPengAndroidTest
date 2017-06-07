package com.fupengpeng.butterknife.model.factory;


import com.fupengpeng.butterknife.model.impl.ButterKnifeModel;
import com.fupengpeng.butterknife.model.interf.IButterKnifeModel;

/**
 * Created by fupengpeng on 2017/5/26 0026.
 * 注册信息业务工厂
 */

public class ButterKnifeModelFactory {

    /**
     * 创建注册信息业务
     *
     * @return 注册信息业务
     */
    public static IButterKnifeModel newInstance() {
        return new ButterKnifeModel();
    }
}
