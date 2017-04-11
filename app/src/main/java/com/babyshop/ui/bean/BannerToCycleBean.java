package com.babyshop.ui.bean;

import com.babyshop.widget.cycleImage.BaseBannerBean;

/**
 * Created by admin on 2017/4/11.
 */

public class BannerToCycleBean extends BaseBannerBean {

    public String id;
    public String categoryid;
    public String name;
    public String price;
    public String pic;
    public String describe;
    public String createtime;
    public String categoryname;

    @Override
    public String toString() {
        return "BannerToCycleBean{" +
                "url='" + url + '\'' +
                "id='" + id + '\'' +
                ", categoryid='" + categoryid + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", pic='" + pic + '\'' +
                ", describe='" + describe + '\'' +
                ", createtime='" + createtime + '\'' +
                ", categoryname='" + categoryname + '\'' +
                '}';
    }
}
