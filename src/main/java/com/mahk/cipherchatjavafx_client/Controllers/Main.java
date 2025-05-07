package com.mahk.cipherchatjavafx_client.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mahk.cipherchatjavafx_client.util.ApiRequest;
import com.mahk.cipherchatjavafx_client.util.Session;
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

    public void initialize() throws Exception {
        Contacts.getChildren().clear();
        conversationPane.getChildren().clear();

        try {
            String contactsJson = ApiRequest.sendGet("getChats/" + Session.userId);
            JsonArray contactsArray = JsonParser.parseString(contactsJson).getAsJsonArray();

            for(JsonElement contactElem : contactsArray) {
                JsonObject contactObj = contactElem.getAsJsonObject();
                JsonObject chatPartnerObj = contactObj.get("chatPartnerInfo").getAsJsonObject();
                String name = chatPartnerObj.get("name").getAsString();
                String lastMessage = contactObj.get("lastMessage").getAsString();
                String unreadCount = contactObj.get("unreadCount").getAsString();
                String userId = contactObj.get("chatPartnerId").getAsString();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mahk/cipherchatjavafx_client/contact.fxml"));
                HBox contactsHBox = fxmlLoader.load();

                contact controller = fxmlLoader.getController();
                controller.setData(name, lastMessage, unreadCount, this, userId);
                Contacts.getChildren().add(contactsHBox);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConversation(String chatPartnerId, String chatPartnerName) throws Exception {
        conversationPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mahk/cipherchatjavafx_client/conversation.fxml"));
        VBox conversation = fxmlLoader.load();

        AnchorPane.setTopAnchor(conversation, 0.0);
        AnchorPane.setLeftAnchor(conversation, 0.0);
        AnchorPane.setRightAnchor(conversation, 0.0);
        AnchorPane.setBottomAnchor(conversation, 0.0);

        conversation controller = fxmlLoader.getController();
        controller.loadConversation(chatPartnerId, chatPartnerName);
        conversationPane.getChildren().add(conversation);
    }

}