import com.mjh.pojo.entity.domain.bean.User;
import com.mjh.service.UserService;
import com.mjh.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * @author mjh
 * @date 2021-10-02 15:19
 */
public class UserServiceTest {
    @Test
    public void testRegist(){
        UserService userService=new UserServiceImpl();
        userService.register(new User("wang","5555","wang@qq.com"));
    }

    @Test
    public void testLogin(){
        UserService userService=new UserServiceImpl();
        System.out.println(userService.login(new User("zhang","123456","zhang@qq.com")));
    }

    @Test
    public void testExitUser(){
        UserService userService=new UserServiceImpl();
        System.out.println(userService.exitUsername("li"));
    }
}
