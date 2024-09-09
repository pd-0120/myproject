module drs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.opencsv;
    
    opens drs to javafx.fxml;
    opens Controller to javafx.fxml;
    opens Enum to javafx.fxml;
    opens Models to javafx.fxml;
    exports Controller;
    exports Enum;
    exports Models;
    exports drs;
}
