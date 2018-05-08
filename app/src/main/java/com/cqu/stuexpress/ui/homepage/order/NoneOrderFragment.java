/**
 * 空的Frag
 */
package com.cqu.stuexpress.ui.homepage.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cqu.stuexpress.R;
import com.cqu.stuexpress.ui.base.FragmentWithOnResume;

public class NoneOrderFragment extends FragmentWithOnResume {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_none, container, false);
        return view;
    }
}
