module EmailClient {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;

    opens com.drobert;
    opens com.drobert.view;
    opens com.drobert.controller;

}