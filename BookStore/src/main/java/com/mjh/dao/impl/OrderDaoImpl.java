package com.mjh.dao.impl;

import com.mjh.dao.OrderDao;
import com.mjh.pojo.entity.domain.bean.Order;

/**
 * @author mjh
 * @date 2021-10-30 16:25
 */
public class OrderDaoImpl extends BaseDao implements OrderDao{
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
