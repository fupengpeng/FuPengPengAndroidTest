package com.fupengpeng.butterknife.presenter.impl;

import android.app.Activity;
import android.content.Intent;

import com.fupengpeng.butterknife.activity.interf.IButterKnifeView;
import com.fupengpeng.butterknife.model.factory.ButterKnifeModelFactory;
import com.fupengpeng.butterknife.model.interf.IButterKnifeModel;
import com.fupengpeng.butterknife.presenter.interf.IButterKnifePresenter;

/**
 * Created by fupengpeng on 2017/5/31 0031.
 */

public class ButterKnifePresenter implements IButterKnifePresenter{
    private IButterKnifeView butterKnifeView;
    private IButterKnifeModel butterKnifeModel;
    /**
     * Activity
     */
    protected Activity activity;
    public ButterKnifePresenter(IButterKnifeView butterKnifeView){
        activity = (Activity) butterKnifeView;
        this.butterKnifeView = butterKnifeView;
        this.butterKnifeModel = ButterKnifeModelFactory.newInstance();

    }

}
