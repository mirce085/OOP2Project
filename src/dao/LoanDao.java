package dao;

import model.Loan;
import java.util.List;
public interface LoanDao {
    Loan findById(String id) throws Exception;
    List<Loan> findOverdue() throws Exception;
    void insert(Loan loan) throws Exception;
    void update(Loan loan) throws Exception;
}