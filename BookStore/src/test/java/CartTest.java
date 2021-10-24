import com.mjh.pojo.entity.domain.bean.Cart;
import com.mjh.pojo.entity.domain.bean.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author mjh
 * @date 2021-10-24 12:18
 */
public class CartTest {
    @Test
    public void testCart(){
        Cart cart=new Cart();
        CartItem item1=new CartItem(1,"zhangsan",1,new BigDecimal(10),new BigDecimal(10));
        CartItem item2=new CartItem(2,"lisi",1,new BigDecimal(20),new BigDecimal(20));
        CartItem item3=new CartItem(3,"Tom",1,new BigDecimal(30),new BigDecimal(30));
        CartItem item4=new CartItem(4,"Jack",1,new BigDecimal(40),new BigDecimal(40));


        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item1);

//        cart.deleteItem(1);
//        cart.updateCount(2,10);
        cart.clearItem();
        System.out.println(cart);
    }
}
