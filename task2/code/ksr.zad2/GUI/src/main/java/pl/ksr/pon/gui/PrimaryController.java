package pl.ksr.pon.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import pl.ksr.pon.gen.*;

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
    @FXML
    public ComboBox<String> singleSumQuantifierComboBox;
    @FXML
    public ComboBox<String> singleSumQualifiersComboBox;
    @FXML
    public ComboBox<String> singleSumSummarizersComboBox;
    @FXML
    public Label singleSumQuantifierLabel;
    @FXML
    public ComboBox<String> singleSumFormComboBox;
    @FXML
    public ScrollPane singleSelectedQualifiersPane;
    @FXML
    public ScrollPane singleSelectedSummarizesPane;
    @FXML
    public Button singleGenerate;
    @FXML
    public ComboBox<String> multiSumQuantifierComboBox;
    @FXML
    public ComboBox<String> multiSumQualifiersComboBox1;
    @FXML
    public ComboBox<String> multiSumSummarizersComboBox;
    @FXML
    public ComboBox<String> multiSumFormComboBox;
    @FXML
    public Button multiGenerate;
    @FXML
    public Label multiSumQuantifierLabel;
    @FXML
    public ScrollPane multiSelectedQualifiersPane1;
    @FXML
    public ComboBox<String> multiSumQualifiersComboBox2;
    @FXML
    public ScrollPane multiSelectedQualifiersPane2;
    @FXML
    public ScrollPane multiSelectedSummarizersPane;
    @FXML
    public Button singleQualifiersClearBtn;
    @FXML
    public Button singleSummarizersClearBtn;
    @FXML
    public Button multiQualifiers1ClearBtn;
    @FXML
    public Button multiSummarizersClearBtn;
    @FXML
    public Button multiQualifiers2ClearBtn;

    List<LinguisticVariable> linguisticVariables;
    List<RelativeQuantifier> relativeQuantifiers;
    List<AbsoluteQuantifier> absoluteQuantifiers;
    List<String> qualifiersAndSummarizersNames;
    List<String> relativeQuantifiersNames;
    List<String> absoluteQuantifiersNames;
    List<String> allQuantifiersNames;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //------------------------quantifiers init:---------------------------------
        relativeQuantifiers = Predefined.getRelativeQuantifiers();
        absoluteQuantifiers = Predefined.getAbsoluteQuantifiers();
        relativeQuantifiersNames = new ArrayList<>();
        absoluteQuantifiersNames = new ArrayList<>();
        allQuantifiersNames = new ArrayList<>();
        for (RelativeQuantifier quantifier : relativeQuantifiers) {
            relativeQuantifiersNames.add(quantifier.getLabel().getName());
        }
        for (AbsoluteQuantifier quantifier : absoluteQuantifiers) {
            absoluteQuantifiersNames.add(quantifier.getLabel().getName());
        }
        allQuantifiersNames.addAll(relativeQuantifiersNames);
        allQuantifiersNames.addAll(absoluteQuantifiersNames);
//        String[] relativeQuantifiersNames = {"Prawie żaden", "Około 1/4", "Około 1/2",
//                "Około 3/4", "Prawie wszystkie"};
//        String[] absoluteQuantifiersNames = {"Więcej niż 0", "Około 2000", "Około 4000", "Około 6000",
//                "Około 8000", "Prawie 11144"};
//        List<String> allQuantifiersList = new ArrayList<String>(Arrays.asList(relativeQuantifiersNames));
//        allQuantifiersList.addAll(Arrays.asList(absoluteQuantifiersNames));
//        //cast List to array:
//        String[] allQuantifiersNames = new String[allQuantifiersList.size()];
//        allQuantifiersNames = allQuantifiersList.toArray(allQuantifiersNames);

        //-----------------------single subject summaries quantifiers init---------------------------
        singleSumQuantifierComboBox.getItems().addAll(FXCollections.observableArrayList(allQuantifiersNames));
        EventHandler<ActionEvent> setQuantifierLabel = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                singleSumQuantifierLabel.setText(singleSumQuantifierComboBox.getValue());
            }
        };
        singleSumQuantifierComboBox.setOnAction(setQuantifierLabel);

        //-----------------------qualifiers and summarizers init-----------------------------------
        linguisticVariables = Predefined.getPredefinedLinguisticVariables();
        qualifiersAndSummarizersNames = new ArrayList<>();
        for (LinguisticVariable variable : linguisticVariables) {
            for (LinguisticLabel label : variable.getLabels())
            qualifiersAndSummarizersNames.add(label.getName());
        }
        //cast List to array:
//        String[] qualifiersAndSummarizersNamesArray = new String[qualifiersAndSummarizersNames.size()];
//        qualifiersAndSummarizersNamesArray = qualifiersAndSummarizersNames.
//                toArray(qualifiersAndSummarizersNamesArray);

        //--------------------single subject summaries qualifiers init-----------------------------
//        String[] qualifiersNames = {"test 1", "test 2"};
        prepareComboBoxAndScrollView(qualifiersAndSummarizersNames, singleSumQualifiersComboBox,
                singleSelectedQualifiersPane);
        singleSumQualifiersComboBox.setDisable(true);
        clearScrollPane(singleSelectedQualifiersPane, singleQualifiersClearBtn);


        //----------------single subject summaries summarizes init------------------------------
