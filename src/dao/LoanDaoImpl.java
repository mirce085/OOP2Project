package dao;

import model.Loan;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class LoanDaoImpl implements LoanDao {
    @Override
    public Loan findById(String id) throws Exception {
        String sql = "SELECT * FROM loans WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Loan(
                        rs.getString("id"),
                        rs.getString("user_id"),
                        rs.getString("isbn"),
                        rs.getTimestamp("checkout_date"),
                        rs.getTimestamp("due_date")
                );
            }
            return null;
        }
    }
    @Override
    public List<Loan> findOverdue() throws Exception {
        String sql = "SELECT * FROM loans WHERE due_date < NOW() AND returned = FALSE";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<Loan> list = new ArrayList<>();
            while (rs.next()) {
                Loan loan = new Loan(
                        rs.getString("id"),
                        rs.getString("user_id"),
                        rs.getString("isbn"),
                        rs.getTimestamp("checkout_date"),
                        rs.getTimestamp("due_date")
                );
                loan.setReturned(rs.getBoolean("returned"));
                list.add(loan);
            }
            return list;
        }
    }
    @Override
    public void insert(Loan loan) throws Exception {
        String sql = "INSERT INTO loans(id,user_id,isbn,checkout_date,due_date,returned) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, loan.getId());
            ps.setString(2, loan.getUserId());
            ps.setString(3, loan.getIsbn());
            ps.setTimestamp(4, new Timestamp(loan.getCheckoutDate().getTime()));
            ps.setTimestamp(5, new Timestamp(loan.getDueDate().getTime()));
            ps.setBoolean(6, loan.isReturned());
            ps.executeUpdate();
        }
    }
    @Override
    public void update(Loan loan) throws Exception {
        String sql = "UPDATE loans SET returned=?, due_date=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, loan.isReturned());
            ps.setTimestamp(2, new Timestamp(loan.getDueDate().getTime()));
            ps.setString(3, loan.getId());
            ps.executeUpdate();
        }
    }
}
