package com.imooc.controller;

import com.imooc.controller.viewobject.GoodsVO;
import com.imooc.error.BusinessException;
import com.imooc.response.CommonReturnType;
import com.imooc.service.GoodsService;
import com.imooc.service.model.GoodsModel;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    //商品详情浏览
    @GetMapping("/get")
    public CommonReturnType getGoods(@RequestParam(name = "id") Integer id) {
        GoodsModel goodsModel = goodsService.getGoodsById(id);

        GoodsVO goodsVO = convertVOFromModel(goodsModel);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("goods", goodsVO);

        return CommonReturnType.success(resultMap);
    }

    //商品列表浏览
    @GetMapping("/list")
    public CommonReturnType list() {
        List<GoodsModel> goodsModelList = goodsService.listGoods();

        //使用stream api将list内的goodsModel转化为goodsVO
        List<GoodsVO> goodsVOList = goodsModelList.stream().map(goodsModel -> {
            GoodsVO goodsVO = this.convertVOFromModel(goodsModel);
            return goodsVO;
        }).collect(Collectors.toList());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("goodsList", goodsVOList);

        return CommonReturnType.success(resultMap);
    }

    @PostMapping("/create")
    public CommonReturnType createGoods(@RequestParam(name = "title") String title,
                                        @RequestParam(name = "description") String description,
                                        @RequestParam(name = "price") BigDecimal price,
                                        @RequestParam(name = "stock") Integer stock,
                                        @RequestParam(name = "imgUrl") String imgUrl) throws BusinessException {

        //封装service请求用来创建商品
        GoodsModel goodsModel = new GoodsModel();
        goodsModel.setTitle(title);
        goodsModel.setDescription(description);
        goodsModel.setPrice(price);
        goodsModel.setStock(stock);
        goodsModel.setImgUrl(imgUrl);
        goodsModel.setSales(0);

        GoodsModel goodsModelBeReturn = goodsService.createGoods(goodsModel);
        GoodsVO goodsVO = convertVOFromModel(goodsModelBeReturn);

        return CommonReturnType.success(goodsVO);

    }

    private GoodsVO convertVOFromModel(GoodsModel goodsModel) {
        if (goodsModel == null) {
            return null;
        }
        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(goodsModel, goodsVO);
        if (goodsModel.getPromoModel() != null) {
            //有正在进行或即将进行的秒杀活动
            goodsVO.setPromoStatus(goodsModel.getPromoModel().getStatus());
            goodsVO.setPromoId(goodsModel.getPromoModel().getId());
            goodsVO.setStartDate(goodsModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            goodsVO.setPromoPrice(goodsModel.getPromoModel().getPromoGoodsPrice());
        } else {
            goodsVO.setPromoStatus(0);
        }
        return goodsVO;
    }

}
