package com.drobert;

import com.drobert.controller.services.FetchFoldersService;
import com.drobert.model.EmailAccount;
import com.drobert.model.EmailTreeItem;

public class EmailManager {

    // Folder handling:
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<String>("");


    public EmailTreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount) {
        EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getAddress());
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem);
        fetchFoldersService.start();
        foldersRoot.getChildren().add(treeItem);
    }
}
