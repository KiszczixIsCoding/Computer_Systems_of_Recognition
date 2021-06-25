package pl.ksr.pon.gui;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import pl.ksr.pon.dao.DAO;
import pl.ksr.pon.dao.Player;
import pl.ksr.pon.dao.PlayerDAOFactory;
import pl.ksr.pon.gen.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


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
    @FXML
    public Button switchSubjectsBtn;
    @FXML
    public Button editQuantifier, editSummarizer;
    @FXML
    public Button editQualifier;
    @FXML
    public Label subjectLabel1, subjectLabel2;
    @FXML
    public TableView<LinguisticSummary> summariesTable;
    @FXML
    public TableView<MultisubjectLinguisticSummary> multiSummariesTable;
    @FXML
    public TableColumn<LinguisticSummary, String> textCol;
    @FXML
    public TableColumn<LinguisticSummary, Double> t1Col;
    @FXML
    public TableColumn<LinguisticSummary, Double> t3Col;
    @FXML
    public TableColumn<LinguisticSummary, Double> t4Col;
    @FXML
    public TableColumn<LinguisticSummary, Double> t2Col;
    @FXML
    public TableColumn<LinguisticSummary, Double> t5Col;
    @FXML
    public TableColumn<LinguisticSummary, Double> t6Col;
    @FXML
    public TableColumn<LinguisticSummary, Double> t7Col;
    @FXML
    public TableColumn<LinguisticSummary, Double> t8Col;
    @FXML
    public TableColumn<LinguisticSummary, Double> t9Col;
    @FXML
    public TableColumn<LinguisticSummary, Double> t10Col;
    @FXML
    public TableColumn<LinguisticSummary, Double> t11Col;
    @FXML
    public TableColumn<LinguisticSummary, Double> averageCol;
    @FXML
    public TableColumn<MultisubjectLinguisticSummary, String> multiTextCol;
    @FXML
    public TableColumn<MultisubjectLinguisticSummary, Double> multiMetricCol;

    public TextField textField1, textField2, textField3, textField4,
                     textField5, textField6, textField7, textField8,
                     textField9, textField10, textField11;

    List<LinguisticVariable> linguisticVariables;
    List<RelativeQuantifier> relativeQuantifiers;
    List<AbsoluteQuantifier> absoluteQuantifiers;
    List<String> qualifiersAndSummarizersNames;
    List<String> relativeQuantifiersNames;
    List<String> absoluteQuantifiersNames;
    List<String> allQuantifiersNames;
    List<Player> players = new ArrayList<>();
    ObservableList<LinguisticSummary> summariesToTable = FXCollections.observableArrayList();
    ObservableList<MultisubjectLinguisticSummary> multiSummariesToTable = FXCollections.observableArrayList();
    List<TextField> textFields = new ArrayList<>();
    List<Double> weights_double;
    List<DoubleProperty> weights;
    List<List<Player>> subjects = new ArrayList<>();
    boolean subjectsFlag = true;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DAO<Player> dao = new PlayerDAOFactory().getPlayerDAO("all_seasons.csv");
        try {
            players = dao.getAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringConverter<Number> converter = new NumberStringConverter();
        weights = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            weights.add(new SimpleDoubleProperty(0.09));
        }
        weights.add(new SimpleDoubleProperty(0.1));
        weights_double = weights.stream().map(DoubleExpression::getValue).collect(Collectors.toList());

        Bindings.bindBidirectional(textField1.textProperty(), weights.get(0), converter);
        Bindings.bindBidirectional(textField2.textProperty(), weights.get(1), converter);
        Bindings.bindBidirectional(textField3.textProperty(), weights.get(2), converter);
        Bindings.bindBidirectional(textField4.textProperty(), weights.get(3), converter);
        Bindings.bindBidirectional(textField5.textProperty(), weights.get(4), converter);
        Bindings.bindBidirectional(textField6.textProperty(), weights.get(5), converter);
        Bindings.bindBidirectional(textField7.textProperty(), weights.get(6), converter);
        Bindings.bindBidirectional(textField8.textProperty(), weights.get(7), converter);
        Bindings.bindBidirectional(textField9.textProperty(), weights.get(8), converter);
        Bindings.bindBidirectional(textField10.textProperty(), weights.get(9), converter);
        Bindings.bindBidirectional(textField11.textProperty(), weights.get(10), converter);

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
        linguisticVariables = Predefined.getPredefinedLinguisticVariables(players);
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
                    singleSumQuantifierComboBox.getItems().addAll(FXCollections.observableArrayList(absoluteQuantifiersNames));
                } else if (singleSumFormComboBox.getValue().equalsIgnoreCase("druga")) {
                    singleSumQualifiersComboBox.setDisable(false);
                    singleSumQuantifierComboBox.getItems().clear();
                    singleSumQuantifierComboBox.getItems().addAll(FXCollections.observableArrayList(relativeQuantifiersNames));
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

        //--------------single Subject Table init---------------------------
        textCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        t1Col.setCellValueFactory(new PropertyValueFactory<>("t1"));
        t2Col.setCellValueFactory(new PropertyValueFactory<>("t2"));
        t3Col.setCellValueFactory(new PropertyValueFactory<>("t3"));
        t4Col.setCellValueFactory(new PropertyValueFactory<>("t4"));
        t5Col.setCellValueFactory(new PropertyValueFactory<>("t5"));
        t6Col.setCellValueFactory(new PropertyValueFactory<>("t6"));
        t7Col.setCellValueFactory(new PropertyValueFactory<>("t7"));
        t8Col.setCellValueFactory(new PropertyValueFactory<>("t8"));
        t9Col.setCellValueFactory(new PropertyValueFactory<>("t9"));
        t10Col.setCellValueFactory(new PropertyValueFactory<>("t10"));
        t11Col.setCellValueFactory(new PropertyValueFactory<>("t11"));
        averageCol.setCellValueFactory(new PropertyValueFactory<>("average"));
        summariesTable.setItems(summariesToTable);

        //-----------multi Subject Table init----------------------------------
        multiTextCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        multiMetricCol.setCellValueFactory(new PropertyValueFactory<>("degreeOfTruth"));
        multiSummariesTable.setItems(multiSummariesToTable);

        //-------- Open window for summarizers edition -----------------------------------
        EventHandler<MouseEvent> openSumWindowEvt = e -> {
            try {
                openNewWindow("edit_window", "Sumaryzator");
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        };

        editSummarizer.setOnMouseClicked(openSumWindowEvt);

        EventHandler<MouseEvent> openQualifiersWindowEvt = e -> {
            try {
                openNewWindow("edit_window", "Kwalifikator");
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        };

        editQualifier.setOnMouseClicked(openQualifiersWindowEvt);

        EventHandler<MouseEvent> openQuantifierWindowEvt = e -> {
            try {
                openNewWindow("edit_window", "Kwantyfikator");
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        };

        editQuantifier.setOnMouseClicked(openQuantifierWindowEvt);
        singleGenerate.setOnMouseClicked(mouseEvent1 -> generateSingleSubjectSummary());
        multiGenerate.setOnMouseClicked(mouseEvent1 -> generateMultiSubjectSummary());


        //----------------- Change subjects ---------------------
        List<Player> easternPlayers = players.stream().filter(player -> Clubs.getEasternClubs()
                .contains(player.getTeam())).collect(Collectors.toList());

        List<Player> westernPlayers = players.stream().filter(player -> Clubs.getWesternClubs()
                .contains(player.getTeam())).collect(Collectors.toList());

        subjects.addAll(Arrays.asList(easternPlayers, westernPlayers));
        switchSubjectsBtn.setOnMouseClicked(mouseEvent -> {
            subjects.clear();
            if (subjectsFlag) {
                subjectsFlag = false;
                subjects.addAll(Arrays.asList(westernPlayers, easternPlayers));
                subjectLabel1.setText("Konferencja zachodnia");
                subjectLabel2.setText("Konferencja wschodnia");

            } else {
                subjectsFlag = true;
                subjects.addAll(Arrays.asList(easternPlayers, westernPlayers));
                subjectLabel1.setText("Konferencja wschodnia");
                subjectLabel2.setText("Konferencja zachodnia");
            }
        });
    }

    private void clearScrollPane(ScrollPane pane, Button btn) {
        EventHandler<MouseEvent> clearBtnEvent = event -> removeLabelsFromScroll(pane);
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


    public LinguisticSummary generateSingleSubjectSummary() {
        String quantifierText = singleSumQuantifierLabel.getText();
        String qualifiersText = "";
        String summarizersText = "";
        LinguisticQuantifier quantifier = null;
        List<LinguisticLabel> qualifiers = new ArrayList<>();
        List<LinguisticLabel> summarizers = new ArrayList<>();

        List<LinguisticQuantifier> allQuantifiers = new ArrayList<>();
        allQuantifiers.addAll(relativeQuantifiers);
        allQuantifiers.addAll(absoluteQuantifiers);
        for (LinguisticQuantifier linguisticQuantifier : allQuantifiers) {
            if (linguisticQuantifier.getLabel().getName().equals(quantifierText)) {
                quantifier = linguisticQuantifier;
            }
        }

        VBox singleQualifiersRoot = (VBox) singleSelectedQualifiersPane.getContent();
        if (singleQualifiersRoot.getChildren().size() > 0) {
            for (Node label : singleQualifiersRoot.getChildren()) {
                Label convertedLabel = (Label) label;
                qualifiersText += convertedLabel.getText() + ", ";
                for (LinguisticVariable variable : linguisticVariables) {
                    for (LinguisticLabel linguisticLabel : variable.getLabels()) {
                        if (linguisticLabel.getName().equals(convertedLabel.getText())) {
                            qualifiers.add(linguisticLabel);
                            break;
                        }
                    }
                }
            }
        }

        VBox singleSummarizersRoot = (VBox) singleSelectedSummarizesPane.getContent();
        for (Node label : singleSummarizersRoot.getChildren()) {
            Label convertedLabel = (Label) label;
            summarizersText += convertedLabel.getText() + ", ";
            for (LinguisticVariable variable : linguisticVariables) {
                for (LinguisticLabel linguisticLabel : variable.getLabels()) {
                    if (linguisticLabel.getName().equals(convertedLabel.getText())) {
                        summarizers.add(linguisticLabel);
                        break;
                    }
                }
            }
        }
        String summaryText = "";

        if (singleSumFormComboBox.getValue().equalsIgnoreCase("pierwsza")) {
            summaryText = quantifierText + " zawodników " +
                    " jest " + summarizersText;
        } else {
            summaryText = quantifierText + " zawodników będących " + qualifiersText +
                    " jest " + summarizersText;
        }

        LinguisticSummariesGenerator generator = new LinguisticSummariesGenerator(
                qualifiers, summarizers, quantifier, players);
        LinguisticSummary finalSummary = generator.generateSummary(summaryText);
        weights_double = weights.stream().map(DoubleExpression::getValue).collect(Collectors.toList());
        finalSummary.setAverage(Utils.roundDouble(finalSummary.countWeightedAverage(weights_double),2));
        summariesToTable.add(finalSummary);
        System.out.println(finalSummary);
        return finalSummary;

    }

    public LinguisticSummary generateMultiSubjectSummary() {
        String quantifierText = multiSumQuantifierLabel.getText();
        int form = multiSumFormComboBox.getSelectionModel().getSelectedIndex();
        String summaryText = "";
        StringBuilder qualifiersText = new StringBuilder();
        StringBuilder summarizersText = new StringBuilder();

        List<LinguisticLabel> summarizers = new ArrayList<>();
        List<LinguisticLabel> qualifiers = new ArrayList<>();
        RelativeQuantifier quantifier = null;

        for (RelativeQuantifier linguisticQuantifier : relativeQuantifiers) {
            if (linguisticQuantifier.getLabel().getName().equals(quantifierText)) {
                quantifier = linguisticQuantifier;
            }
        }



        VBox multiSummarizersRoot = (VBox) multiSelectedSummarizersPane.getContent();
        for (Node label : multiSummarizersRoot.getChildren()) {
            Label convertedLabel = (Label) label;
            summarizersText.append(convertedLabel.getText()).append(", ");
            for (LinguisticVariable variable : linguisticVariables) {
                for (LinguisticLabel linguisticLabel : variable.getLabels()) {
                    if (linguisticLabel.getName().equals(convertedLabel.getText())) {
                        summarizers.add(linguisticLabel);
                        break;
                    }
                }
            }
        }

        VBox multiQualifiersRoot1 = (VBox) multiSelectedQualifiersPane1.getContent();
        if (multiQualifiersRoot1.getChildren().size() > 0) {
            for (Node label : multiQualifiersRoot1.getChildren()) {
                Label convertedLabel = (Label) label;
                qualifiersText.append(convertedLabel.getText()).append(", ");
                for (LinguisticVariable variable : linguisticVariables) {
                    for (LinguisticLabel linguisticLabel : variable.getLabels()) {
                        if (linguisticLabel.getName().equals(convertedLabel.getText())) {
                            qualifiers.add(linguisticLabel);
                            break;
                        }
                    }
                }
            }
        }

        VBox multiQualifiersRoot2 = (VBox) multiSelectedQualifiersPane2.getContent();
        if (multiQualifiersRoot2.getChildren().size() > 0) {
            for (Node label : multiQualifiersRoot2.getChildren()) {
                Label convertedLabel = (Label) label;
                qualifiersText.append(convertedLabel.getText()).append(", ");
                for (LinguisticVariable variable : linguisticVariables) {
                    for (LinguisticLabel linguisticLabel : variable.getLabels()) {
                        if (linguisticLabel.getName().equals(convertedLabel.getText())) {
                            qualifiers.add(linguisticLabel);
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(summarizers.size());
        MultisubjectLinguisticSummariesGenerator generator = new MultisubjectLinguisticSummariesGenerator(
                qualifiers, summarizers, quantifier, subjects);


        if (form == 0) {
            summaryText = quantifier.getLabel().getName() + " zawodników z konferencji wschodniej w porównaniu " +
                    "do zawodników z konferencji zachodniej jest " + summarizersText;
        } else if (form == 1) {
            summaryText = quantifier.getLabel().getName() + " zawodników z konferencji wschodniej w porównaniu " +
                    "do zawodników z konferencji zachodniej, którzy są " + qualifiersText + ", jest " + summarizersText;
        } else if (form == 2) {
            summaryText = quantifier.getLabel().getName() + " zawodników z konferencji wschodniej, którzy są " +
                    qualifiersText + ", w porównaniu " + "do zawodników z konferencji zachodniej jest " + summarizersText;
        } else {
            summaryText = "Więcej zawodników z konferencji wschodniej " +
                    "niż z konferencji zachodniej jest " + summarizersText;
        }

        MultisubjectLinguisticSummary finalSummary = generator.generateSummary(summaryText, form);
        System.out.println(finalSummary);

        multiSummariesToTable.add(finalSummary);
        return null;
    }

    public void openNewWindow(String filename, String kindOfLabel) throws IOException {
        App.openNewStage(filename, kindOfLabel);
    }
}

