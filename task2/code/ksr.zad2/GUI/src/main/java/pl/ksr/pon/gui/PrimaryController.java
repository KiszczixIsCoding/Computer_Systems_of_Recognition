package pl.ksr.pon.gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class PrimaryController implements Initializable {

    @FXML
    public AnchorPane singlePane;
    @FXML
    public AnchorPane multiPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //nie wiem czy najbardziej optymalna opcja
    public void goToMultiSubject(MouseEvent mouseEvent) {
        singlePane.setVisible(false);
        multiPane.setVisible(true);
    }

    public void goToSingleSubject(MouseEvent mouseEvent) {
        multiPane.setVisible(false);
        singlePane.setVisible(true);
    }


}

