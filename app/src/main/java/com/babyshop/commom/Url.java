package com.babyshop.commom;

/**
 * Created by admin on 2017/4/11.
 */

public class Url {
    //主机IP
    private static final String BASE = "http://123.206.8.171:8080/myshop";
    //图片前缀
    public static final String IMG = BASE;

    //登陆
    public static final String LOGIN = BASE + "/login";
    //注册
    public static final String REGIST = BASE + "/regist";
    //首页轮播
    public static final String HOME_BANNER = BASE + "/commodity/findAdCommodities";
    //商品列表 type:0 限时抢购，1 促销快报，2 新品上架，3 热卖单品，4 推荐品牌
    public static final String HOME_LIST = BASE + "/commodity/findTypeCommodities";
    //获取商品分类
    public static final String CATEGORY = BASE + "/commodity/getCategory";
    //根据关键词或分类id来获取商品列表
    public static final String QUERY_LIST = BASE + "/commodity/getCommoditylist";
    //根据商品id来获取详细信息
    public static final String COMMODITY = BASE + "/commodity/getCommodity";
    //添加到购物车
    public static final String ADD_CART = BASE + "/commodity/addShoppingcart";
    //购物车列表
    public static final String CART_LIST = BASE + "/commodity/getShoppingcart";
    //购物车结算 - 添加订单
    public static final String SETTLEMENT_CART = BASE + "/order/addOrder";
    //订单列表
    public static final String MY_ORDER = BASE + "/order/getOrderList";
    //浏览历史
    public static final String MY_HISTORY = BASE + "/commodity/getUserCommodity";
    //添加到收藏
    public static final String ADD_COLLECT = BASE + "/commodity/addCollection";
    //取消收藏
    public static final String DEL_COLLECT = BASE + "/commodity/deleteCollection";
    //收藏列表
    public static final String MY_COLLECT = BASE + "/commodity/getCollection";

}
