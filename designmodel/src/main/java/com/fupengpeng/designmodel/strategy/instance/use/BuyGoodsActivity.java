package com.fupengpeng.designmodel.strategy.instance.use;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fupengpeng.designmodel.R;
import com.fupengpeng.designmodel.strategy.instance.context.Customer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BuyGoodsActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @BindView(R.id.btn_buy_goods_001)
    Button btnBuyGoods001;
    @BindView(R.id.btn_buy_goods_002)
    Button btnBuyGoods002;
    @BindView(R.id.btn_buy_goods_003)
    Button btnBuyGoods003;
    @BindView(R.id.btn_buy_goods_004)
    Button btnBuyGoods004;
    @BindView(R.id.btn_buy_goods_005)
    Button btnBuyGoods005;
    @BindView(R.id.btn_buy_goods_006)
    Button btnBuyGoods006;
    private Customer customer;
    private String TAG = "BuyGoodsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_goods);
        unbinder = ButterKnife.bind(this);

        buyGoods();

    }

    private void buyGoods() {
        customer = new Customer();
    }

    @OnClick({R.id.btn_buy_goods_001, R.id.btn_buy_goods_002, R.id.btn_buy_goods_003, R.id.btn_buy_goods_004, R.id.btn_buy_goods_005, R.id.btn_buy_goods_006})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_buy_goods_001:
                customer.buy(50000d);
                Log.e(TAG, "buyGoods: 客户需要付钱:----" + customer.calLastAmount());
                break;
            case R.id.btn_buy_goods_002:
                customer.buy(50000d);
                Log.e(TAG, "buyGoods: 客户需要付钱:----" + customer.calLastAmount());
                break;
            case R.id.btn_buy_goods_003:
                customer.buy(50000d);
                Log.e(TAG, "buyGoods: 客户需要付钱:----" + customer.calLastAmount());
                break;
            case R.id.btn_buy_goods_004:
                customer.buy(50000d);
                Log.e(TAG, "buyGoods: 客户需要付钱:----" + customer.calLastAmount());
                break;
            case R.id.btn_buy_goods_005:
                customer.buy(50000d);
                Log.e(TAG, "buyGoods: 客户需要付钱:----" + customer.calLastAmount());
                break;
            case R.id.btn_buy_goods_006:
                customer.buy(50000d);
                Log.e(TAG, "buyGoods: 客户需要付钱:----" + customer.calLastAmount());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
