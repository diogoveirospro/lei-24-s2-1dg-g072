module pt.ipp.isep.dei.esoft.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires AuthLib;
    requires java.management;
    requires org.apache.commons.lang3;
    requires java.logging;

    opens pt.ipp.isep.dei.esoft.project to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project;
}
