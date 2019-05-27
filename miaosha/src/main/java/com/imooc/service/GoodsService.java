package com.imooc.service;

import com.imooc.error.BusinessException;
import com.imooc.service.model.GoodsModel;

import java.util.List;

public interface GoodsService {

    //创建商品
    GoodsModel createGoods(GoodsModel goodsModel) throws BusinessException;

    //商品列表浏览
    List<GoodsModel> listGoods();

    //商品详情浏览
    GoodsModel getGoodsById(Integer id);

    //库存扣减
    boolean decreaseStock(Integer goodsId, Integer amount) throws BusinessException;

    //商品销量增加
    void increaseSales(Integer goodsId, Integer amount) throws BusinessException;
}
