package com.cqu.stuexpress.ui.zone;


import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.cqu.stuexpress.R;
import com.cqu.stuexpress.bean.Record;
import com.cqu.stuexpress.ui.base.ComListActivity;
import com.cqu.stuexpress.view.refresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class ZoneActivity extends ComListActivity<Record> {

    List<Record> mData;


    @Override
    public String getUITitle() {
        return "校园动态";
    }

    @Override
    protected int getHeadLayout() {
        return R.layout.zone_header_view;
    }

    @Override
    public void init() {
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) mData.add(new Record());
    }


    @Override
    public CommonAdapter<Record> getAdapter() {
        return new CommonAdapter<Record>(this, R.layout.item_express_stu, mData) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, Record item, int position) {

            }
        };
    }

    @Override
    public void onRefresh(PullToRefreshLayout l) {
        l.refreshFinish(true);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout l) {
        l.loadMoreFinish(true);
    }
}
