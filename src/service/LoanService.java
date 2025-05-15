package service;

import dao.LoanDao;
import dao.LoanDaoImpl;
import dao.BookDao;
import dao.BookDaoImpl;
import model.Loan;
import model.Book;
import model.Receipt;

import java.util.Date;
import java.util.List;
import java.util.UUID;
public class LoanService {
    private LoanDao loanDao = new LoanDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    public Loan checkout(String userId, String isbn) throws Exception {
        Book book = bookDao.findByISBN(isbn);
        if (book == null || book.getAvailableCopies() < 1) {
            throw new IllegalStateException("Book not available");
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookDao.update(book);

        String loanId = UUID.randomUUID().toString();
        Date checkoutDate = new Date();
        Date dueDate = new Date(checkoutDate.getTime() + 14L*24*60*60*1000); // 2 weeks
        Loan loan = new Loan(loanId, userId, isbn, checkoutDate, dueDate);
        loanDao.insert(loan);
        return loan;
    }

    public Receipt checkin(String loanId) throws Exception {
        Loan loan = loanDao.findById(loanId);
        if (loan == null || loan.isReturned()) {
            throw new IllegalStateException("Invalid loan ID or already returned");
        }
        loan.setReturned(true);
        loanDao.update(loan);

        Book book = bookDao.findByISBN(loan.getIsbn());
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookDao.update(book);

        return new Receipt(loanId, new Date());
    }

    public boolean renew(String loanId) throws Exception {
        Loan loan = loanDao.findById(loanId);
        if (loan == null || loan.isReturned()) {
            return false;
        }
        Date newDue = new Date(loan.getDueDate().getTime() + 7L*24*60*60*1000); // +1 week
        loan.setDueDate(newDue);
        loanDao.update(loan);
        return true;
    }

    public List<Loan> getOverdueLoans() throws Exception {
        return loanDao.findOverdue();
    }
}
