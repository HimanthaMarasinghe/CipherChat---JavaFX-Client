module com.mahk.cipherchatjavafx_client {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mahk.cipherchatjavafx_client to javafx.fxml;
    exports com.mahk.cipherchatjavafx_client;
    opens com.mahk.cipherchatjavafx_client.Controllers to javafx.fxml;
    exports com.mahk.cipherchatjavafx_client.Controllers;
}