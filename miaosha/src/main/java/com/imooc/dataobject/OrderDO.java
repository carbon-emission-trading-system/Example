package com.imooc.dataobject;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.id
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.user_id
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.goods_id
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    private Integer goodsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.goods_price
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    private BigDecimal goodsPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.amount
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    private Integer amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_price
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    private BigDecimal orderPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.createtime
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    private Date createtime;

    private Integer promoId;

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.user_id
     *
     * @return the value of order_info.user_id
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.user_id
     *
     * @param userId the value for order_info.user_id
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.goods_id
     *
     * @return the value of order_info.goods_id
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.goods_id
     *
     * @param goodsId the value for order_info.goods_id
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.goods_price
     *
     * @return the value of order_info.goods_price
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.goods_price
     *
     * @param goodsPrice the value for order_info.goods_price
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.amount
     *
     * @return the value of order_info.amount
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.amount
     *
     * @param amount the value for order_info.amount
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_price
     *
     * @return the value of order_info.order_price
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_price
     *
     * @param orderPrice the value for order_info.order_price
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.createtime
     *
     * @return the value of order_info.createtime
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.createtime
     *
     * @param createtime the value for order_info.createtime
     *
     * @mbggenerated Sun Feb 10 01:05:32 CST 2019
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}