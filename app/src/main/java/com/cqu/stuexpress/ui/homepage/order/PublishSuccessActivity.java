package com.cqu.stuexpress.ui.homepage.order;


import android.view.View;

import com.cqu.stuexpress.R;
import com.cqu.stuexpress.bean.Order;
import com.cqu.stuexpress.ui.base.TitleBarActivity;
import com.cqu.stuexpress.ui.homepage.OrderActivity;

public class PublishSuccessActivity extends TitleBarActivity {

    Order mOrder;

    @Override
    public String getUITitle() {
        return "成功";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_publish_success;
    }

    @Override
    public void init() {

        mOrder = (Order) myTool.getParam(Order.class);
        registerClickListener(R.id.tv_myorder);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_myorder:
                myTool.startActivity(OrderActivity.class);
                break;
        }
    }
}
