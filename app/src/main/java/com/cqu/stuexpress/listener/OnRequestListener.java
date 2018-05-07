package com.cqu.stuexpress.listener;


public interface OnRequestListener {

    void onError(Exception e);

    void onResponse(String response);
}
