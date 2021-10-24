package com.mjh.pojo.entity.domain.bean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author mjh
 * @date 2021-10-24 11:51
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    /**
     * key:商品ID
     * value:商品CartItem对象
     */
    private Map<Integer,CartItem> items=new LinkedHashMap<>();

    //添加商品
    public void addItem(CartItem item){
        Integer id=item.getId();
        CartItem myItem=items.get(id);
        if (myItem != null){
           myItem.setCount(myItem.getCount()+1);
           myItem.setTotalPrice(myItem.getPrice().multiply(new BigDecimal(myItem.getCount())));
        }else {
            items.put(item.getId(),item);
        }
    }

    //删除商品
    public void deleteItem(Integer id){
        items.remove(id);
    }

    //清空商品
    public void clearItem(){
        items.clear();
    }

    //修改商品数量
    public void updateCount(Integer id,Integer count){
        CartItem item=items.get(id);
        if (item != null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(count)));
        }
    }

    //遍历整个Map获取总商品数
    public Integer getTotalCount(){
        Integer totalCount=0;
        for (Map.Entry<Integer,CartItem> item:items.entrySet()){
            totalCount += item.getValue().getCount();
        }
        return totalCount;
    }

    //遍历整个Map,由该商品的个数和单价可知该商品总价,然后将所有商品总价相加
    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice=new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> item:items.entrySet()){
            totalPrice=totalPrice.add(item.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
