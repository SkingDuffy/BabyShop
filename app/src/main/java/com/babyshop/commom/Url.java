package com.babyshop.commom;

/**
 * Created by admin on 2017/4/11.
 */

public class Url {
    //主机IP
    private static final String BASE = "http://123.206.8.171:8080/myshop";

    //图片前缀
    public static final String IMG = BASE;
    //首页轮播
    public static final String HOME_BANNER = BASE + "/commodity/findAdCommodities";

    //商品列表 type:0 限时抢购，1 促销快报，2 新品上架，3 热卖单品，4 推荐品牌
    public static final String HOME_LIST = BASE + "/commodity/findTypeCommodities";

}