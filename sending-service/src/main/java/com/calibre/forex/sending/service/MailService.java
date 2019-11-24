package com.calibre.forex.sending.service;

import com.calibre.forex.sending.model.Mail;

public interface MailService {

    public void sendMailWithAttach(Mail mail);
}
