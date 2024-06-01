module pt.ipp.isep.dei.esoft.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires AuthLib;
    requires java.management;
    requires org.apache.commons.lang3;
    requires java.logging;
    requires java.desktop;

    opens pt.ipp.isep.dei.esoft.project to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.ui.gui.ui to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.ui.gui.controller to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss to javafx.fxml;

    exports pt.ipp.isep.dei.esoft.project;
    exports pt.ipp.isep.dei.esoft.project.ui.gui.ui to javafx.graphics, javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui.gui.controller to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss to javafx.fxml, javafx.graphics;
}

