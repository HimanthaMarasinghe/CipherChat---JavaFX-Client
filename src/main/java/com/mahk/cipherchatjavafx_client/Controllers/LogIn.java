package com.mahk.cipherchatjavafx_client.Controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mahk.cipherchatjavafx_client.util.ApiRequest;
import com.mahk.cipherchatjavafx_client.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LogIn {

    @FXML
    private Button LogIn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label welcomeText;

    @FXML
    private Label welcomeText1;

    @FXML
    void viewHome(ActionEvent event) throws Exception {

        String usernameInput = username.getText();
        String passwordInput = password.getText();

        if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a valid username and password");
            alert.showAndWait();
        }

        String json = String.format("{\"name\":\"%s\", \"password\":\"%s\"}", usernameInput, passwordInput);
        String postResponse = ApiRequest.sendPost("login", json);

        if (postResponse.contains("token")) {

            JsonObject jsonObj = JsonParser.parseString(postResponse).getAsJsonObject();
            Session.token = jsonObj.get("token").getAsString();
            Session.userId = jsonObj.get("userId").getAsString();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mahk/cipherchatjavafx_client/main.fxml"));
            Scene Scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(Scene);
            stage.setTitle("JavaChat");
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username or password");
            alert.showAndWait();
        }
    }
}
