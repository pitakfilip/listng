package sk.fmfi.listng.user.application.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;
        
    private static final String subjectBase = "[LIST-NG] ";
    
    private static final String subjectPasswordReset = "Obnova hesla";

    /**
     * Send a basic email to a single user.
     * @param to        User email receiving our email.
     * @param subject   Title of email left as is.
     * @param body      Body of our email.
     * @throws MessagingException in case of a failure sending the email.
     */
    public void sendRawEmail(String to, String subject, String body) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(subject);
        helper.setText(body);
        helper.setTo(to);

        mailSender.send(mimeMessage);
    }

    /**
     * Send a basic email to a several users.
     * @param to        User emails receiving our email.
     * @param subject   Title of email left as is.
     * @param body      Body of our email.
     * @throws MessagingException in case of a failure sending the email.
     */
    public void sendRawEmails(String[] to, String subject, String body) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(subject);
        helper.setText(body);
        helper.setTo(to);

        mailSender.send(mimeMessage);
    }

    /**
     * Send a templated email to a single user.
     * @param to        Single user email, which will receive the email.
     * @param subject   Title of email, which will be formatted as well by a common prefix.
     * @param body      Content of email, which will be injected into the HTML template. We are able to use 
     *                  either plain text or a HTML formatted text.
     * @throws MessagingException in case of a failure sending the email.
     */
    public void sendTemplatedMail(String to, String subject, String body) throws MessagingException, jakarta.mail.MessagingException {
        Context context = new Context();
        context.setVariable("innerBodyInject", body);

        String process = templateEngine.process("general", context);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(subjectBase + subject);
        helper.setText(process, true);
        helper.setTo(to);
        
        mailSender.send(mimeMessage);
    }

    /**
     * Send a templated email to several people at once.
     * @param to        Array of user emails, which will receive the email.
     * @param subject   Title of email, which will be formatted as well by a common prefix.
     * @param body      Content of email, which will be injected into the HTML template. We are able to use 
     *                  either plain text or a HTML formatted text.
     * @throws MessagingException in case of a failure sending the email.
     */
    public void sendTemplatedMails(String[] to, String subject, String body) throws MessagingException, jakarta.mail.MessagingException {
        Context context = new Context();
        context.setVariable("innerBodyInject", body);

        String process = templateEngine.process("general", context);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(subjectBase + subject);
        helper.setText(process, true);
        helper.setTo(to);

        mailSender.send(mimeMessage);
    }
    
    public void sendPasswordReset(String email, String URL, String expires) throws MessagingException, jakarta.mail.MessagingException {
        Context context = new Context();
        context.setVariable("generatedURL", URL);
        context.setVariable("linkExpires", expires);
        
        String process = templateEngine.process("reset", context);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(subjectBase + subjectPasswordReset);
        helper.setText(process, true);
        helper.setTo(email);
        
        mailSender.send(mimeMessage);
    }
}
