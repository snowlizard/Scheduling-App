module c195.schedulingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens c195.schedulingapp to javafx.fxml;
    exports c195.schedulingapp;
}
