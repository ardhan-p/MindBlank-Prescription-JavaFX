module com.mindblank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mindblank to javafx.fxml;
    exports com.mindblank;

    opens com.mindblank.login to javafx.fxml;
    exports com.mindblank.login;

    opens com.mindblank.doctor to javafx.fxml;
    exports com.mindblank.doctor;

}