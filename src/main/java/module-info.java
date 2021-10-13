module com.mindblank {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mindblank to javafx.fxml;
    exports com.mindblank;
    exports com.mindblank.login;
    opens com.mindblank.login to javafx.fxml;
}