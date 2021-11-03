module com.mindblank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.mindblank;
    opens com.mindblank to javafx.fxml;

    exports com.mindblank.login;
    opens com.mindblank.login to javafx.fxml;

    exports com.mindblank.entities;
    opens com.mindblank.entities to javafx.fxml;

    opens com.mindblank.doctor.controllers to javafx.fxml;
    exports com.mindblank.doctor.controllers;

    exports com.mindblank.doctor.boundaries;
    opens com.mindblank.doctor.boundaries to javafx.fxml;

    exports com.mindblank.patient.boundaries;
    opens com.mindblank.patient.boundaries to javafx.fxml;

    exports com.mindblank.patient.controllers;
    opens com.mindblank.patient.controllers to javafx.fxml;

}