module pl.ksr.pon.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires Classifier;

    opens pl.ksr.pon.gui to javafx.fxml;
    exports pl.ksr.pon.gui;
}