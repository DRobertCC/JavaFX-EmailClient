module EmailClient {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires activation;
    requires java.mail; //https://myaccount.google.com/lesssecureapps: turn on

    opens com.drobert;
    opens com.drobert.view;
    opens com.drobert.controller;

}