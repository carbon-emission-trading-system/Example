package com.imooc.dao;

import com.imooc.dataobject.PromoDO;

public interface PromoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbggenerated Sun Feb 10 16:36:22 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbggenerated Sun Feb 10 16:36:22 CST 2019
     */
    int insert(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbggenerated Sun Feb 10 16:36:22 CST 2019
     */
    int insertSelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbggenerated Sun Feb 10 16:36:22 CST 2019
     */
    PromoDO selectByPrimaryKey(Integer id);

    PromoDO selectByGoodsId(Integer goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbggenerated Sun Feb 10 16:36:22 CST 2019
     */
    int updateByPrimaryKeySelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbggenerated Sun Feb 10 16:36:22 CST 2019
     */
    int updateByPrimaryKey(PromoDO record);
}