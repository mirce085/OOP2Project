package model;

import java.util.Date;
public class Receipt {
    private String loanId;
    private Date returnDate;
    public Receipt(String loanId, Date returnDate) {
        this.loanId = loanId;
        this.returnDate = returnDate;
    }
    public String getLoanId() { return loanId; }
    public Date getReturnDate() { return returnDate; }
}

