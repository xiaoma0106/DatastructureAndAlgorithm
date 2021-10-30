package com.mjh.dao;

import com.mjh.pojo.entity.domain.bean.OrderItem;

/**
 * @author mjh
 * @date 2021-10-30 16:31
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);
}
