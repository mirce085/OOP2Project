package service;

import dao.BookDao;
import dao.BookDaoImpl;
import model.Book;

import java.util.List;

public class CatalogService {
    private final BookDao bookDao = new BookDaoImpl();

    /**
     * Search books by title, author, or ISBN.
     */
    public List<Book> search(String query) throws Exception {
        return bookDao.findByTitle(query);
    }

    /**
     * Add a new book to the catalog.
     */
    public void addBook(Book book) throws Exception {
        bookDao.insert(book);
    }

    /**
     * Update existing book details.
     */
    public void updateBook(Book book) throws Exception {
        bookDao.update(book);
    }

    /**
     * Remove a book from the catalog by ISBN.
     */
    public void removeBook(String isbn) throws Exception {
        bookDao.delete(isbn);
    }

    /**
     * Find a book by its ISBN.
     */
    public Book getByISBN(String isbn) throws Exception {
        return bookDao.findByISBN(isbn);
    }
}