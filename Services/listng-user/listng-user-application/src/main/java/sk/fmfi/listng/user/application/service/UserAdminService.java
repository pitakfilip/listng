package sk.fmfi.listng.user.application.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.user.domain.User;
import sk.fmfi.listng.user.application.repository.UserRepository;
import sk.fmfi.listng.user.util.SecretUtil;

import javax.mail.MessagingException;
import java.io.InvalidObjectException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserAdminService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;

    private static final long VALID_RESET_LINK = 1000L * 60 * 30; // 30 minutes

    private static final String REST_BASE_URL = "http://localhost:4200/reset/"; // TODO GET URL FROM CONSUL AS PROPERTY

    public boolean sendEmail(List<String> users, String subject, String content) {

        return false;
    }
    
    public boolean sendNewAccountNotification(String email, String password) {
        // TODO 
        return true;
    }

    public boolean sendPasswordResetEmail(String email) {
        Date expires = new Date(new Date().getTime() + VALID_RESET_LINK);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("Europe/Bratislava"));

        JsonObject json = new JsonObject();
        json.addProperty("username", email);
        json.addProperty("expires", expires.getTime());
        json.addProperty("token", SecretUtil.generateSecret());

        byte[] jsonStringBytes = json.toString().getBytes(StandardCharsets.UTF_8);
        String encoded = Base64.getUrlEncoder().encodeToString(jsonStringBytes);

        try {
            emailService.sendPasswordReset(email, REST_BASE_URL + encoded, df.format(expires));
            emailService.sendTemplatedMail(email, REST_BASE_URL + encoded, df.format(expires));
            return true;
        } catch (MessagingException | jakarta.mail.MessagingException e) {
            return false;
        }
    }

    public void processPasswordReset(String hash) throws Exception {
        byte[] hashBytes = Base64.getUrlDecoder().decode(hash);
        String jsonString = new String(hashBytes);
        JsonObject json = JsonParser.parseString(jsonString)
                .getAsJsonObject();

        if (!json.has("username") || !json.has("password") || !json.has("secret")) {
            throw new InvalidObjectException("error.hash.invalid.format");    
        }
        
        String email = json.get("username").getAsString();
        String encryptedNewPassword = json.get("password").getAsString();
        String secret = json.get("secret").getAsString();

        if (!SecretUtil.isValid((secret))) {
            throw new InvalidKeyException("error.hash.invalid.secret");
        }

        User user = repository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("error.user.not.found");
        }
        
        user.setPassword(encryptedNewPassword);
        repository.save(user);
    }

}
