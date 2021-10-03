import com.mjh.pojo.entity.domain.bean.Book;
import com.mjh.service.impl.BookServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * @author mjh
 * @date 2021-10-03 20:25
 */
public class BookServiceTest {
    @Test
    public void testBookService(){
        BookServiceImpl bookService=new BookServiceImpl();
        List<Book> books=bookService.queryBooks();
        System.out.println(books);
    }
}
