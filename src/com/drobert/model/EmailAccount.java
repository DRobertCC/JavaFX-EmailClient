package com.drobert.model;

import javax.mail.Store;
import java.util.Properties;

public class EmailAccount {

    private String address;
    private String password;
    private Properties properties;
    private Store store;

    public EmailAccount(String address, String password) {
        this.address = address;
        this.password = password;
        properties = new Properties(); // https://support.google.com/mail/answer/7126229
        properties.put("incomingHost", "imap.gmail.com"); // Sending emails
        properties.put("mail.store.protocol", "imaps");

        properties.put("mail.transport.protocol", "smtps"); // Receiving emails
        properties.put("mail.smtps.host", "smtp.gmail.com");
        properties.put("mail.smtps.auth", "true");
        properties.put("outgoingHost", "smtp.gmail.com");
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
