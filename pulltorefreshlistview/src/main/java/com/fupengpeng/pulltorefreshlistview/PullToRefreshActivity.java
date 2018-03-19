package com.fupengpeng.pulltorefreshlistview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PullToRefreshActivity extends ActionBarActivity {

    private static final String TAG = "PullToRefreshActivity";
    @BindView(R.id.ptrlv_fragment_order_center_all)
    PullToRefreshListView ptrlvFragmentOrderCenterAll;
    @BindView(R.id.ll_fragment_order_center_all)
    LinearLayout llFragmentOrderCenterAll;
    /**
     * Adapter
     */
    private OrderCenterAllFragmentAdapter orderCenterAllFragmentAdapter;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 总页数
     */
    private int totalPage ;

    /**
     * 数据源对象
     */
    private OrderCenterOrderList orderCenterOrderList;

    /**
     * 数据源
     */
    List<Map<String, Object>> list;

//    /**
//     * 数据源对象
//     */
//    private OrderCenterOrderList orderCenterOrderList;
//
//    private PullToRefreshListView pullToRefresh;
//
//    /**
//     * 数据源
//     */
//    List<Map<String, Object>> list;
//    OrderCenterAllFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        ButterKnife.bind(this);
        parseData();

        // 初始化
        init();
        // 设置监听器
        setListeners();
//
//        pullToRefresh = (PullToRefreshListView) findViewById(R.id.pullToRefresh);
//        list = getData();
//        adapter = new OrderCenterAllFragmentAdapter(this,list);
//        pullToRefresh.setAdapter(adapter);
//        pullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
//        init();
//		/*
//		 * Mode.BOTH：同时支持上拉下拉
//         * Mode.PULL_FROM_START：只支持下拉Pulling Down
//         * Mode.PULL_FROM_END：只支持上拉Pulling Up
//		 */
//		/*
//		 * 如果Mode设置成Mode.BOTH，需要设置刷新Listener为OnRefreshListener2，并实现onPullDownToRefresh()、onPullUpToRefresh()两个方法。
//         * 如果Mode设置成Mode.PULL_FROM_START或Mode.PULL_FROM_END，需要设置刷新Listener为OnRefreshListener，同时实现onRefresh()方法。
//         * 当然也可以设置为OnRefreshListener2，但是Mode.PULL_FROM_START的时候只调用onPullDownToRefresh()方法，
//         * Mode.PULL_FROM的时候只调用onPullUpToRefresh()方法.
//		 */
//		/*
//		 * setOnRefreshListener(OnRefreshListener listener):设置刷新监听器；
//		 * setOnLastItemVisibleListener(OnLastItemVisibleListener listener):设置是否到底部监听器；
//		 * setOnPullEventListener(OnPullEventListener listener);设置事件监听器；
//		 * onRefreshComplete()：设置刷新完成
//		 */
//		/*
//		 * pulltorefresh.setOnScrollListener()
//		 */
//        // SCROLL_STATE_TOUCH_SCROLL 正在滚动
//        // SCROLL_STATE_FLING 手指做了抛的动作（手指离开屏幕前，用力滑了一下）
//        // SCROLL_STATE_IDLE 停止滚动
//		/*
//		 * setOnLastItemVisibleListener
//		 * 当用户拉到底时调用
//		 */
//		/*
//		 * setOnTouchListener是监控从点下鼠标 （可能拖动鼠标）到放开鼠标（鼠标可以换成手指）的整个过程 ，他的回调函数是onTouchEvent（MotionEvent event）,
//		 * 然后通过判断event.getAction()是MotionEvent.ACTION_UP还是ACTION_DOWN还是ACTION_MOVE分别作不同行为。
//         * setOnClickListener的监控时间只监控到手指ACTION_DOWN时发生的行为
//		 */
//
//        pullToRefresh.setOnRefreshListener(
//                new PullToRefreshBase.OnRefreshListener2<ListView>(){
//            @Override
//            public void onPullDownToRefresh(
//                    PullToRefreshBase<ListView> refreshView) {
//                // TODO Auto-generated method stub
//
//                OrderCenterOrderList ocola = new OrderCenterOrderList();
//                List<OrderCenterOrderObject> orderCenterOrderObjects = new ArrayList<OrderCenterOrderObject>();
//                for (int i = 0; i < 3; i++) {
//                    OrderCenterOrderObject orderCenterOrderObject = new OrderCenterOrderObject();
//                    orderCenterOrderObject.setOrderCenterOrderListOrderNumber("订单号：----下拉刷新----" + i + "--------");
//                    orderCenterOrderObject.setOrderCenterOrderListCommodityPic(R.drawable.nvshengtouxiang);
//                    orderCenterOrderObject.setOrderCenterOrderListCommodityName("商品名称：----下拉刷新----" + i + "--------");
//                    orderCenterOrderObject.setOrderCenterOrderListCommodityQuantity("商品数量：----下拉刷新----" + i + "--------");
//                    orderCenterOrderObject.setOrderCenterOrderListCommodityAggregatePrice("商品价格：----下拉刷新----" + i + "---------");
//                    orderCenterOrderObjects.add(orderCenterOrderObject);
//                }
//                ocola.setOrderCenterOrderObjectList(orderCenterOrderObjects);
//
//                List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
//                Map<String, Object> map;
//                for (int i = 0; i < ocola.getOrderCenterOrderObjectList().size(); i++) {
//                    map = new HashMap<String, Object>();
//                    map.put("orderCenterOrderListOrderNumber", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListOrderNumber());
//                    map.put("orderCenterOrderListCommodityPic", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityPic());
//                    map.put("orderCenterOrderListCommodityName", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityName());
//                    map.put("orderCenterOrderListCommodityQuantity", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityQuantity());
//                    map.put("orderCenterOrderListCommodityAggregatePrice", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityAggregatePrice());
//                    lista.add(map);
//                }
//
//                adapter.addFirst(lista);
//                new FinishRefresh().execute();
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onPullUpToRefresh(
//                    PullToRefreshBase<ListView> refreshView) {
//                // TODO Auto-generated method stub
//                OrderCenterOrderList ocol = new OrderCenterOrderList();
//                List<OrderCenterOrderObject> orderCenterOrderObjects = new ArrayList<OrderCenterOrderObject>();
//                for (int i = 0; i < 2; i++) {
//                    OrderCenterOrderObject orderCenterOrderObject = new OrderCenterOrderObject();
//                    orderCenterOrderObject.setOrderCenterOrderListOrderNumber("订单号：----上拉加载----" + i + "--------");
//                    orderCenterOrderObject.setOrderCenterOrderListCommodityPic(R.drawable.nvshengtouxiang);
//                    orderCenterOrderObject.setOrderCenterOrderListCommodityName("商品名称：----上拉加载----" + i + "--------");
//                    orderCenterOrderObject.setOrderCenterOrderListCommodityQuantity("商品数量：----上拉加载----" + i + "--------");
//                    orderCenterOrderObject.setOrderCenterOrderListCommodityAggregatePrice("商品价格：----上拉加载----" + i + "---------");
//                    orderCenterOrderObjects.add(orderCenterOrderObject);
//                }
//                ocol.setOrderCenterOrderObjectList(orderCenterOrderObjects);
//
//                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//                Map<String, Object> map;
//                for (int i = 0; i < ocol.getOrderCenterOrderObjectList().size(); i++) {
//                    map = new HashMap<String, Object>();
//                    map.put("orderCenterOrderListOrderNumber", ocol.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListOrderNumber());
//                    map.put("orderCenterOrderListCommodityPic", ocol.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityPic());
//                    map.put("orderCenterOrderListCommodityName", ocol.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityName());
//                    map.put("orderCenterOrderListCommodityQuantity", ocol.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityQuantity());
//                    map.put("orderCenterOrderListCommodityAggregatePrice", ocol.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityAggregatePrice());
//                    list.add(map);
//                }
//
//
//                adapter.addLast(list);
//                new FinishRefresh().execute();
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//    }


    }

    /**
     * 初始化
     */
    private void init() {
        Log.e(TAG, "init: " + "初始化");

        // 绑定数据
        bindData();
        // 设置加载模式
        PullToRefreshUtils.setPullBoth(ptrlvFragmentOrderCenterAll);
        // 刷新数据
        refreshData(1);
    }

    /**
     * 设置监听器
     */
    private void setListeners() {
        // 刷新监听器
        ptrlvFragmentOrderCenterAll.setOnRefreshListener(new InnerOnRefreshListener2());


        // 子项点击监听器
        ptrlvFragmentOrderCenterAll.setOnItemClickListener(new InnerOnItemClickListener());
    }

    /**
     * 刷新监听器
     */
    private class InnerOnRefreshListener2 implements PullToRefreshBase.OnRefreshListener2 {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            // 下拉刷新
            refreshData(1);

            OrderCenterOrderList ocola = new OrderCenterOrderList();
            List<OrderCenterOrderObject> orderCenterOrderObjects = new ArrayList<OrderCenterOrderObject>();
            for (int i = 0; i < 3; i++) {
                OrderCenterOrderObject orderCenterOrderObject = new OrderCenterOrderObject();
                orderCenterOrderObject.setOrderCenterOrderListOrderNumber("订单号：----增加数据----" + i + "--------");
                orderCenterOrderObject.setOrderCenterOrderListCommodityPic(R.drawable.nvshengtouxiang);
                orderCenterOrderObject.setOrderCenterOrderListCommodityName("商品名称：----下拉刷新----" + i + "--------");
                orderCenterOrderObject.setOrderCenterOrderListCommodityQuantity("商品数量：----下拉刷新----" + i + "--------");
                orderCenterOrderObject.setOrderCenterOrderListCommodityAggregatePrice("商品价格：----下拉刷新----" + i + "---------");
                orderCenterOrderObjects.add(orderCenterOrderObject);
            }
            ocola.setOrderCenterOrderObjectList(orderCenterOrderObjects);

            List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
            Map<String, Object> map;
            for (int i = 0; i < ocola.getOrderCenterOrderObjectList().size(); i++) {
                map = new HashMap<String, Object>();
                map.put("orderCenterOrderListOrderNumber", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListOrderNumber());
                map.put("orderCenterOrderListCommodityPic", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityPic());
                map.put("orderCenterOrderListCommodityName", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityName());
                map.put("orderCenterOrderListCommodityQuantity", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityQuantity());
                map.put("orderCenterOrderListCommodityAggregatePrice", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityAggregatePrice());
                lista.add(map);
            }

            orderCenterAllFragmentAdapter.addFirst(lista);
            new FinishRefresh().execute();
            orderCenterAllFragmentAdapter.notifyDataSetChanged();
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {



            totalPage = 5;
            // 判断是否还有更多数据
            if (currentPage >= totalPage) {
                Toast.makeText(PullToRefreshActivity.this, "没有更多数据", Toast.LENGTH_SHORT).show();
                // 加载完成
                PullToRefreshUtils.refreshComplete(ptrlvFragmentOrderCenterAll);
                return;
            }
            // 上拉加载
            currentPage++;
            refreshData(currentPage);

            OrderCenterOrderList ocola = new OrderCenterOrderList();
            List<OrderCenterOrderObject> orderCenterOrderObjects = new ArrayList<OrderCenterOrderObject>();
            for (int i = 0; i < 3; i++) {
                OrderCenterOrderObject orderCenterOrderObject = new OrderCenterOrderObject();
                orderCenterOrderObject.setOrderCenterOrderListOrderNumber("订单号：----增加数据----" + i + "--------");
                orderCenterOrderObject.setOrderCenterOrderListCommodityPic(R.drawable.nvshengtouxiang);
                orderCenterOrderObject.setOrderCenterOrderListCommodityName("商品名称：----sssssss----" + i + "--------");
                orderCenterOrderObject.setOrderCenterOrderListCommodityQuantity("商品数量：----sssssss----" + i + "--------");
                orderCenterOrderObject.setOrderCenterOrderListCommodityAggregatePrice("商品价格：----sssssss----" + i + "---------");
                orderCenterOrderObjects.add(orderCenterOrderObject);
            }
            ocola.setOrderCenterOrderObjectList(orderCenterOrderObjects);

            List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
            Map<String, Object> map;
            for (int i = 0; i < ocola.getOrderCenterOrderObjectList().size(); i++) {
                map = new HashMap<String, Object>();
                map.put("orderCenterOrderListOrderNumber", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListOrderNumber());
                map.put("orderCenterOrderListCommodityPic", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityPic());
                map.put("orderCenterOrderListCommodityName", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityName());
                map.put("orderCenterOrderListCommodityQuantity", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityQuantity());
                map.put("orderCenterOrderListCommodityAggregatePrice", ocola.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityAggregatePrice());
                lista.add(map);
            }
            orderCenterAllFragmentAdapter.addLast(lista);
            new FinishRefresh().execute();
            orderCenterAllFragmentAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 子项点击监听器
     */
    public class InnerOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 数据源当前索引
            int pos = position - 1;

        }
    }

    /**
     * 绑定数据
     */
    private void bindData() {
        Log.e(TAG, "bindData: " + "绑定数据");
        list = getData();
//        setData(list,2);
        orderCenterAllFragmentAdapter = new OrderCenterAllFragmentAdapter(this, list);
        ptrlvFragmentOrderCenterAll.setAdapter(orderCenterAllFragmentAdapter);
    }

    /**
     * 刷新数据
     */
    private void refreshData(int page) {
        currentPage = page;
        // 加载数据



    }

    /**
     * 设置数据
     *
     * @param datas 数据
     * @param tp    总页数 （通常数据和总页数以一个对象传过来，这里举例子分开传递的）
     */
    public void setData(List<Map<String, Object>> datas, int tp) {
        // 判断是否存在数据
        if (datas == null || datas.size() == 0) {
            // 显示提示
            llFragmentOrderCenterAll.setVisibility(View.VISIBLE);
            Log.e(TAG, "setData: "+"3333333333333333333" );
            // 清空现有数据
            list.clear();
//            orderCenterAllFragmentAdapter.notifyDataSetChanged();
            // 调用刷新完成
            PullToRefreshUtils.refreshComplete(ptrlvFragmentOrderCenterAll);
            return;
        }

        // 隐藏提示
        llFragmentOrderCenterAll.setVisibility(View.GONE);

        // 设置总页数
        totalPage = tp;

        // 刷新数据
        if (currentPage == 1) {
            list.clear();
        }
        list.addAll(datas);
        Log.e(TAG, "setData: "+"sssssssssssss" );
//        orderCenterAllFragmentAdapter.notifyDataSetChanged();

        // 调用刷新完成
        PullToRefreshUtils.refreshComplete(ptrlvFragmentOrderCenterAll);
    }


    private void parseData() {
        orderCenterOrderList = new OrderCenterOrderList();
        List<OrderCenterOrderObject> orderCenterOrderObjects = new ArrayList<OrderCenterOrderObject>();
        for (int i = 0; i < 5; i++) {
            OrderCenterOrderObject orderCenterOrderObject = new OrderCenterOrderObject();
            orderCenterOrderObject.setOrderCenterOrderListOrderNumber("订单号：---" + i + "---");
            orderCenterOrderObject.setOrderCenterOrderListCommodityPic(R.drawable.nvshengtouxiang);
            orderCenterOrderObject.setOrderCenterOrderListCommodityName("商品名称：---" + i + "---");
            orderCenterOrderObject.setOrderCenterOrderListCommodityQuantity("商品数量：---" + i + "---");
            orderCenterOrderObject.setOrderCenterOrderListCommodityAggregatePrice("商品价格：---" + i + "---");
            orderCenterOrderObjects.add(orderCenterOrderObject);
        }
        orderCenterOrderList.setOrderCenterOrderObjectList(orderCenterOrderObjects);
        Log.e(TAG, "parseData: " + orderCenterOrderList.getOrderCenterOrderObjectList().get(2).getOrderCenterOrderListCommodityName().toString().trim());

    }

    private List<Map<String, Object>> getData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i = 0; i < orderCenterOrderList.getOrderCenterOrderObjectList().size(); i++) {
            map = new HashMap<String, Object>();
            map.put("orderCenterOrderListOrderNumber", orderCenterOrderList.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListOrderNumber());
            map.put("orderCenterOrderListCommodityPic", orderCenterOrderList.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityPic());
            map.put("orderCenterOrderListCommodityName", orderCenterOrderList.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityName());
            map.put("orderCenterOrderListCommodityQuantity", orderCenterOrderList.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityQuantity());
            map.put("orderCenterOrderListCommodityAggregatePrice", orderCenterOrderList.getOrderCenterOrderObjectList().get(i).getOrderCenterOrderListCommodityAggregatePrice());
            list.add(map);
        }
        return list;
    }

//    private void init() {
//
//
//        // 设置下拉刷新文本
//        pullToRefresh.getLoadingLayoutProxy(false, true).setPullLabel("上拉刷新...");
//        pullToRefresh.getLoadingLayoutProxy(false, true).setReleaseLabel("放开刷新...");
//        pullToRefresh.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
//
//        // 设置上拉刷新文本
//        pullToRefresh.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新...");
//        pullToRefresh.getLoadingLayoutProxy(true, false).setReleaseLabel("放开刷新...");
//        pullToRefresh.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在加载...");
//    }

    private class FinishRefresh extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//        	adapter.notifyDataSetChanged();
            ptrlvFragmentOrderCenterAll.onRefreshComplete();
        }
    }


}
