package pl.ksr.pon.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

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
    public ComboBox<String> singleSumFormComboBox;
    public AnchorPane singleSelectedQualifiersPane;
    public AnchorPane singleSelectedSummarizesPane;
    public Button singleGenerate;
    public ComboBox<String> multiSumQuantifierComboBox;
    public ComboBox<String> multiSumQualifiersComboBox1;
    public ComboBox<String> multiSumSummarizersComboBox;
    public ComboBox<String> multiSumFormComboBox;
    public Button multiGenerate;
    public Label multiSumQuantifierLabel;
    public AnchorPane multiSelectedQualifiersPane1;
    public ComboBox<String> multiSumQualifiersComboBox2;
    public AnchorPane multiSelectedQualifiersPane2;
    public AnchorPane multiSelectedSummarizersPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //------------------------quantifiers init:---------------------------------
        String[] relativeQuantifiersNames = {"Prawie żaden", "Około 1/4", "Około 1/2",
                "Około 3/4", "Prawie wszystkie"};
        String[] absoluteQuantifiersNames = {"Więcej niż 0", "Około 2000", "Około 4000", "Około 6000",
                "Około 8000", "Prawie 11144"};
        List<String> allQuantifiersList = new ArrayList<String>(Arrays.asList(relativeQuantifiersNames));
        allQuantifiersList.addAll(Arrays.asList(absoluteQuantifiersNames));
        //cast List to array:
        String[] allQuantifiersNames = new String[allQuantifiersList.size()];
        allQuantifiersNames = allQuantifiersList.toArray(allQuantifiersNames);

        //-----------------------single subject summarizes quantifiers init---------------------------
        singleSumQuantifierComboBox.getItems().addAll(FXCollections.observableArrayList(allQuantifiersNames));
        EventHandler<ActionEvent> setQuantifierLabel = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                singleSumQuantifierLabel.setText(singleSumQuantifierComboBox.getValue());
            }
        };
        singleSumQuantifierComboBox.setOnAction(setQuantifierLabel);

        //--------------------single subject summarizes qualifiers init-----------------------------
        singleSumQualifiersComboBox.setDisable(true);

        //-----------------single subject summarizes forms init:------------------------
        String[] singleForms = {"Pierwsza", "Druga"};
        singleSumFormComboBox.getItems().addAll(FXCollections.observableArrayList(singleForms));
        singleSumFormComboBox.getSelectionModel().selectFirst();
        EventHandler<ActionEvent> singleSubjectFormEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (singleSumFormComboBox.getValue().equalsIgnoreCase("pierwsza")) {
                    singleSumQualifiersComboBox.setDisable(true);
                    singleSelectedQualifiersPane.getChildren().clear();
                } else if (singleSumFormComboBox.getValue().equalsIgnoreCase("druga")) {
                    singleSumQualifiersComboBox.setDisable(false);
                }
            }
        };
        singleSumFormComboBox.setOnAction(singleSubjectFormEvent);

        //-----------------------multi subject summarizes quantifiers init---------------------------
        multiSumQuantifierComboBox.getItems().addAll(FXCollections.observableArrayList(relativeQuantifiersNames));
        EventHandler<ActionEvent> setMultiQuantifierLabel = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                multiSumQuantifierLabel.setText(multiSumQuantifierComboBox.getValue());
            }
        };
        multiSumQuantifierComboBox.setOnAction(setMultiQuantifierLabel);

        //--------------------multi subject summarizes qualifiers init-----------------------------
        multiSumQualifiersComboBox1.setDisable(true);
        multiSumQualifiersComboBox2.setDisable(true);

        //-----------------multi subject summarizes forms init:------------------------
        String[] multiForms = {"Pierwsza", "Druga", "Trzecia", "Czwarta"};
        multiSumFormComboBox.getItems().addAll(FXCollections.observableArrayList(multiForms));
        multiSumFormComboBox.getSelectionModel().selectFirst();
        EventHandler<ActionEvent> multiSubjectFormEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (multiSumFormComboBox.getValue().equalsIgnoreCase("pierwsza")) {
                    multiSumQualifiersComboBox1.setDisable(true);
                    multiSumQualifiersComboBox2.setDisable(true);
                    multiSelectedQualifiersPane1.getChildren().clear();
                    multiSelectedQualifiersPane2.getChildren().clear();
                    multiSumQuantifierComboBox.setDisable(false);
                } else if (multiSumFormComboBox.getValue().equalsIgnoreCase("druga")) {
                    multiSumQualifiersComboBox1.setDisable(true);
                    multiSumQualifiersComboBox2.setDisable(false);
                    multiSelectedQualifiersPane1.getChildren().clear();
                    multiSumQuantifierComboBox.setDisable(false);
                } else if (multiSumFormComboBox.getValue().equalsIgnoreCase("trzecia")) {
                    multiSumQualifiersComboBox2.setDisable(true);
                    multiSumQualifiersComboBox1.setDisable(false);
                    multiSelectedQualifiersPane2.getChildren().clear();
                    multiSumQuantifierComboBox.setDisable(false);
                } else if (multiSumFormComboBox.getValue().equalsIgnoreCase("czwarta")) {
                    multiSumQualifiersComboBox1.setDisable(true);
                    multiSumQualifiersComboBox2.setDisable(true);
                    multiSelectedQualifiersPane1.getChildren().clear();
                    multiSelectedQualifiersPane2.getChildren().clear();
                    multiSumQuantifierComboBox.setDisable(true);
                    multiSumQuantifierLabel.setText("");
                }
            }
        };
        multiSumFormComboBox.setOnAction(multiSubjectFormEvent);

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

