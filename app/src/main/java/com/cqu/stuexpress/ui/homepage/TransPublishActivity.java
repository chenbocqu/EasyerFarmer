package com.cqu.stuexpress.ui.homepage;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cqu.stuexpress.R;
import com.cqu.stuexpress.ui.base.TitleBarActivity;
import com.cqu.stuexpress.utils.TextUtils;


public class TransPublishActivity extends TitleBarActivity {

    int llItemIds[] = {
            R.id.ll_express_take,
            R.id.ll_express_send,
            R.id.ll_book_return,
            R.id.ll_other_task,
    };

    String types[] = {
            "小面包车", "中面包车", "小货车", "中货车"
    };

    String weights[] = {
            "500公斤", "1吨", "1吨", "1.5吨"
    };

    String ckgs[] = {
            "1.8*1.3*1.1", "2.7*1.4*1.2", "2.7*1.5*1.7", "4.2*1.8*1.8"
    };
    String volumes[] = {
            "2.6方", "4.5方", "6.9方", "13.6方"
    };

    String type = types[0];

    @Override
    public String getUITitle() {
        return "发布货运";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_trans_publish;
    }

    EditText edtMark;
    TextView tvCnt;
    int maxLen = 50;

    @Override
    public void init() {
        // 注册点击事件
        for (int llItemId : llItemIds) registerClickListener(llItemId);

        registerClickListener(R.id.rl_from);
        registerClickListener(R.id.rl_to);
        edtMark = (EditText) findViewById(R.id.edt_mark);
        tvCnt = (TextView) findViewById(R.id.tv_cnt);

        TextUtils.with(this).restrictTextLenth(edtMark, maxLen, "最长不超过" + maxLen + "个字。", tvCnt);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        for (int i = 0; i < llItemIds.length; i++) {
            if (v.getId() == llItemIds[i])
                choose(i);
        }
    }

    private void choose(int index) {
        resetView();
        selected(index);
    }


    // unselect all
    void resetView() {
        for (int index = 0; index < llItemIds.length; index++)
            unSelectView(index);
    }

    void selected(int i) {

        View v = findViewById(llItemIds[i]);

        if (v == null) return;

        v.setBackgroundResource(R.drawable.item_selected);

        // 选择类型
        type = types[i];

        setText(R.id.tv_weight, weights[i]);
        setText(R.id.tv_chang, ckgs[i]);
        setText(R.id.tv_volume, volumes[i]);
    }

    void unSelectView(int i) {
        View v = findViewById(llItemIds[i]);
        if (v == null) return;
        v.setBackgroundResource(R.drawable.item_unselected);
    }
}
