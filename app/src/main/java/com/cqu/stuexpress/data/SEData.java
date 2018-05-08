package com.cqu.stuexpress.data;

import com.cqu.stuexpress.bean.ContactAddress;
import com.cqu.stuexpress.bean.ExpressStu;

import java.util.ArrayList;
import java.util.List;

public class SEData {

    public static List<String> getImageUrls() {
        List<String> images = new ArrayList<>();
        images.add("http://www.farmer.com.cn/tppd/snjj/201805/W020180508355568130642.jpg");
        images.add("http://paper.ce.cn/jjrb/res/1/1/2018-05/08/16/res01_attpic_brief.jpg");
        images.add("http://szb.farmer.com.cn/nmrb/images/2018-05/08/05/p43_b.jpg");
        return images;
    }

    public static List<String> getImageTitles() {
        List<String> titles = new ArrayList<>();
        titles.add("重庆：“农业+旅游”带动农户脱贫增收");
        titles.add("全国果菜茶有机肥替代化肥试点增至150个县");
        titles.add("2018年国家优质稻品种攻关推进暨鉴评推介会：寻找中国好大米");
        return titles;
    }

    public static List<ExpressStu> getStus() {
        List<ExpressStu> stus = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            stus.add(ExpressStu.getInstance());
        return stus;
    }

    public static List<ContactAddress> getAddress() {

        List<ContactAddress> addresses = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            ContactAddress address = new ContactAddress();
            address.setContactName("用户" + i);
            address.setPhone("1502531689" + (i - 1));
            address.setProvince("重庆市");
            address.setCity("重庆市");
            address.setArea("沙坪坝区");
            address.setDetialAddress("重庆大学A区6舍70" + (i + 2));
            addresses.add(address);
        }

        return addresses;
    }

}
