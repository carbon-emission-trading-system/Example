package com.imooc.service;

import com.imooc.service.model.PromoModel;

public interface PromoService {

    // 根据goodsId获取即将进行的或正在进行的秒杀活动
    PromoModel getPromoByGoodsId(Integer goodsId);
}
