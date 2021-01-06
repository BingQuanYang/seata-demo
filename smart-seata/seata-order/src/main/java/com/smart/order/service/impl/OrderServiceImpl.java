package com.smart.order.service.impl;

import com.smart.order.dto.OrderDto;
import com.smart.order.entity.Order;
import com.smart.order.feign.StoreService;
import com.smart.order.mapper.OrderMapper;
import com.smart.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * undo-log 表
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    StoreService storeService;
    @Resource
    OrderMapper orderMapper;

    @Override
    @GlobalTransactional
    public String createOrder(OrderDto orderDto) {
        try {
            Order order = new Order();
            BeanUtils.copyProperties(orderDto, order);
            int insert = orderMapper.insert(order);
            int dec = storeService.dec(orderDto.getProductId(), orderDto.getQuantity());
            if (dec < 0) {
                log.info("库存不足");
                throw new RuntimeException("库存不足");
            }
            return insert + "";
        } catch (Exception e) {
            throw new RuntimeException("系统..");
        }
    }
}
