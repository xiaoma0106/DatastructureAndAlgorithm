package com.mjh.service;

import com.mjh.pojo.entity.domain.bean.Cart;

/**
 * @author mjh
 * @date 2021-10-30 16:38
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
