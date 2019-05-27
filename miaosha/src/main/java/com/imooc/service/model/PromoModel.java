package com.imooc.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public class PromoModel {

    private Integer id;

    //秒杀活动状态 1表示还未开始 2进行中 3已结束
    private Integer status;

    //秒杀活动名称
    private String promoName;

    //活动开始时间
    private DateTime startDate;

    //活动结束时间
    private DateTime endDate;

    private Integer goodsId;

    //秒杀活动的商品价格
    private BigDecimal promoGoodsPrice;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getPromoGoodsPrice() {
        return promoGoodsPrice;
    }

    public void setPromoGoodsPrice(BigDecimal promoGoodsPrice) {
        this.promoGoodsPrice = promoGoodsPrice;
    }
}
