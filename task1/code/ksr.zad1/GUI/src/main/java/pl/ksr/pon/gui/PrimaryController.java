package pl.ksr.pon.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import pl.ksr.pon.cla.ChebyshevMetric;
import pl.ksr.pon.cla.EuclideanMetric;
import pl.ksr.pon.cla.ManhattanMetric;
import pl.ksr.pon.cla.Metric;


public class PrimaryController implements Initializable {
    @FXML private ChoiceBox<String> metricChoiceBox;
    @FXML private Slider proportionSlider;
    @FXML private Label proportionLabel;
    @FXML private Button markAllBtn, unmarkAllBtn;
    @FXML private VBox tradesBox;
    @FXML private Button loadFiles;
    private ArrayList<String> metricNames;
    private Metric selectedMetric;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
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
            double trainPart = (double)currentNumber - (double)currentNumber % 5;
            double testPart = 100 - trainPart;
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

        loadFiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                directoryChooser.showDialog(App.getStage());
            }
        });

    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
