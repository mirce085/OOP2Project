package dao;

import model.Book;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> findByTitle(String title) throws Exception {
        String sql = "SELECT * FROM books WHERE title LIKE ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + title + "%");
            ResultSet rs = ps.executeQuery();
            List<Book> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Book(
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("total_copies")
                ));
            }
            return list;
        }
    }
    @Override
    public Book findByISBN(String isbn) throws Exception {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("total_copies")
                );
            }
            return null;
        }
    }
    @Override
    public void insert(Book book) throws Exception {
        String sql = "INSERT INTO books(isbn,title,author,genre,total_copies,available_copies) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getGenre());
            ps.setInt(5, book.getTotalCopies());
            ps.setInt(6, book.getAvailableCopies());
            ps.executeUpdate();
        }
    }
    @Override
    public void update(Book book) throws Exception {
        String sql = "UPDATE books SET title=?,author=?,genre=?,total_copies=?,available_copies=? WHERE isbn=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getGenre());
            ps.setInt(4, book.getTotalCopies());
            ps.setInt(5, book.getAvailableCopies());
            ps.setString(6, book.getIsbn());
            ps.executeUpdate();
        }
    }
    @Override
    public void delete(String isbn) throws Exception {
        String sql = "DELETE FROM books WHERE isbn=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, isbn);
            ps.executeUpdate();
        }
    }
}
