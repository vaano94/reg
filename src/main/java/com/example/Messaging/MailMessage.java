package com.example.Messaging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 05/06/16.
 */
public class MailMessage implements Serializable {

    private static final long serialVersionUID = -3679304901044035566L;

    private String author;
    private String recipient;
    private String replyTo;
    private String subject;
    private String body;
    private String passfield;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String sender) {
        this.recipient = recipient;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPassfield() {
        return passfield;
    }

    public void setPassfield(String passfield) {
        this.passfield = passfield;
    }
}