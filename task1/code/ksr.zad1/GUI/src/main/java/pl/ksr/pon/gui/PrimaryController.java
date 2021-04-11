package pl.ksr.pon.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import pl.ksr.pon.cla.ChebyshevMetric;
import pl.ksr.pon.cla.EuclideanMetric;
import pl.ksr.pon.cla.ManhattanMetric;
import pl.ksr.pon.cla.Metric;
import pl.ksr.pon.dao.ArticleDaoFactory;
import pl.ksr.pon.dao.Dao;
import pl.ksr.pon.ext.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class PrimaryController implements Initializable {
    @FXML private ChoiceBox<String> metricChoiceBox;
    @FXML private Slider proportionSlider;
    @FXML private Label proportionLabel;
    @FXML private Button markAllBtn, unmarkAllBtn, loadFilesBtn, classifyBtn;
    @FXML private VBox tradesBox;
    @FXML private TextField kNeighboursField;
    @FXML private Label filesCountLabel, articlesCountLabel;
    @FXML private TableView<Benchmark> resultsTable;

    private ArrayList<String> metricNames;
    double trainPart, testPart;
    int kNeighbours = 0;
    private List<Article> articlesList;
    private Metric selectedMetric;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileChooser fileChooser = new FileChooser();

        metricNames = new ArrayList<>();
        metricNames.add(" ");
        metricNames.add("Czebyszewa");
        metricNames.add("Uliczna");
        metricNames.add("Euklidesowa");

        metricChoiceBox.getItems().addAll(metricNames);
        metricChoiceBox.setOnAction(actionEvent -> {
            String selectedItem = metricChoiceBox.getSelectionModel().getSelectedItem();
            if (selectedItem.equals(metricNames.get(0))) {
                selectedMetric = null;
            } else if (selectedItem.equals(metricNames.get(1))) {
                selectedMetric = new ChebyshevMetric();
            } else if (selectedItem.equals(metricNames.get(2))) {
                selectedMetric = new ManhattanMetric();
            } else {
                selectedMetric = new EuclideanMetric();
            }
        });

        proportionSlider.valueProperty().addListener((observableValue, number, currentNumber) -> {
            trainPart = (double)currentNumber - (double)currentNumber % 5;
            testPart = 100 - trainPart;
            proportionLabel.setText(Math.round(trainPart) + " - treningowa"
                    + " / " + Math.round(testPart) + " - testowa");
        });

        markAllBtn.setOnAction(actionEvent -> {
            for (Node item : tradesBox.getChildren()) {
                ((CheckBox)item).setSelected(true);
            }
        });

        unmarkAllBtn.setOnAction(actionEvent -> {
            for (Node item : tradesBox.getChildren()) {
                ((CheckBox)item).setSelected(false);
            }
        });


        loadFilesBtn.setOnAction(actionEvent -> {
            List<File> filesList = fileChooser.showOpenMultipleDialog(App.getStage());
            Dao<Article> dao = new ArticleDaoFactory().getArticleDao(filesList);
            articlesList = dao.getAll();
            filesCountLabel.setText(String.valueOf(filesList.size()));
            articlesCountLabel.setText(String.valueOf(articlesList.size()));
        });

        classifyBtn.setOnAction(actionEvent -> {
            if (!kNeighboursField.getText().isEmpty()) {
                kNeighbours = Integer.parseInt(kNeighboursField.getText());
            }
        });

        TableColumn<Benchmark, String> nameColumn = new TableColumn<>("Miara podobieństwa");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Benchmark, String> valueColumn = new TableColumn<>("Rezultat");
        valueColumn.setMinWidth(100);
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        ObservableList<Benchmark> benchmarks = FXCollections.observableArrayList();
        benchmarks.add(new Benchmark("Accuracy (dokładność)", 0d));
        benchmarks.add(new Benchmark("Precision (precyzja)", 0d));
        benchmarks.add(new Benchmark("Recall (czułość)", 0d));
        benchmarks.add(new Benchmark("F1", 0d));

        resultsTable.setItems(benchmarks);
        resultsTable.getColumns().addAll(nameColumn, valueColumn);


        kNeighboursField.setTextFormatter(new TextFormatter<>(change ->
                        (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));

    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
