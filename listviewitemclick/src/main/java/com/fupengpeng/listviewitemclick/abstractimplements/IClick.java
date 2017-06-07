package com.fupengpeng.listviewitemclick.abstractimplements;

import android.view.View;

/**
 * Created by fupengpeng on 2017/6/5 0005.
 */

public abstract class IClick implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        listViewItemClick((Integer) v.getTag(), v);
    }

    public abstract void listViewItemClick(int position, View v);
}
