package com.mjh.dao;

import com.mjh.pojo.entity.domain.bean.Order;

/**
 * @author mjh
 * @date 2021-10-30 16:24
 */
public interface OrderDao {
    public int saveOrder(Order order);
}
