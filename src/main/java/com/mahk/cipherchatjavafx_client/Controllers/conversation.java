package com.mahk.cipherchatjavafx_client.Controllers;

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

    public void initialize() {
        messageVBox.getChildren().clear();
        try{
            for(int i = 0; i < 30; i++){
                FXMLLoader fxmlLoaderR = new FXMLLoader(getClass().getResource("/com/mahk/cipherchatjavafx_client/recivedMessage.fxml"));
                HBox recivedMessage = fxmlLoaderR.load();
                messageVBox.getChildren().add(recivedMessage);

                FXMLLoader fxmlLoaderS = new FXMLLoader(getClass().getResource("/com/mahk/cipherchatjavafx_client/sentMessage.fxml"));
                HBox sentMessage = fxmlLoaderS.load();
                messageVBox.getChildren().add(sentMessage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
