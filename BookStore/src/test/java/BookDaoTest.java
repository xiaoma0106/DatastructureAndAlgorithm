import com.mjh.dao.impl.BookDaoImpl;
import com.mjh.pojo.entity.domain.bean.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author mjh
 * @date 2021-10-03 20:01
 */
public class BookDaoTest {
    @Test
    public void testAddBook(){
        BookDaoImpl bookDao=new BookDaoImpl();
        Book book=new Book(null,"如何成为百万富翁",new BigDecimal(9999),"mjh",200,300,null);
        bookDao.addBook(book);
    }

    @Test
    public void testDeleteBook(){
        BookDaoImpl bookDao=new BookDaoImpl();
        bookDao.deleteBookById(21);
    }

    @Test
    public void testUpdateBook(){
        BookDaoImpl bookDao=new BookDaoImpl();
        Book book=new Book(22,"如何成为百万富翁2",new BigDecimal(9999),"mjh",200,300,null);
        bookDao.updateBook(book);
    }

    @Test
    public void testQueryBook(){
        BookDaoImpl bookDao=new BookDaoImpl();
        Book book=bookDao.queryBookById(22);
        System.out.println(book);
    }

    @Test
    public void testQueryBooks(){
        BookDaoImpl bookDao=new BookDaoImpl();
        List<Book> books=bookDao.queryBooks();
        System.out.println(books);
    }
}
