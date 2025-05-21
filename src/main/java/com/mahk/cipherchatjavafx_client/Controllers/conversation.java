package com.mahk.cipherchatjavafx_client.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mahk.cipherchatjavafx_client.util.ApiRequest;
import com.mahk.cipherchatjavafx_client.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.HashMap;

public class conversation {

    @FXML
    private ImageView Contact_image;

    @FXML
    private Text Contact_name;

    @FXML
    private VBox messageVBox;

    @FXML
    private TextArea textArea;

    private String reciverId;


    public void loadConversation(String userId, String name) throws Exception {
        Contact_name.setText(name);
        reciverId = userId;
        String conversation = ApiRequest.sendGet(String.format("getConversation/%s/%s", Session.userId, userId));
        JsonArray conversationArr = JsonParser.parseString(conversation).getAsJsonArray();
        for (JsonElement conversationElement : conversationArr) {
            JsonObject conversationObj = conversationElement.getAsJsonObject();
            renderMessageBubble(conversationObj);
        }
    }

    public void renderMessageBubble(JsonObject conversationObj) throws IOException {
        String message = conversationObj.get("message").getAsString();
        String sendAt = conversationObj.get("sendAt").getAsString();
        boolean sent = conversationObj.get("senderId").getAsString().equals(Session.userId);

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

    @FXML
    void sendMessage(ActionEvent event) throws Exception {
        String message = textArea.getText();
        HashMap<String, Object> data = new HashMap<>();
        data.put("senderId", Session.userId);
        data.put("receiverId", this.reciverId);
        data.put("message", message);

        String json = ApiRequest.sendPost("newMessage", data);
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        renderMessageBubble(jsonObject);
        textArea.clear();
    }

}
