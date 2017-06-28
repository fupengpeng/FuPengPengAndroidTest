package com.fupengpeng.secondlevellinkage.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fupengpeng.secondlevellinkage.R;
import com.fupengpeng.secondlevellinkage.bean.MenuData;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 要显示的adapter
 */
public class MenuDialogAdapter extends BaseAdapter {
    private Context mContext;
    private List<MenuData> menuDatas;
    private LayoutInflater mInflater = null;
    private int selectedPos = -1;
    private int mSelectedBackgroundResource;//选中item时的背景颜色
    private int mNormalBackgroundResource;//为选中的背景颜色
    private boolean hasDivider = true;

    public void setSelectedBackgroundResource(int mSelectedBackgroundResource) {
        this.mSelectedBackgroundResource = mSelectedBackgroundResource;
    }

    public void setNormalBackgroundResource(int mNormalBackgroundResource) {
        this.mNormalBackgroundResource = mNormalBackgroundResource;
    }

    public void setHasDivider(boolean hasDivider) {
        this.hasDivider = hasDivider;
    }

    public MenuDialogAdapter(Context mContext, List<MenuData> menuDatas) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.menuDatas = menuDatas;
    }



    //选中的position,及时更新数据
    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
        notifyDataSetChanged();
    }

    public void setData(List<MenuData> data) {
        this.menuDatas = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return menuDatas == null ? 0 : menuDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return menuDatas == null ? null : menuDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.layout_menu_item, null);


            viewHolder = new ViewHolder(convertView);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MenuData menuData = menuDatas.get(position);
        viewHolder.menuItemTextview.setText(menuData.name);//设置标题

        convertView.setSelected(selectedPos == position);//设置选中时的view
        viewHolder.menuItemTextview.setSelected(selectedPos == position);

        //选中后的标题字体颜色
        viewHolder.menuItemTextview.setTextColor(selectedPos == position ? 0xFF00B4C9 : 0xFF333333);
        //选中与未选中的背景色
        if (mNormalBackgroundResource == 0)
            mNormalBackgroundResource = R.color.white;

        if (mSelectedBackgroundResource != 0)
            viewHolder.menuItemLy.setBackgroundResource(selectedPos == position ? mSelectedBackgroundResource : mNormalBackgroundResource);

        //隐藏view
        viewHolder.menuItemDivider.setVisibility(hasDivider ? View.VISIBLE : View.INVISIBLE);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.menu_item_textview)
        TextView menuItemTextview;
        @BindView(R.id.menu_item_divider)
        TextView menuItemDivider;
        @BindView(R.id.menu_item_ly)
        LinearLayout menuItemLy;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


//    static class ViewHolder {
//        //无参构造
//        public ViewHolder() {
//        }
//
//        public static <T extends View> T get(View view, int id) {
//            SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();//j节省内存，提高性能，使用SparseArray
//            if (viewHolder == null) {
//                viewHolder = new SparseArray<View>();
//                view.setTag(viewHolder);
//            }
//            View childView = viewHolder.get(id);
//            if (childView == null) {
//                childView = view.findViewById(id);
//                viewHolder.put(id, childView);
//            }
//            return (T) childView;
//        }
//    }

}
