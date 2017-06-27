package com.fupengpeng.viewpager.viewpager003.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fupengpeng.viewpager.R;
import com.fupengpeng.viewpager.viewpager003.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 个人中心Fragment
 */
public class PersonCenterFragment extends Fragment {


    private static final String TAG = "PersonCenterFragment";
    /**
     * 上下文
     */
    protected Context context = null;
    /**
     * 依附的MainActivity
     */
    protected Activity mainActivity = null;
    @BindView(R.id.iv_title_activity_left)
    ImageView ivTitleActivityLeft;
    @BindView(R.id.tv_title_activity_left)
    TextView tvTitleActivityLeft;
    @BindView(R.id.tv_title_activity_title)
    TextView tvTitleActivityTitle;
    @BindView(R.id.tv_title_activity_right)
    TextView tvTitleActivityRight;
    @BindView(R.id.iv_title_activity_right)
    ImageView ivTitleActivityRight;
    @BindView(R.id.iv_fragment_person_information_pic)
    ImageView ivFragmentPersonInformationPic;
    @BindView(R.id.tv_fragment_person_login_register)
    TextView tvFragmentPersonLoginRegister;
    @BindView(R.id.tv_fragment_person_information_username)
    TextView tvFragmentPersonInformationUsername;
    @BindView(R.id.tv_fragment_person_information_phone_number)
    TextView tvFragmentPersonInformationPhoneNumber;
    @BindView(R.id.ll_fragment_person_information_address)
    LinearLayout llFragmentPersonInformationAddress;
    @BindView(R.id.ll_fragment_person_information_person)
    LinearLayout llFragmentPersonInformationPerson;
    @BindView(R.id.btn_fragment_person_member_center)
    Button btnFragmentPersonMemberCenter;
    @BindView(R.id.tv_fragment_person_growth_value)
    TextView tvFragmentPersonGrowthValue;
    @BindView(R.id.iv_fragment_person_integral_center)
    ImageView ivFragmentPersonIntegralCenter;
    @BindView(R.id.tv_fragment_person_integral_center)
    TextView tvFragmentPersonIntegralCenter;
    @BindView(R.id.ll_fragment_person_integral_center)
    LinearLayout llFragmentPersonIntegralCenter;
    @BindView(R.id.tv_fragment_person_balance)
    TextView tvFragmentPersonBalance;
    @BindView(R.id.ll_fragment_person_balance)
    LinearLayout llFragmentPersonBalance;
    @BindView(R.id.btn_fragment_person_with_draw_deposit)
    Button btnFragmentPersonWithDrawDeposit;
    @BindView(R.id.tv_fragment_person_order_center_all)
    TextView tvFragmentPersonOrderCenterAll;
    @BindView(R.id.ll_fragment_person_order_center_all)
    LinearLayout llFragmentPersonOrderCenterAll;
    @BindView(R.id.ll_fragment_person_wait_payment)
    LinearLayout llFragmentPersonWaitPayment;
    @BindView(R.id.ll_fragment_person_wait_shipments)
    LinearLayout llFragmentPersonWaitShipments;
    @BindView(R.id.ll_fragment_person_wait_receiving)
    LinearLayout llFragmentPersonWaitReceiving;
    @BindView(R.id.ll_fragment_person_wait_picking)
    LinearLayout llFragmentPersonWaitPicking;
    @BindView(R.id.ll_fragment_person_refund_after_sales)
    LinearLayout llFragmentPersonRefundAfterSales;
    @BindView(R.id.ll_fragment_person_shopping_cart)
    LinearLayout llFragmentPersonShoppingCart;
    @BindView(R.id.ll_fragment_person_discount_coupon)
    LinearLayout llFragmentPersonDiscountCoupon;
    @BindView(R.id.ll_fragment_person_bargain)
    LinearLayout llFragmentPersonBargain;
    @BindView(R.id.ll_fragment_person_groupon)
    LinearLayout llFragmentPersonGroupon;
    @BindView(R.id.ll_fragment_person_winning)
    LinearLayout llFragmentPersonWinning;
    @BindView(R.id.ll_fragment_person_integral_shopping)
    LinearLayout llFragmentPersonIntegralShopping;


    private View personCenterFragmentView;

    private Intent intent;
    private int setFragment;
    private Bundle bundle;

    private Unbinder unbinder;

    /**
     * 获取Fragment依赖的MainActivity
     *
     * @return
     */
    public Activity getMainActivity() {
        mainActivity = (MainActivity) getActivity();
        context = mainActivity;
        return mainActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity();
        personCenterFragmentView = inflater.inflate(R.layout.fragment_person_center, container, false);
        unbinder = ButterKnife.bind(this, personCenterFragmentView);
        tvTitleActivityTitle.setText("个人中心");
        return personCenterFragmentView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
