package com.mahk.cipherchatjavafx_client.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Main {

    @FXML
    private VBox Contacts;

    @FXML
    private AnchorPane conversationPane;

    public void initialize() {
        Contacts.getChildren().clear();
        conversationPane.getChildren().clear();
        try {
            for(int i = 0; i < 30; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mahk/cipherchatjavafx_client/contact.fxml"));
                HBox contactsHBox = fxmlLoader.load();
                Contacts.getChildren().add(contactsHBox);
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mahk/cipherchatjavafx_client/conversation.fxml"));
            VBox conversationVBox = fxmlLoader.load();

            AnchorPane.setTopAnchor(conversationVBox, 0.0);
            AnchorPane.setLeftAnchor(conversationVBox, 0.0);
            AnchorPane.setRightAnchor(conversationVBox, 0.0);
            AnchorPane.setBottomAnchor(conversationVBox, 0.0);

            conversationPane.getChildren().add(conversationVBox);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}