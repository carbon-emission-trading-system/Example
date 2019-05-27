package com.imooc.service.impl;

import com.imooc.dao.GoodsDOMapper;
import com.imooc.dao.GoodsStockDOMapper;
import com.imooc.dataobject.GoodsDO;
import com.imooc.dataobject.GoodsStockDO;
import com.imooc.error.BusinessException;
import com.imooc.error.EmBusinessError;
import com.imooc.service.GoodsService;
import com.imooc.service.PromoService;
import com.imooc.service.model.GoodsModel;
import com.imooc.service.model.PromoModel;
import com.imooc.validator.ValidationResult;
import com.imooc.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private GoodsDOMapper goodsDOMapper;

    @Autowired
    private GoodsStockDOMapper goodsStockDOMapper;

    @Autowired
    private PromoService promoService;

    private GoodsDO convertGoodsDOFromGoodsModel(GoodsModel goodsModel) {
        if (goodsModel == null) {
            return null;
        }
        GoodsDO goodsDO = new GoodsDO();
        BeanUtils.copyProperties(goodsModel, goodsDO);
        return goodsDO;
    }

    private GoodsStockDO convertGoodsStockDOFromGoodsModel(GoodsModel goodsModel) {
        if (goodsModel == null) {
            return null;
        }
        GoodsStockDO goodsStockDO = new GoodsStockDO();
        goodsStockDO.setGoodsId(goodsModel.getId());
        goodsStockDO.setStock(goodsModel.getStock());
        return goodsStockDO;
    }

    @Override
    @Transactional
    public GoodsModel createGoods(GoodsModel goodsModel) throws BusinessException {
        //校验入参
        ValidationResult result = validator.validate(goodsModel);
        if (result.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMMETER_VALIDATION_ERROR, result.getErrMsg());
        }

        //转化goodsModel->dataobject
        GoodsDO goodsDO = this.convertGoodsDOFromGoodsModel(goodsModel);

        //写入数据库
        goodsDOMapper.insertSelective(goodsDO);
        goodsModel.setId(goodsDO.getId());

        GoodsStockDO goodsStockDO = this.convertGoodsStockDOFromGoodsModel(goodsModel);

        goodsStockDOMapper.insertSelective(goodsStockDO);
        //返回创建完成的对象
        return this.getGoodsById(goodsModel.getId());
    }

    @Override
    // 多个sql
//    public List<GoodsModel> listGoods() {
//        List<GoodsDO> goodsDOList = goodsDOMapper.listGoods();
//        List<GoodsModel> goodsModelList = goodsDOList.stream().map(goodsDO -> {
//            GoodsStockDO goodsStockDO = goodsStockDOMapper.selectByGoodsId(goodsDO.getId());
//            GoodsModel goodsModel = this.convertModelFromDataObject(goodsDO, goodsStockDO);
//            return goodsModel;
//        }).collect(Collectors.toList());
//
//        return goodsModelList;
//    }

    //一条sql
    public List<GoodsModel> listGoods() {
        List<GoodsDO> goodsDOList = goodsDOMapper.listGoods();

        //拿到所有商品的id集合
        List<Integer> idList = new ArrayList<>();
        for (GoodsDO goodsDO : goodsDOList) {
            idList.add(goodsDO.getId());
        }

        //根据idList查出goodsStockDOList
        List<GoodsStockDO> goodsStockDOList = goodsStockDOMapper.selectByGoodsIdList(idList);

        List<GoodsModel> list = new ArrayList<>();
        for (GoodsDO goodsDO : goodsDOList) {
            for (GoodsStockDO goodsStockDO : goodsStockDOList) {
                if (goodsDO.getId() == goodsStockDO.getGoodsId()) {
                    GoodsModel goodsModel = convertModelFromDataObject(goodsDO, goodsStockDO);
                    list.add(goodsModel);
                }
            }
        }

        return list;
    }

    @Override
    public GoodsModel getGoodsById(Integer id) {
        GoodsDO goodsDO = goodsDOMapper.selectByPrimaryKey(id);
        if (goodsDO == null) {
            return null;
        }

        //操作获得库存对象
        GoodsStockDO goodsStockDO = goodsStockDOMapper.selectByGoodsId(goodsDO.getId());

        //将dataobject->model
        GoodsModel goodsModel = convertModelFromDataObject(goodsDO, goodsStockDO);

        //获取活动商品信息
        PromoModel promoModel = promoService.getPromoByGoodsId(id);
        if (promoModel != null && promoModel.getStatus().intValue() != 3) {
            goodsModel.setPromoModel(promoModel);
        }

        return goodsModel;
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer goodsId, Integer amount) throws BusinessException {
        int affectedRow = goodsStockDOMapper.decreaseStock(goodsId, amount);
        if (affectedRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public void increaseSales(Integer goodsId, Integer amount) throws BusinessException {
        goodsDOMapper.increaseSales(goodsId, amount);
    }

    private GoodsModel convertModelFromDataObject(GoodsDO goodsDO, GoodsStockDO goodsStockDO) {
        GoodsModel goodsModel = new GoodsModel();
        BeanUtils.copyProperties(goodsDO, goodsModel);
        goodsModel.setStock(goodsStockDO.getStock());

        return goodsModel;
    }
}
