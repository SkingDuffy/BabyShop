package com.babyshop.ui.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */

public class OrderBean implements Serializable {

    public String id;
    public String totalprice;
    public List<CartGoodsBean> commodities;

}
