module c195.schedulingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens c195.schedulingapp to javafx.fxml;
    opens c195.schedulingapp.Controllers to javafx.fxml;
    opens c195.schedulingapp.Models to javafx.fxml;
    opens c195.schedulingapp.Singletons to javafx.fxml;
    opens c195.schedulingapp.DBAccess to javafx.fxml;
    
    exports c195.schedulingapp;
    exports c195.schedulingapp.Controllers;
    exports c195.schedulingapp.Models;
    exports c195.schedulingapp.Singletons;
    exports c195.schedulingapp.DBAccess;
}
