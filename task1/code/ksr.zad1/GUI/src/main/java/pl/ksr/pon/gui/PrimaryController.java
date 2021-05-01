package pl.ksr.pon.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import pl.ksr.pon.cla.*;
import pl.ksr.pon.dao.ArticleDaoFactory;
import pl.ksr.pon.dao.Dao;
import pl.ksr.pon.ext.Article;
import pl.ksr.pon.ext.ClassifiedPlaces;

import java.io.File;
import java.net.URL;
import java.util.*;


public class PrimaryController implements Initializable {
    @FXML
    private ChoiceBox<String> metricChoiceBox;
    @FXML
    private Slider proportionSlider;
    @FXML
    private Label proportionLabel;
    @FXML
    private Button markAllBtn, unmarkAllBtn, loadFilesBtn, classifyBtn;
    @FXML
    private VBox featuresBox;
    @FXML
    private TextField kNeighboursField;
    @FXML
    private Label filesCountLabel, articlesCountLabel;
    @FXML
    private TableView<Benchmark> resultsTable;
    @FXML
    private BarChart<String, Number> classificationBarChart;

    private ArrayList<String> metricNames;
    double trainPart = 50, testPart;
    int kNeighbours = 0;
    private List<Article> articlesList, trainingList, testingList;
    private Metric selectedMetric;
    private double accuracyValue = 0d;
    private double recallValue = 0d;
    private double precisionValue = 0d;
    private double f1Value = 0d;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileChooser fileChooser = new FileChooser();

        metricNames = new ArrayList<>();
        metricNames.add("Czebyszewa");
        metricNames.add("Uliczna");
        metricNames.add("Euklidesowa");

        metricChoiceBox.getItems().addAll(metricNames);
        metricChoiceBox.setOnAction(actionEvent -> {
            String selectedItem = metricChoiceBox.getSelectionModel().getSelectedItem();
            if (selectedItem.equals(metricNames.get(0))) {
                selectedMetric = new ChebyshevMetric();
            } else if (selectedItem.equals(metricNames.get(1))) {
                selectedMetric = new ManhattanMetric();
            } else if (selectedItem.equals(metricNames.get(2))) {
                selectedMetric = new EuclideanMetric();
            } else {
                selectedMetric = new EuclideanMetric();
            }
        });

        proportionSlider.valueProperty().addListener((observableValue, number, currentNumber) -> {
            trainPart = (double) currentNumber - (double) currentNumber % 5;
            testPart = 100 - trainPart;
            proportionLabel.setText(Math.round(trainPart) + " - treningowa"
                    + " / " + Math.round(testPart) + " - testowa");

        });

        markAllBtn.setOnAction(actionEvent -> {
            for (Node item : featuresBox.getChildren()) {
                ((CheckBox) item).setSelected(true);
            }
        });

        unmarkAllBtn.setOnAction(actionEvent -> {
            for (Node item : featuresBox.getChildren()) {
                ((CheckBox) item).setSelected(false);
            }
        });


