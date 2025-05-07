package com.mahk.cipherchatjavafx_client.Controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;

public class contact {

    @FXML
    private Text lastMessageField;

    @FXML
    private Text nameField;

    @FXML
    private Text newCount;

    @FXML
    private Circle newMessagesCircle;

    private Main main;
    private String userId;
    private String name;

    @FXML
    void loadConversation(MouseEvent event) throws Exception {
        this.main.loadConversation(this.userId, this.name);
    }

    public void setData(String name, String lastMessage, String count, Main main, String userId) {
        lastMessageField.setText(lastMessage);
        nameField.setText(name);
        newCount.setText(count);

        this.main = main;
        this.userId = userId;
        this.name = name;
    }
}