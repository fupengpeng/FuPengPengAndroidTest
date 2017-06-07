package com.fupengpeng.butterknife.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by fupengpeng on 2017/5/31 0031.
 */

public abstract class BaseFragment extends Fragment {

    public abstract int getContentViewId();
    protected Context context;
    protected View mRootView;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView =inflater.inflate(getContentViewId(),container,false);
        unbinder = ButterKnife.bind(this,mRootView);//绑定framgent
        this.context = getActivity();
        initAllMembersView(savedInstanceState);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void initAllMembersView(Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();//解除绑定
    }


}
