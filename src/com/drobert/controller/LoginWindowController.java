package com.drobert.controller;

import com.drobert.EmailManager;
import com.drobert.controller.services.LoginService;
import com.drobert.model.EmailAccount;
import com.drobert.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController extends BaseController {

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
            LoginService loginService = new LoginService(emailAccount, emailManager);
            EmailLoginResult emailLoginResult = loginService.login();

            switch (emailLoginResult) {
                case SUCCESS:
                    System.out.println("Success" + emailAccount);
                    viewFactory.showMainWindow();
                    Stage stage = (Stage) errorLabel.getScene().getWindow();
                    viewFactory.closeStage(stage);
                    return;
            }
        }
    }

    private boolean fieldsAreValid() {
        if (emailAddressField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            errorLabel.setText("Missing Email address and/or Password");
            return false;
        }
        return true;
    }
}
