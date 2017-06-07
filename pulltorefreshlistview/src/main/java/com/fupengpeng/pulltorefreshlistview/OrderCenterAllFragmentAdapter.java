package com.fupengpeng.pulltorefreshlistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fupengpeng on 2017/6/7 0007.
 */

public class OrderCenterAllFragmentAdapter extends BaseAdapter{

    public static final String TAG = "OrderCenterAllFragmentAdapter";
    private LayoutInflater mInflater = null;
    private Context context;
    private List<Map<String, Object>> data;

    public OrderCenterAllFragmentAdapter(Context context, List<Map<String, Object>> data) {
        //根据context上下文加载布局，这里的是Activity本身，即this
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
        Log.e(TAG, "OrderCenterAllFragmentAdapter: " + "初始化适配器");
    }



    public void addFirst(List<Map<String, Object>> list){
        for (int i = 0; i < list.size() ; i++) {
            data.add(0,list.get(i));
        }
//        data.add(0, list.get(0));
    }

    public void addLast(List<Map<String, Object>> list){
        for (int i = 0; i < list.size() ; i++) {
            data.add(list.get(i));
        }
//        data.add( list.get(0));
    }



    @Override
    public int getCount() {
        //How many items are in the data set represented by this Adapter.
        //在此适配器中所代表的数据集中的条目数
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // Get the data item associated with the specified position in the data set.
        //获取数据集中与指定索引对应的数据项
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Get the row id associated with the specified position in the list.
        //获取在列表中与指定索引对应的行id
        return position;
    }

    //Get a View that displays the data at the specified position in the data set.
    //获取一个在数据集中指定索引的视图来显示数据
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.item_fragment_order_center_order_list, null);
            viewHolder = new ViewHolder(convertView);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Log.e(TAG, "getView: " + "适配器数据设置");
        viewHolder.tvItemFragmentOrderCenterOrderListOrderNumber.setText((String) data.get(position).get("orderCenterOrderListOrderNumber"));
        viewHolder.ivItemFragmentOrderCenterOrderListCommodityPic.setImageResource((Integer) data.get(position).get("orderCenterOrderListCommodityPic"));
        viewHolder.tvItemFragmentOrderCenterOrderListCommodityName.setText((String) data.get(position).get("orderCenterOrderListCommodityName"));
        viewHolder.tvItemFragmentOrderCenterOrderListCommodityQuantity.setText((String) data.get(position).get("orderCenterOrderListCommodityQuantity"));
        viewHolder.tvItemFragmentOrderCenterOrderListCommodityAggregatePrice.setText((String) data.get(position).get("orderCenterOrderListCommodityAggregatePrice"));


        viewHolder.tvItemFragmentOrderCenterOrderListOrderNumberExplain.setTag(position);
        viewHolder.tvItemFragmentOrderCenterOrderListOrderNumber.setTag(position);
        viewHolder.ivItemFragmentOrderCenterOrderListDeleteOrder.setTag(position);
        viewHolder.ivItemFragmentOrderCenterOrderListCommodityPic.setTag(position);
        viewHolder.tvItemFragmentOrderCenterOrderListCommodityName.setTag(position);
        viewHolder.tvItemFragmentOrderCenterOrderListCommodityQuantity.setTag(position);
        viewHolder.tvItemFragmentOrderCenterOrderListCommodityPay.setTag(position);
        viewHolder.tvItemFragmentOrderCenterOrderListCommodityAggregatePrice.setTag(position);
        viewHolder.vItemFragmentOrderCenterOrderListBlank.setTag(position);
        viewHolder.btnItemFragmentOrderCenterOrderListCommodityAgain.setTag(position);


//            viewHolder.tvItemFragmentOrderCenterOrderListOrderNumberExplain.setOnClickListener((View.OnClickListener) context);
//            viewHolder.tvItemFragmentOrderCenterOrderListOrderNumber.setOnClickListener((View.OnClickListener) context);
//            viewHolder.ivItemFragmentOrderCenterOrderListDeleteOrder.setOnClickListener((View.OnClickListener) context);
//            viewHolder.ivItemFragmentOrderCenterOrderListCommodityPic.setOnClickListener((View.OnClickListener) context);
//            viewHolder.tvItemFragmentOrderCenterOrderListCommodityName.setOnClickListener((View.OnClickListener) context);
//            viewHolder.tvItemFragmentOrderCenterOrderListCommodityQuantity.setOnClickListener((View.OnClickListener) context);
//            viewHolder.tvItemFragmentOrderCenterOrderListCommodityPay.setOnClickListener((View.OnClickListener) context);
//            viewHolder.tvItemFragmentOrderCenterOrderListCommodityAggregatePrice.setOnClickListener((View.OnClickListener) context);
//            viewHolder.vItemFragmentOrderCenterOrderListBlank.setOnClickListener((View.OnClickListener) context);
//            viewHolder.btnItemFragmentOrderCenterOrderListCommodityAgain.setOnClickListener((View.OnClickListener) context);


        return convertView;
    }


    //ViewHolder静态类
    class ViewHolder {
        @BindView(R.id.tv_item_fragment_order_center_order_list_order_number_explain)
        TextView tvItemFragmentOrderCenterOrderListOrderNumberExplain;
        @BindView(R.id.tv_item_fragment_order_center_order_list_order_number)
        TextView tvItemFragmentOrderCenterOrderListOrderNumber;
        @BindView(R.id.iv_item_fragment_order_center_order_list_delete_order)
        ImageView ivItemFragmentOrderCenterOrderListDeleteOrder;
        @BindView(R.id.iv_item_fragment_order_center_order_list_commodity_pic)
        ImageView ivItemFragmentOrderCenterOrderListCommodityPic;
        @BindView(R.id.tv_item_fragment_order_center_order_list_commodity_name)
        TextView tvItemFragmentOrderCenterOrderListCommodityName;
        @BindView(R.id.tv_item_fragment_order_center_order_list_commodity_quantity)
        TextView tvItemFragmentOrderCenterOrderListCommodityQuantity;
        @BindView(R.id.tv_item_fragment_order_center_order_list_commodity_pay)
        TextView tvItemFragmentOrderCenterOrderListCommodityPay;
        @BindView(R.id.tv_item_fragment_order_center_order_list_commodity_aggregate_price)
        TextView tvItemFragmentOrderCenterOrderListCommodityAggregatePrice;
        @BindView(R.id.v_item_fragment_order_center_order_list_blank)
        View vItemFragmentOrderCenterOrderListBlank;
        @BindView(R.id.btn_item_fragment_order_center_order_list_commodity_again)
        Button btnItemFragmentOrderCenterOrderListCommodityAgain;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