        loadFilesBtn.setOnAction(actionEvent -> {
            List<File> filesList = fileChooser.showOpenMultipleDialog(App.getStage());
            Dao<Article> dao = new ArticleDaoFactory().getArticleDao(filesList);
            articlesList = dao.getAll();

            filesCountLabel.setText(String.valueOf(filesList.size()));
            articlesCountLabel.setText(String.valueOf(articlesList.size()));


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


        classifyBtn.setOnAction(actionEvent -> {
//            Collections.shuffle(articlesList);
            int articlesListSize = articlesList.size();
            int divisionIndex = (int) (trainPart / 100 * articlesListSize + 1);
            trainingList = new ArrayList<>(articlesList.subList(0, divisionIndex));
            testingList = new ArrayList<>(articlesList.subList(divisionIndex, articlesListSize));

            if (!kNeighboursField.getText().isEmpty()) {
                kNeighbours = Integer.parseInt(kNeighboursField.getText());
            }

            List<Boolean> booleanList = new ArrayList<>();
            for (Node item : featuresBox.getChildren()) {
                booleanList.add(((CheckBox) item).isSelected());
            }

            for (Article trainingArticle : trainingList) {
                trainingArticle.initFeaturesVector(booleanList);
            }


            KnnClassifier classifier = new KnnClassifier(kNeighbours, selectedMetric);
            int counter = 0;
            for (Article testingArticle : testingList) {
                testingArticle.initFeaturesVector(booleanList);
                classifier.classify(trainingList, testingArticle);

//                System.out.println(counter);
                counter++;
            }

            generateStatistics(benchmarks);

            generateBarChart();
        });

        kNeighboursField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));

    }

    private void generateStatistics(ObservableList<Benchmark> benchmarks) {
        StatisticsGenerator generator = new StatisticsGenerator();
        accuracyValue = generator.countAccuracy(testingList);
        System.out.println(accuracyValue);

        for (ClassifiedPlaces place : ClassifiedPlaces.values()) {
            Map<String, Integer> ratesMap = generator.generateRatesMap(place, testingList);
            int allArticlesFromClass =
                    ratesMap.get("truePositive") + ratesMap.get("falseNegative");

            double singlePrecision = generator.countPrecision(place, testingList);
            System.out.println("Precission " + place.name() + ": " +
                    singlePrecision);
            if (Double.isNaN(singlePrecision)) {
                singlePrecision = 0;
            }
            precisionValue += singlePrecision * allArticlesFromClass;


            double singleRecall = generator.countRecall(place, testingList);
            System.out.println("Recall " + place.name() + ": " +
                    singleRecall);
            if (Double.isNaN(singleRecall)) {
                singleRecall = 0;
            }
            recallValue += singleRecall * allArticlesFromClass;

            System.out.println("F1 " + place.name() + ": " +
                    generator.countF1(place, testingList));
        }

        precisionValue /= testingList.size();
        recallValue /= testingList.size();
        f1Value = 2 * precisionValue * recallValue
                / (precisionValue + recallValue);

        benchmarks.removeAll(benchmarks);
        benchmarks.add(new Benchmark("Accuracy (dokładność)", accuracyValue));
        benchmarks.add(new Benchmark("Precision (precyzja)", precisionValue));
        benchmarks.add(new Benchmark("Recall (czułość)", recallValue));
        benchmarks.add(new Benchmark("F1", f1Value));

        System.out.println("Final precision: " + precisionValue);
        System.out.println("Final recall: " + recallValue);
        System.out.println("Final F1: " + f1Value);

    }

    private void generateBarChart() {
        classificationBarChart.getData().clear();

        Map<ClassifiedPlaces, Integer> beforeStatisticsMap = new HashMap<>();
        Map<ClassifiedPlaces, Integer> afterStatisticsMap = new HashMap<>();
        XYChart.Series<String, Number> beforeSeries = new XYChart.Series<>();
        XYChart.Series<String, Number> afterSeries = new XYChart.Series<>();

        for (ClassifiedPlaces place : ClassifiedPlaces.values()) {
            beforeStatisticsMap.put(place, 0);
            afterStatisticsMap.put(place, 0);
        }

        for (Article article : testingList) {
            beforeStatisticsMap.put(article.getPlace(), beforeStatisticsMap.get(article.getPlace()) + 1);
            afterStatisticsMap.put(article.getPredictedPlace(),
                    afterStatisticsMap.get(article.getPredictedPlace()) + 1);
        }

        for (ClassifiedPlaces placeKey : ClassifiedPlaces.values()) {
            beforeSeries.getData().add(new XYChart.Data<>(placeKey.name(), beforeStatisticsMap.get(placeKey)));
            afterSeries.getData().add(new XYChart.Data<>(placeKey.name(), afterStatisticsMap.get(placeKey)));
        }


        classificationBarChart.getData().addAll(beforeSeries, afterSeries);
    }

}
