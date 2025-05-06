module com.mahk.cipherchatjavafx_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.mahk.cipherchatjavafx_client to javafx.fxml;
    exports com.mahk.cipherchatjavafx_client;
    opens com.mahk.cipherchatjavafx_client.Controllers to javafx.fxml;
    exports com.mahk.cipherchatjavafx_client.Controllers;
}