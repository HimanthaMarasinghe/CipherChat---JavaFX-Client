package com.mahk.cipherchatjavafx_client.Controllers;

import com.mahk.cipherchatjavafx_client.util.TimeUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Message {

    @FXML
    private Label Message;

    @FXML
    private ImageView ProfileImg;

    @FXML
    private Text Time;

    public void setData(String message, String time) {
        String formatedTime = TimeUtil.formatedTime(time, false);
        Time.setText(formatedTime);
        Message.setText(message);
    }

}
