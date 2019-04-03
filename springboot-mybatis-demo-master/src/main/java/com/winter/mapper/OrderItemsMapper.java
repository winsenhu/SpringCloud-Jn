package com.winter.mapper;

import com.winter.model.OrderItems;

public interface OrderItemsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderItems record);

    int insertSelective(OrderItems record);

    OrderItems selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderItems record);

    int updateByPrimaryKey(OrderItems record);

    OrderItems selectByOrderId(Long orderId);
}