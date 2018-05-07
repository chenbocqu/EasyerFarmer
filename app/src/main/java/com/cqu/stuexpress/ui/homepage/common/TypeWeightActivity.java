package com.cqu.stuexpress.ui.homepage.common;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import com.cqu.stuexpress.R;
import com.cqu.stuexpress.ui.base.TitleBarActivity;


public class TypeWeightActivity extends TitleBarActivity {

    public static final String KEY_WEIGHT = "KEY_WEIGHT";
    public static final String KEY_TYPE = "KEY_TYPE";
    int tvItemIds[] = {
            R.id.tv_item1,
            R.id.tv_item2,
            R.id.tv_item3,
            R.id.tv_item4,
            R.id.tv_item5,
            R.id.tv_item6
    };

    String types[] = {
            "日用品", "数码产品", "衣物", "食物", "文件", "其他"
    };

    int weight = 1, maxWeight = 100;
    String type;

    @Override
    public String getUITitle() {
        return "类型及重量";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_type_weight;
    }

    @Override
    public void init() {

        registerClickListener(R.id.btn_ok);
        registerClickListener(R.id.edt_price);
        registerClickListener(R.id.iv_desc);
        registerClickListener(R.id.iv_increase);

        for (int i = 0; i < tvItemIds.length; i++)
            registerClickListener(tvItemIds[i]);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {

            case R.id.btn_ok:
                save();
                break;

            case R.id.iv_desc:

                if (weight <= 1) return;
                weight--;
                setText(R.id.tv_weight, weight + "");

                break;

            case R.id.iv_increase:

                if (weight >= maxWeight) {
                    myTool.showInfo("最多不超过" + maxWeight + "Kg");
                    return;
                }
                weight++;
                setText(R.id.tv_weight, weight + "");

                break;
        }

        for (int i = 0; i < tvItemIds.length; i++) {
            if (v.getId() == tvItemIds[i])
                choose(i);
        }
    }

    private void save() {
        if (type == null) {
            myTool.showInfo("请先选择类型。");
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(KEY_WEIGHT, weight);
        intent.putExtra(KEY_TYPE, type);

        setResult(RESULT_OK, intent);

        finish();
    }

    private void choose(int i) {
        resetView();
        // select one
        selected(i);
    }

    // unselect all
    void resetView() {
        for (int index = 0; index < tvItemIds.length; index++)
            unSelectView(index);
    }

    void selected(int i) {

        TextView tv = (TextView) findViewById(tvItemIds[i]);

        if (tv == null) return;

        tv.setBackgroundResource(R.drawable.item_selected);
        tv.setTextColor(Color.parseColor("#019978"));
        TextPaint tp1 = tv.getPaint();
        tp1.setFakeBoldText(true);

        // 选择类型
        type = types[i];
    }

    void unSelectView(int i) {

        TextView tv = (TextView) findViewById(tvItemIds[i]);
        if (tv == null) return;

        tv.setBackgroundResource(R.drawable.item_unselected);
        tv.setTextColor(Color.parseColor("#888888"));
        TextPaint tp1 = tv.getPaint();
        tp1.setFakeBoldText(false);
    }
}
