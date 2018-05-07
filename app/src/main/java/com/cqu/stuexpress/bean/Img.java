package com.cqu.stuexpress.bean;


import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import com.previewlibrary.enitity.IThumbViewInfo;

public class Img implements IThumbViewInfo {

    private String url;  //图片地址
    private Rect mBounds; // 记录坐标

    public Img(String url) {
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Rect getBounds() {
        return mBounds;
    }

    public void setBounds(Rect bounds) {
        mBounds = bounds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeParcelable(this.mBounds, 0);
    }

    protected Img(Parcel in) {
        this.url = in.readString();
        this.mBounds = in.readParcelable(Rect.class.getClassLoader());
    }

    public static final Parcelable.Creator<Img> CREATOR = new Parcelable.Creator<Img>() {
        public Img createFromParcel(Parcel source) {
            return new Img(source);
        }

        public Img[] newArray(int size) {
            return new Img[size];
        }
    };
}
