package com.cqu.stuexpress.ui.homepage;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cqu.stuexpress.R;
import com.cqu.stuexpress.bean.ContactAddress;
import com.cqu.stuexpress.bean.Order;
import com.cqu.stuexpress.bean.TakeMsg;
import com.cqu.stuexpress.bean.User;
import com.cqu.stuexpress.listener.PopwindowListener;
import com.cqu.stuexpress.ui.address.AddressManageActivity;
import com.cqu.stuexpress.ui.base.TitleBarActivity;
import com.cqu.stuexpress.ui.homepage.common.ChooseTimeActivity;
import com.cqu.stuexpress.ui.homepage.common.TypeWeightActivity;
import com.cqu.stuexpress.ui.homepage.exptake.Dialog4Mark;
import com.cqu.stuexpress.ui.homepage.exptake.Dialog4SelectPrice;
import com.cqu.stuexpress.ui.homepage.exptake.SmsSelectedActivity;
import com.cqu.stuexpress.ui.homepage.order.PublishSuccessActivity;
import com.cqu.stuexpress.utils.DataMaker;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;

/**
 * 帮我取快递页面
 */

public class ExpressTakeActivity extends TitleBarActivity implements PopwindowListener<Object, Object> {

    public static final int REQUEST_RECEIVE_ADDRESS = 1;
    public static final int REQUEST_TAKE_MSG = 2;       // 请求姓名，手机，短消息
    private static final int REQUEST_TYPE_WEIGHT = 3;   // 请求类型和重量
    private static final int REQUEST_TIME = 4;

    RelativeLayout rlTakeMsg;
    Button btnTakeExp;
    Dialog4SelectPrice dialog4SelectPrice;
    Dialog4Mark dialog4Remark;
    RelativeLayout rlPrice, rlRemark;
    int mPrice = 3, mWeight;
    String remark, mType, mTime;
    TextView tvPrice, tvRemark;
    private WindowManager.LayoutParams params;
    ContactAddress mReceiveAdd;
    Order mOrder;

    TakeMsg mTakeMsg;
    SweetAlertDialog mDialog;

    @Override
    protected int getContentView() {
        return R.layout.activity_express_take;
    }

    @Override
    public String getUITitle() {
        return "帮我取";
    }

    @Override
    public void init() {

        mTakeMsg = new TakeMsg();
        mOrder = new Order();

        viewById();
        initEvents();
    }

    private void initEvents() {

        rlTakeMsg.setOnClickListener(this);

        registerClickListener(R.id.rl_receive_address);
        registerClickListener(R.id.rl_type_weight);
        registerClickListener(R.id.rl_predict_time);

        btnTakeExp.setOnClickListener(this);
        rlPrice.setOnClickListener(this);
        rlRemark.setOnClickListener(this);
        dialog4Remark.setOnPowwinListener(this);
        dialog4SelectPrice.setOnPowwinListener(this);
    }

