package com.cqu.stuexpress.bean;

import android.content.Context;
import android.widget.ImageView;

import com.cqu.stuexpress.utils.CommonTools;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;


public class ImageAdapter extends NineGridImageViewAdapter<Img> {

    @Override
    protected void onDisplayImage(Context context, ImageView imageView, Img img) {
        CommonTools myTool = new CommonTools(context);
        myTool.showImage(img.getUrl(), imageView);
    }
}
