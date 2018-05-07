package com.cqu.stuexpress.ui.homepage.common;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.cqu.stuexpress.R;
import com.cqu.stuexpress.ui.base.TitleBarActivity;
import com.cqu.stuexpress.utils.TimePickerUtil;

import java.util.Date;


public class ChooseTimeActivity extends TitleBarActivity {

    public static final String KEY_TIME = "KEY_TIME";

    int tvItemIds[] = {
            R.id.tv_item1,
            R.id.tv_item2,
            R.id.tv_item3,
            R.id.tv_item4,
            R.id.tv_item5,
            R.id.tv_item6,
            R.id.tv_item7,
            R.id.tv_item8
    };

    String times[] = {
            "随时", "8:00~10:00", "10:00~12:00", "12:00~14:00",
            "14:00~16:00", "16:00~18:00", "18:00~20:00", "20:00~22:00"
    };

    String time, startTime, endTime;
    Date mStartDate, mEndDate;

    @Override
    public String getUITitle() {
        return "期望配送时间";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_choose_time;
    }

    @Override
    public void init() {

        registerClickListener(R.id.btn_ok);
        registerClickListener(R.id.tv_start_time);
        registerClickListener(R.id.tv_end_time);

        for (int i = 0; i < tvItemIds.length; i++) {
            registerClickListener(tvItemIds[i]);
            setText(tvItemIds[i], i < times.length ? times[i] : "null");
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {

            case R.id.btn_ok:
                save();
                break;

            case R.id.tv_start_time:

                time = null;
                resetView();

                TimePickerUtil.pickTimeDialog(this, "起始时间", mStartDate, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mStartDate = date;
                        startTime = mStartDate.getHours() + ":" +
                                (mStartDate.getMinutes() > 9 ? mStartDate.getMinutes() : ("0" + mStartDate.getMinutes()));

                        setText(R.id.tv_start_time, startTime);
                    }
                });
                break;

            case R.id.tv_end_time:

                time = null;
                resetView();

                TimePickerUtil.pickTimeDialog(this, "起始时间", mEndDate, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mEndDate = date;
                        endTime = mEndDate.getHours() + ":" +
                                (mEndDate.getMinutes() > 9 ? mEndDate.getMinutes() : ("0" + mEndDate.getMinutes()));

                        setText(R.id.tv_end_time, endTime);
                    }
                });
                break;
        }

        for (int i = 0; i < tvItemIds.length; i++) {
            if (v.getId() == tvItemIds[i])
                choose(i);
        }
    }

    private void save() {

        if (startTime != null && endTime != null)
            time = startTime + "~" + endTime;

        if (time == null) {
            myTool.showInfo("请先选择时间。");
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(KEY_TIME, time);

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
        time = times[i];
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
