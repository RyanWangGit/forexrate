package com.calibre.forex.sending.service.impl;

import com.calibre.forex.sending.model.Mail;
import com.calibre.forex.sending.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMailWithAttach(Mail mail) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mail.getFrom());
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            String content = StringUtils.isEmpty(mail.getBody())? mailContent():mail.getBody();
            helper.setText(content, true);

            String attachPath = mail.getAttachPath();
            FileSystemResource file = new FileSystemResource(new File(attachPath));
            String fileName = attachPath.substring(attachPath.lastIndexOf(File.separator)+1);
            helper.addAttachment(fileName, file);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error(" Error in sendMailWithAttach method", e);
        }
    }

    private String mailContent(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html><body>")
                .append("Dear Customer:<br>")
                .append("The FOREX data that you subscribed has new update,<br>")
                .append("Please checked the attach file.<br>")
                .append("Thanks for your subscription! <p>")
                .append("Regards")
                .append("</body></html>");
        return stringBuffer.toString();
    }
}
