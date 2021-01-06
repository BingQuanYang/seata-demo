package com.smart.order.controller;

import com.smart.order.dto.OrderDto;
import com.smart.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderService orderService;

    @GetMapping("/create")
    public String create(long productId, int quantity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(1);
        orderDto.setProductId(productId);
        orderDto.setQuantity(quantity);
        return orderService.createOrder(orderDto);
    }
}
