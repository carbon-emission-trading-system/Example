package com.imooc.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

//订单模型
public class OrderModel {
    //2019021000088888
    private String id;

    private Integer userId;

    //购买的商品id
    private Integer goodsId;

    //购买数量
    private Integer amount;

    //购买金额,如果promoId非空,表示秒杀商品的金额
    private BigDecimal orderPrice;

    //购买商品的单价,如果promoId非空,表示秒杀商品的价格
    private BigDecimal goodsPrice;

    //订单创建时间
    private DateTime createtime;

    //若非空,表示是以秒杀商品方式下单
    private Integer promoId;

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public DateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(DateTime createtime) {
        this.createtime = createtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }


}
