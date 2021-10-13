module com.mindblank {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mindblank to javafx.fxml;
    exports com.mindblank;
}