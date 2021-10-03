import com.mjh.dao.UserDao;
import com.mjh.dao.impl.UserDaoImpl;
import com.mjh.pojo.entity.domain.bean.User;
import org.junit.Test;

/**
 * @author mjh
 * @date 2021-10-02 14:53
 */
public class UserDaoTest {
    @Test
    public void testQueryByUsername(){
        UserDao userDao=new UserDaoImpl();
        User user= userDao.queryUserByUsername("admin");
        if (null !=user){
            System.out.println(user);
        }else{
            System.out.println("用户不存在!");
        }
    }

    @Test
    public void testQueryByUsernameAndPassword(){
        UserDao userDao=new UserDaoImpl();
        User user=userDao.queryUserByUsernameAndPassword("admin","admin");
        System.out.println(user);
    }

    @Test
    public void testSave(){
        UserDao userDao=new UserDaoImpl();
        int rs=userDao.saveUser(new User("zhang","123456","san@qq.com"));
        System.out.println(rs);
    }
}
