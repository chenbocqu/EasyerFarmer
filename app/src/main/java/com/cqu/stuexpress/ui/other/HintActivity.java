package com.cqu.stuexpress.ui.other;

import com.cqu.stuexpress.R;
import com.cqu.stuexpress.ui.base.TitleBarActivity;

public class HintActivity extends TitleBarActivity {


    @Override
    public String getUITitle() {
        return "提示";
    }

    @Override
    public void init() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_hint;
    }
}
