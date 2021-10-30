package com.mjh.dao.impl;

import com.mjh.dao.OrderItemDao;
import com.mjh.pojo.entity.domain.bean.OrderItem;

/**
 * @author mjh
 * @date 2021-10-30 16:32
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
