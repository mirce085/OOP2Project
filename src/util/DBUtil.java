package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;
public class DBUtil {
    private static String url;
    private static String user;
    private static String pass;
    static {
        try (InputStream in = DBUtil.class.getResourceAsStream("/application.properties")) {
            Properties props = new Properties();
            props.load(in);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            pass = props.getProperty("db.password");
            Class.forName(props.getProperty("db.driver"));
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, pass);
    }
}