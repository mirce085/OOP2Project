package util;

public class ValidationUtil {
    public static boolean isValidIsbn(String isbn) {
        return isbn != null && isbn.matches("\\d{10}|\\d{13}");
    }
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^.+@.+\\..+$");
    }
    public static boolean isStrongPassword(String pwd) {
        return pwd != null && pwd.length() >= 8;
    }
}
