package com.cqu.stuexpress.ui.homepage;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cqu.stuexpress.R;
import com.cqu.stuexpress.ui.base.FragmentWithOnResume;
import com.cqu.stuexpress.ui.base.TitleBarActivity;
import com.cqu.stuexpress.ui.homepage.order.PendingOrderFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends TitleBarActivity implements ViewPager.OnPageChangeListener {

    ViewPager mViewPager;
    ImageView ivTabLine;
    int curIndex = 0, pageIndex = 0;

    List<FragmentWithOnResume> mTabs = new ArrayList<>();
    FragmentPagerAdapter mAdapter;

    int tvItemIds[] = {R.id.tv_item1, R.id.tv_item2, R.id.tv_item3, R.id.tv_item4};
    int llItemIds[] = {R.id.ll_item1, R.id.ll_item2, R.id.ll_item3, R.id.ll_item4};

    int screenWidth, mPerScreenWidth, initLeftMargin = 40, tabCnt;
    LinearLayout.LayoutParams lp;

    @Override
    protected int getContentView() {
        return R.layout.activity_orders;
    }

    @Override
    public String getUITitle() {
        return "我的订单";
    }

    @Override
    public void init() {

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        ivTabLine = (ImageView) findViewById(R.id.iv_tabline);
        setFragments();

        initEvent();

    }

    private void initEvent() {
        for (int id : llItemIds) {
            View v = findViewById(id);
            if (v != null)
                v.setOnClickListener(this);
        }
    }

    private void setFragments() {
        mTabs.add(new PendingOrderFragment());
        mTabs.add(new PendingOrderFragment());
        mTabs.add(new PendingOrderFragment());
        mTabs.add(new PendingOrderFragment());

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }

        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);

        initScreenMetrics();

        // 选择第一个Item
        choose(curIndex);
    }

    private void initScreenMetrics() {
        // 移动tabline
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);

        tabCnt = mTabs.size();

        screenWidth = dpMetrics.widthPixels;

        mPerScreenWidth = screenWidth / tabCnt;//每个tab的宽度

        lp = (LinearLayout.LayoutParams) ivTabLine.getLayoutParams();
        lp.width = mPerScreenWidth - 2 * initLeftMargin;
    }

    void setTabLine(int i) {
        lp.leftMargin = i * screenWidth / tabCnt + initLeftMargin;
        ivTabLine.setLayoutParams(lp);
    }

    private void choose(int i) {
        // tab的变化
        resetView();

        if (i < tvItemIds.length) {

            TextView tv = (TextView) findViewById(tvItemIds[i]);

            if (tv != null)
                tv.setTextColor(getResources().getColor(R.color.main_color_deeper));
        }

        // 切换页面
        mViewPager.setCurrentItem(i, false);

        setTabLine(i);
    }

    private void resetView() {

        for (int i = 0; i < tvItemIds.length; i++) {

            TextView tv = (TextView) findViewById(tvItemIds[i]);

            if (tv != null)
                tv.setTextColor(getResources().getColor(R.color.text_color));
        }

    }

    @Override
    public void onPageScrolled(int position, float offset, int positionOffsetPixels) {
        /**
         * offset表示在当前页面偏离左边的距离，是一个0-->1的梯度值，可以用来做动画效果
         */
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ivTabLine
                .getLayoutParams();
        if (offset > 0) {
            lp.leftMargin = (int) (offset * mPerScreenWidth + position
                    * mPerScreenWidth + initLeftMargin);
        }

        ivTabLine.setLayoutParams(lp);

    }

    @Override
    public void onPageSelected(int position) {

//        choose(position);
        curIndex = position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        // 处理tab点击事件
        for (int i = 0; i < llItemIds.length; i++) {
            if (v.getId() == llItemIds[i]) {
                choose(i);
                break;
            }
        }

    }
}
