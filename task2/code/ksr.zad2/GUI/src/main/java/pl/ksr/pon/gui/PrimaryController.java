package pl.ksr.pon.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class PrimaryController implements Initializable {

    @FXML
    public AnchorPane singlePane;
    @FXML
    public AnchorPane multiPane;
    public ComboBox<String> singleSumQuantifierComboBox;
    public ComboBox<String> singleSumQualifiersComboBox;
    public ComboBox<String> singleSumSummarizersComboBox;
    public Label singleSumQuantifierLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] relativeQuantifiersNames = {"Prawie żaden", "Około 1/4", "Około 1/2",
                "Około 3/4", "Prawie wszystkie"};
        String[] absoluteQuantifiersNames = {"Więcej niż 0", "Około 2000", "Około 4000", "Około 6000",
                "Około 8000", "Prawie 11144"};
        List<String> allQuantifiersList = new ArrayList<String>(Arrays.asList(relativeQuantifiersNames));
        allQuantifiersList.addAll(Arrays.asList(absoluteQuantifiersNames));
        //cast List to array:
        String[] allQuantifiersNames = new String[allQuantifiersList.size()];
        allQuantifiersNames = allQuantifiersList.toArray(allQuantifiersNames);

        singleSumQuantifierComboBox.getItems().addAll(FXCollections.observableArrayList(allQuantifiersNames));
        EventHandler<ActionEvent> setQuantifierLabel = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                singleSumQuantifierLabel.setText(singleSumQuantifierComboBox.getValue());
            }
        };
        singleSumQuantifierComboBox.setOnAction(setQuantifierLabel);
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

