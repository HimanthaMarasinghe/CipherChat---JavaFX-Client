package com.mahk.cipherchatjavafx_client.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mahk.cipherchatjavafx_client.util.ApiRequest;
import com.mahk.cipherchatjavafx_client.util.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class conversation {

    @FXML
    private ImageView Contact_image;

    @FXML
    private Text Contact_name;

    @FXML
    private VBox messageVBox;

    public void loadConversation(String userId, String name) throws Exception {
        Contact_name.setText(name);
        String conversation = ApiRequest.sendGet(String.format("getConversation/%s/%s", Session.userId, userId));
        JsonArray conversationArr = JsonParser.parseString(conversation).getAsJsonArray();
        for (JsonElement conversationElement : conversationArr) {
            JsonObject conversationObj = conversationElement.getAsJsonObject();
            Boolean sent = conversationObj.get("senderId").getAsString().equals(userId);
            renderMessageBubble(conversationObj.get("message").getAsString(), conversationObj.get("sendAt").getAsString(), sent);
        }
    }

    public void renderMessageBubble(String message, String sendAt, Boolean sent) throws IOException {
        String fxmlFile = "recivedMessage.fxml";
        if (sent){
            fxmlFile = "sentMessage.fxml";
        }
        FXMLLoader fxmlLoaderR = new FXMLLoader(getClass().getResource("/com/mahk/cipherchatjavafx_client/" + fxmlFile));
        HBox messageBubble = fxmlLoaderR.load();
        Message messageController = fxmlLoaderR.getController();
        messageController.setData(message, sendAt);
        messageVBox.getChildren().add(messageBubble);
    }

}
