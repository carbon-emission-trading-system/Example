package com.imooc.service;

import com.imooc.error.BusinessException;
import com.imooc.service.model.OrderModel;

public interface OrderService {

    //使用1.通过前端url上传过来秒杀活动的id,然后下单接口内校验对应的id是否属于对应商品且活动已开始
    //2.直接在下单接口内判断对应的商品是否存在秒杀活动,若存在进行中的则以秒杀价格下单
    OrderModel createOrder(Integer userId,Integer goodsId,Integer promoId,Integer amount) throws BusinessException;
}
