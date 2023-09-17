module c195.schedulingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens c195.schedulingapp to javafx.fxml;
    exports c195.schedulingapp;
}
