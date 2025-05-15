package dao;

import model.Book;
import java.util.List;
public interface BookDao {
    List<Book> findByTitle(String title) throws Exception;
    Book findByISBN(String isbn) throws Exception;
    void insert(Book book) throws Exception;
    void update(Book book) throws Exception;
    void delete(String isbn) throws Exception;
}
