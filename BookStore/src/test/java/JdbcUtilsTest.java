import com.mjh.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author mjh
 * @date 2021-10-02 13:41
 */
public class JdbcUtilsTest {
    @Test
    public  void testJdbc(){
        for (int i = 0; i < 100; i++) {
            Connection con= JdbcUtils.getConnection();
            System.out.println(con);
            JdbcUtils.close(con);
        }
    }
}
