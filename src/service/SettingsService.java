package service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class SettingsService {
    private static final Path CONFIG_PATH = Path.of("src/main/resources/application.properties");

    /**
     * Save database and SMTP settings to application.properties.
     */
    public void save(
            String dbUrl,
            String dbUser,
            String dbPass,
            String smtpHost,
            String smtpPort,
            String smtpUser,
            String smtpPass
    ) throws Exception {
        Properties props = new Properties();
        // Load existing to preserve other settings if needed
        try (InputStream in = Files.newInputStream(CONFIG_PATH)) {
            props.load(in);
        }
        props.setProperty("db.url", dbUrl);
        props.setProperty("db.user", dbUser);
        props.setProperty("db.password", dbPass);
        props.setProperty("mail.smtp.host", smtpHost);
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.user", smtpUser);
        props.setProperty("mail.password", smtpPass);

        try (OutputStream out = new FileOutputStream(CONFIG_PATH.toFile())) {
            props.store(out, "Updated by SettingsController");
        }
    }
}
