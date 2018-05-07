package com.cqu.stuexpress.bean;

import java.util.ArrayList;

/**
 * 动态说说
 */

public class Record {

    String userId;
    String nickName;
    String headUrl;

    String content;
    ArrayList<String> imgUrls;
    String time;        // 发布时间

    int liked;          // 点赞数
    int comments;       // 多少个评论

}
