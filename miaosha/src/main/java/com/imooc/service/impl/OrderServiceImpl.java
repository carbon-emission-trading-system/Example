package com.imooc.service.impl;

import com.imooc.dao.OrderDOMapper;
import com.imooc.dao.SequenceDOMapper;
import com.imooc.dataobject.OrderDO;
import com.imooc.dataobject.SequenceDO;
import com.imooc.error.BusinessException;
import com.imooc.error.EmBusinessError;
import com.imooc.service.GoodsService;
import com.imooc.service.OrderService;
import com.imooc.service.UserService;
import com.imooc.service.model.GoodsModel;
import com.imooc.service.model.OrderModel;
import com.imooc.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer goodsId, Integer promoId, Integer amount) throws BusinessException {
        //1.校验下单状态:下单的商品是否存在,用户是否合法,购买数量是否正确
        GoodsModel goodsModel = goodsService.getGoodsById(goodsId);
        if (goodsModel == null) {
            throw new BusinessException(EmBusinessError.PARAMMETER_VALIDATION_ERROR, "商品信息不存在");
        }

        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMMETER_VALIDATION_ERROR, "用户信息不存在");
        }

        if (amount <= 0 || amount > 99) {
            throw new BusinessException(EmBusinessError.PARAMMETER_VALIDATION_ERROR, "数量息不正确");
        }

        //校验活动信息
        if (promoId != null) {
            //(1)校验对应活动是否存在这个适用商品
            if (promoId.intValue() != goodsModel.getPromoModel().getId()) {
                throw new BusinessException(EmBusinessError.PARAMMETER_VALIDATION_ERROR, "活动信息不正确");
                //(2)校验活动是否正在进行中
            } else if (goodsModel.getPromoModel().getStatus().intValue() != 2) {
                throw new BusinessException(EmBusinessError.PARAMMETER_VALIDATION_ERROR, "活动还未开始");
            }
        }


        //2.落单减库存
        boolean result = goodsService.decreaseStock(goodsId, amount);
        if (!result) {
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }


        //3.订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setGoodsId(goodsId);
        orderModel.setAmount(amount);
        if (promoId != null) {
            orderModel.setGoodsPrice(goodsModel.getPromoModel().getPromoGoodsPrice());
        } else {
            orderModel.setGoodsPrice(goodsModel.getPrice());
        }

        orderModel.setOrderPrice(orderModel.getGoodsPrice().multiply(new BigDecimal(amount)));
        orderModel.setPromoId(promoId);

        //生成交易订单号
        orderModel.setId(generateOrderNo());
        OrderDO orderDO = convertFromOrderModel(orderModel);
        orderDOMapper.insertSelective(orderDO);

        //加上商品的销量
        goodsService.increaseSales(goodsId, amount);

        //4.返回前端
        return orderModel;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private String generateOrderNo() {
        StringBuilder stringBuilder = new StringBuilder();
        //16位,前8位为时间信息,年月日
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(nowDate);
        //中间6位为自增序列
        //获取当前sequence
        int sequence = 0;
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);

        String sequenceStr = String.valueOf(sequence);
        for (int i = 0; i < 6 - sequenceStr.length(); i++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);

        //最后2位为分库分表位,写死
        stringBuilder.append("00");

        return stringBuilder.toString();
    }

    private OrderDO convertFromOrderModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }

        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        return orderDO;
    }
}
