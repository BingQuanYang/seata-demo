package com.smart.order.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class OrderDto {

    private long id;
    /**
     * 用户id
     */
    private long userId;

    /**
     * 产品id
     */
    private long productId;

    /**
     * 数量
     */
    private int quantity;

    /**
     * 金额
     */
    private long money;
}
