package com.drobert.controller;

import com.drobert.EmailManager;
import com.drobert.controller.services.LoginService;
import com.drobert.model.EmailAccount;
import com.drobert.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController extends BaseController implements Initializable {

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    private TextField emailAddressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    void loginButtonAction() {
        errorLabel.setText("");
        System.out.println("loginButtonAction");
        if (fieldsAreValid()) {
            EmailAccount emailAccount = new EmailAccount(emailAddressField.getText(), passwordField.getText());
            LoginService loginService = new LoginService(emailAccount, emailManager); // Creating a new background service
            loginService.start(); // Starting it. The loginService.login() runs in the background
            loginService.setOnSucceeded(event -> { // When the task finished successfully, then and only then the execution of the program continues.

                EmailLoginResult emailLoginResult = loginService.getValue();

                switch (emailLoginResult) {
                    case SUCCESS:
                        System.out.println("Success" + emailAccount);
                        if (!viewFactory.isMainViewInitialized()) {
                            viewFactory.showMainWindow();
                        }
                        Stage stage = (Stage) errorLabel.getScene().getWindow();
                        viewFactory.closeStage(stage);
                        return;
                    case FAILED_BY_CREDENTIALS:
                        errorLabel.setText("Invalid credentials");
                        return;
                    case FAILED_BY_UNEXPECTED_ERROR:
                        errorLabel.setText("Unexpected error");
                        return;
                    default:
                        return;
                }
            });

        }
    }

    private boolean fieldsAreValid() {
        if (emailAddressField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            errorLabel.setText("Missing Email address and/or Password");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailAddressField.setText("vidileso@gmail.com");
        passwordField.setText("HVYcfi981");
    }
}
