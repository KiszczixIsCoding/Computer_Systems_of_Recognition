module pl.ksr.pon.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires pl.ksr.pon.cla;
    requires pl.ksr.pon.dao;

    opens pl.ksr.pon.gui to javafx.fxml;
    exports pl.ksr.pon.gui;
}