package model;

import java.util.Date;
public class Loan {
    private String id;
    private String userId;
    private String isbn;
    private Date checkoutDate;
    private Date dueDate;
    private boolean returned;

    public Loan(String id, String userId, String isbn, Date checkoutDate, Date dueDate) {
        this.id = id;
        this.userId = userId;
        this.isbn = isbn;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.returned = false;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getIsbn() { return isbn; }
    public Date getCheckoutDate() { return checkoutDate; }
    public Date getDueDate() { return dueDate; }
    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }
    public boolean isOverdue() { return new Date().after(dueDate) && !returned; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
}