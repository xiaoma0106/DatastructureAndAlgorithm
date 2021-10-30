package com.mjh.service.impl;

import com.mjh.dao.BookDao;
import com.mjh.dao.OrderDao;
import com.mjh.dao.OrderItemDao;
import com.mjh.dao.impl.BookDaoImpl;
import com.mjh.dao.impl.OrderDaoImpl;
import com.mjh.dao.impl.OrderItemDaoImpl;
import com.mjh.pojo.entity.domain.bean.*;
import com.mjh.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author mjh
 * @date 2021-10-30 16:39
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao= new OrderDaoImpl();
    private OrderItemDao orderItemDao= new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号的生成
        String orderId=System.currentTimeMillis()+""+userId;
        //创建订单
        orderDao.saveOrder(new Order(orderId,new Date(),cart.getTotalPrice(),0,userId));

        //创建订单项
        for (Map.Entry<Integer, CartItem> cartItemEntry:cart.getItems().entrySet()){
            CartItem cartItem=cartItemEntry.getValue();
            orderItemDao.saveOrderItem(new OrderItem(cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId));

            //更新该书籍的销售量和库存
            Book book=bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }

        //已生成订单,故清除购物车信息
        cart.clearItem();
        return orderId;
    }
}
