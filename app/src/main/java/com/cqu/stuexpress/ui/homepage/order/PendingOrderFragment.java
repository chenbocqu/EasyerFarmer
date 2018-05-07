package com.cqu.stuexpress.ui.homepage.order;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.cqu.stuexpress.R;
import com.cqu.stuexpress.bean.Order;
import com.cqu.stuexpress.ui.base.ComListFragment;
import com.cqu.stuexpress.view.refresh.PullToRefreshLayout;

import java.util.ArrayList;


public class PendingOrderFragment extends ComListFragment<Order> {

    ArrayList<Order> mData;

    @Override
    protected void init() {

        mData = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            mData.add(new Order());

        requestData();
    }

    private void requestData() {

    }

    @Override
    public CommonAdapter<Order> getAdapter() {
        return new CommonAdapter<Order>(getActivity(), R.layout.item_express_stu, mData) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, Order item, int position) {

            }
        };
    }

    @Override
    public void onRefresh(final PullToRefreshLayout l) {
        l.postDelayed(new Runnable() {
            @Override
            public void run() {
                l.refreshFinish(true);
            }
        }, 1000);
    }

    @Override
    public void onLoadMore(final PullToRefreshLayout l) {
        l.postDelayed(new Runnable() {
            @Override
            public void run() {
                l.loadMoreFinish(true);
            }
        }, 1000);
    }
}
