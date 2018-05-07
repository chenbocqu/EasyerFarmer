package com.cqu.stuexpress.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.classic.adapter.CommonAdapter;
import com.cqu.stuexpress.R;
import com.cqu.stuexpress.view.refresh.PullToRefreshLayout;

import java.util.ArrayList;

public abstract class ComListFragment<T> extends FragmentWithOnResume implements PullToRefreshLayout.OnRefreshListener {

    ArrayList<T> mData;
    protected CommonAdapter<T> mAdapter;

    ListView mListView;
    PullToRefreshLayout pullToRefreshLayout;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_common_list, container, false);

        _initView();
        _initEvent();

        _initData();
        resetView();

        return view;
    }

    private void _initData() {

        // 供外部初始化
        init();

        mAdapter = getAdapter();
        mListView.setAdapter(mAdapter);

        notifyDataSetChanged();
    }

    protected abstract void init();

    public abstract CommonAdapter<T> getAdapter();

    private void _initView() {

        mListView = (ListView) view.findViewById(R.id.pullListView);
        pullToRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.pullToRefreshLayout);

    }

    private void _initEvent() {
        pullToRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public abstract void onRefresh(PullToRefreshLayout l);

    @Override
    public abstract void onLoadMore(PullToRefreshLayout l);

    protected void resetView() {
        setVisible(R.id.avi, false);
        setVisible(R.id.rl_nodata, false);
        setVisible(R.id.rl_not_login, false);
        setVisible(R.id.rl_net_error, false);
    }

    protected void notifyDataSetChanged() {

        resetView();

        if (comTools != null && !comTools.isLogin()) {
            setVisible(R.id.rl_not_login, true);
            return;
        }

        if (mAdapter.getData().isEmpty()) {
            setVisible(R.id.rl_nodata, true);
        }
    }
}
