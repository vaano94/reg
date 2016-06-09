package com.example.Messaging;

import java.io.Serializable;

/**
 * This class represents MailMessage object.
 */
public class MailMessage implements Serializable {

    /**
     * Serial id.
     */
    private static final long serialVersionUID = -3679304901044035566L;
    /**
     * Author email.
     */
    private String author;
    /**
     * Recipient email.
     */
    private String recipient;
    /**
     * Whom to reply.
     */
    private String replyTo;
    /**
     * Subject of message.
     */
    private String subject;
    /**
     * Body of message.
     */
    private String body;

    /**
     * Get recipient.
     * @return recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Set email recipient.
     * @param recipient Recipient recipient
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * Get reply.
     * @return reply
     */
    public String getReplyTo() {
        return replyTo;
    }

    /**
     * Set reply to address.
     * @param replyTo String replyTo
     */
    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    /**
     * Get subject of message.
     * @return String message subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Set message subject.
     * @param subject String subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Get message body.
     * @return String body
     */
    public String getBody() {
        return body;
    }

    /**
     * Set message body.
     * @param body String body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Get author of message.
     * @return String author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set author of message.
     * @param author String author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

}