    private void viewById() {
        rlTakeMsg = (RelativeLayout) findViewById(R.id.rl_take_msg);
        btnTakeExp = (Button) findViewById(R.id.btn_take_exp);
        rlPrice = (RelativeLayout) findViewById(R.id.rl_price);
        rlRemark = (RelativeLayout) findViewById(R.id.rl_remark);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvRemark = (TextView) findViewById(R.id.tv_remark);

        dialog4SelectPrice = new Dialog4SelectPrice(this, mPrice);
        dialog4Remark = new Dialog4Mark(this, R.style.Translucent_NoTitle);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_take_msg:
                myTool.startActivityForResult(mTakeMsg, SmsSelectedActivity.class, REQUEST_TAKE_MSG);
                break;

            case R.id.rl_receive_address:
                myTool.startActivityForResult(AddressManageActivity.class, REQUEST_RECEIVE_ADDRESS);
                break;

            case R.id.rl_predict_time:
                myTool.startActivityForResult(ChooseTimeActivity.class, REQUEST_TIME);
                break;

            case R.id.rl_type_weight:
                myTool.startActivityForResult(TypeWeightActivity.class, REQUEST_TYPE_WEIGHT);
                break;

            case R.id.rl_price:
                myTool.showPopFormBottom(dialog4SelectPrice, findViewById(R.id.ll_main));
                break;
            case R.id.rl_remark:
                dialog4Remark.show();
                break;
            case R.id.btn_take_exp:
                pay();
                break;

        }
    }

    private void pay() {

        mDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        mDialog.setTitleText("正在支付...");
        mDialog.show();

        User mUser = myTool.getUserInfo();

        if (mUser == null) {
            DataMaker.updateUserInfo(this);
            showPayErrInfo("获取余额信息失败，请稍后重试！");
            return;
        }

        double updateVal = mUser.getBalance() - mPrice;

        if (updateVal < 0) {
            showPayErrInfo("余额不足，请充值后再发布！");
            return;
        }

        OkHttpUtils.post().url(myTool.getServAdd() + "updateUser.do")
                .addParams("exuserID", myTool.getUserId())
                .addParams("item", "remainingSum")
                .addParams("value", updateVal + "")
                .build()
                .execute(new StringCallback() {
                             @Override
                             public void onError(Call call, Exception e, int id) {
                                 myTool.log("Update balance err = " + e.getMessage());
                                 myTool.showInfo("获取余额信息失败，请稍后重试！");
                             }

                             @Override
                             public void onResponse(String response, int id) {

                                 myTool.log("Update balance response = " + response);

                                 try {
                                     JSONObject obj = new JSONObject(response);
                                     String state = obj.getString("state");

                                     switch (state) {
                                         case "success":
                                             publish();
                                             break;
                                         case "error":
                                             showPayErrInfo("获取余额信息失败，请稍后重试！");
                                             break;
                                     }
                                 } catch (JSONException e) {
                                     showPayErrInfo(e.getMessage());
                                 }
                             }
                         }
                );
    }

    private void publish() {

        DataMaker.updateUserInfo(this);

        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
                myTool.startActivity(mOrder, PublishSuccessActivity.class);
            }
        });
        mDialog.dismissWithAnimation();
    }

    private void showPayErrInfo(String info) {
        mDialog.setTitleText("支付失败")
                .setContentText(info)
                .setConfirmText("重试")
                .setCancelText("取消")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        pay();
                    }
                })
                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
    }

    @Override
    public void infoChanged(Object w, Object obj) {
        if ("Dialog4Mark".equals(w.getClass().getSimpleName())) {
            remark = (String) obj;
            tvRemark.setText(remark);
        }
        if ("Dialog4SelectPrice".equals(w.getClass().getSimpleName())) {
            mPrice = (int) obj;
            tvPrice.setText(mPrice + "元");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 请求收货地址
        switch (requestCode) {
            // 请求收货地址
            case REQUEST_RECEIVE_ADDRESS:

                if (resultCode == RESULT_OK) {
                    mReceiveAdd = (ContactAddress) data.getSerializableExtra(AddressManageActivity.ADDRESS);

                    setText(R.id.tv_receive_name_phone, mReceiveAdd.getContactName() + " " + mReceiveAdd.getPhone());
                    setText(R.id.tv_receive_address, mReceiveAdd.getAddressDesc());
                }
                break;

            case REQUEST_TYPE_WEIGHT:
                if (resultCode == RESULT_OK) {

                    mType = data.getStringExtra(TypeWeightActivity.KEY_TYPE);
                    mWeight = data.getIntExtra(TypeWeightActivity.KEY_WEIGHT, 1);
                    setText(R.id.tv_type_weight, mType + " " + mWeight + "kg");
                }
                break;

            case REQUEST_TIME:

                if (resultCode == RESULT_OK) {

                    mTime = data.getStringExtra(ChooseTimeActivity.KEY_TIME);
                    setText(R.id.tv_time, mTime);
                }

                break;
        }
    }
}
