module com.processmanager {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.processmanager to javafx.fxml;
    exports com.processmanager;
}
