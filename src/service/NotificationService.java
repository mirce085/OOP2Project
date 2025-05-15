package service;

import util.EmailUtil;
import model.Loan;
import java.util.List;
public class NotificationService {
    public void sendOverdueNotifications(List<Loan> overdueLoans) {
        overdueLoans.forEach(loan -> {
            // fetch user email from userDao (not shown)
            String email = "user@example.com";
            String body = "Your loan " + loan.getId() + " is overdue since " + loan.getDueDate();
            EmailUtil.sendEmail(email, "Overdue Notice", body);
        });
    }
}
