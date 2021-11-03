module com.mindblank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires core;

    exports com.mindblank;
    opens com.mindblank to javafx.fxml;

    exports com.mindblank.login;
    opens com.mindblank.login to javafx.fxml;

    exports com.mindblank.patient;
    opens com.mindblank.patient to javafx.fxml;

    exports com.mindblank.entities;
    opens com.mindblank.entities to javafx.fxml;

    opens com.mindblank.doctor.controllers to javafx.fxml;
    exports com.mindblank.doctor.controllers;

    exports com.mindblank.doctor.boundaries;
    opens com.mindblank.doctor.boundaries to javafx.fxml;

    exports com.mindblank.admin.controllers;
    opens com.mindblank.admin.controllers to javafx.fxml;

    exports com.mindblank.admin.boundaries;
    opens com.mindblank.admin.boundaries to javafx.fxml;
}