//        String[] summarizersNames = {"test1", "test2"};
        prepareComboBoxAndScrollView(qualifiersAndSummarizersNames, singleSumSummarizersComboBox,
                singleSelectedSummarizesPane);
        clearScrollPane(singleSelectedSummarizesPane, singleSummarizersClearBtn);

        //-----------------single subject summaries forms init:---------------------------------
        String[] singleForms = {"Pierwsza", "Druga"};
        singleSumFormComboBox.getItems().addAll(FXCollections.observableArrayList(singleForms));
        singleSumFormComboBox.getSelectionModel().selectFirst();
        EventHandler<ActionEvent> singleSubjectFormEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (singleSumFormComboBox.getValue().equalsIgnoreCase("pierwsza")) {
                    singleSumQualifiersComboBox.setDisable(true);
                    removeLabelsFromScroll(singleSelectedQualifiersPane);
                } else if (singleSumFormComboBox.getValue().equalsIgnoreCase("druga")) {
                    singleSumQualifiersComboBox.setDisable(false);
                }
            }
        };
        singleSumFormComboBox.setOnAction(singleSubjectFormEvent);

        //-----------------------multi subject summaries quantifiers init---------------------------
        multiSumQuantifierComboBox.getItems().addAll(FXCollections.observableArrayList(relativeQuantifiersNames));
        EventHandler<ActionEvent> setMultiQuantifierLabel = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                multiSumQuantifierLabel.setText(multiSumQuantifierComboBox.getValue());
            }
        };
        multiSumQuantifierComboBox.setOnAction(setMultiQuantifierLabel);

        //--------------------multi subject summaries qualifiers init-----------------------------
        prepareComboBoxAndScrollView(qualifiersAndSummarizersNames,multiSumQualifiersComboBox1,
                multiSelectedQualifiersPane1);
        prepareComboBoxAndScrollView(qualifiersAndSummarizersNames,multiSumQualifiersComboBox2,
                multiSelectedQualifiersPane2);
        multiSumQualifiersComboBox1.setDisable(true);
        multiSumQualifiersComboBox2.setDisable(true);
        clearScrollPane(multiSelectedQualifiersPane1, multiQualifiers1ClearBtn);
        clearScrollPane(multiSelectedQualifiersPane2, multiQualifiers2ClearBtn);

        //----------------single subject summaries summarizes init------------------------------
        prepareComboBoxAndScrollView(qualifiersAndSummarizersNames, multiSumSummarizersComboBox,
                multiSelectedSummarizersPane);
        clearScrollPane(multiSelectedSummarizersPane, multiSummarizersClearBtn);

        //-----------------multi subject summaries forms init:------------------------
        String[] multiForms = {"Pierwsza", "Druga", "Trzecia", "Czwarta"};
        multiSumFormComboBox.getItems().addAll(FXCollections.observableArrayList(multiForms));
        multiSumFormComboBox.getSelectionModel().selectFirst();
        EventHandler<ActionEvent> multiSubjectFormEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (multiSumFormComboBox.getValue().equalsIgnoreCase("pierwsza")) {
                    multiSumQualifiersComboBox1.setDisable(true);
                    multiSumQualifiersComboBox2.setDisable(true);
                    removeLabelsFromScroll(multiSelectedQualifiersPane1);
                    removeLabelsFromScroll(multiSelectedQualifiersPane2);
                    multiSumQuantifierComboBox.setDisable(false);
                } else if (multiSumFormComboBox.getValue().equalsIgnoreCase("druga")) {
                    multiSumQualifiersComboBox1.setDisable(true);
                    multiSumQualifiersComboBox2.setDisable(false);
                    removeLabelsFromScroll(multiSelectedQualifiersPane1);
                    multiSumQuantifierComboBox.setDisable(false);
                } else if (multiSumFormComboBox.getValue().equalsIgnoreCase("trzecia")) {
                    multiSumQualifiersComboBox2.setDisable(true);
                    multiSumQualifiersComboBox1.setDisable(false);
                    removeLabelsFromScroll(multiSelectedQualifiersPane2);
                    multiSumQuantifierComboBox.setDisable(false);
                } else if (multiSumFormComboBox.getValue().equalsIgnoreCase("czwarta")) {
                    multiSumQualifiersComboBox1.setDisable(true);
                    multiSumQualifiersComboBox2.setDisable(true);
                    removeLabelsFromScroll(multiSelectedQualifiersPane1);
                    removeLabelsFromScroll(multiSelectedQualifiersPane2);
                    multiSumQuantifierComboBox.setDisable(true);
                    multiSumQuantifierLabel.setText("");
                }
            }
        };
        multiSumFormComboBox.setOnAction(multiSubjectFormEvent);

    }

    private void clearScrollPane(ScrollPane pane, Button btn) {
        EventHandler<MouseEvent> clearBtnEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                removeLabelsFromScroll(pane);
            }
        };
        btn.setOnMouseClicked(clearBtnEvent);
    }

    private void removeLabelsFromScroll(ScrollPane pane) {
        VBox root = (VBox) pane.getContent();
        root.getChildren().clear();
    }

    private void prepareComboBoxAndScrollView(List<String> names, ComboBox<String> comboBox, ScrollPane pane) {
        comboBox.getItems().addAll(FXCollections.observableArrayList(names));
        VBox rootForScroll = new VBox();
        pane.setContent(rootForScroll);
        pane.setPannable(true);
        EventHandler<ActionEvent> addLabelEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label label = new Label();
                label.setText(comboBox.getValue());
                rootForScroll.getChildren().add(label);
            }
        };
        comboBox.setOnAction(addLabelEvent);
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

