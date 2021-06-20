package pl.ksr.pon.gui;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pl.ksr.pon.fuz.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class EditSummarizerController implements Initializable {
    @FXML
    public Button add_btn;

    @FXML
    public Label kindOfLabel, firstArgLabel, secondArgLabel, thirdArgLabel, forthArgLabel;

    @FXML
    public TextField firstArgField, secondArgField, thirdArgField, forthArgField;

    @FXML
    public ChoiceBox<String> membershipChoice;

    @FXML
    public Button addBtn;


    private MembershipFunction selectedMembershipFunction;
    private String myString = "abc";

    public void initData(String summerizer) {
        this.myString = summerizer;
        List<String> functionsList =
                new ArrayList<>(Arrays.asList("Gaussowska", "Trójkątna", "Trapezoidalna", "Binarna", "Wykładnicza"));

        ObservableList<String> membershipFunctions = FXCollections.observableArrayList(functionsList);

        kindOfLabel.setText(myString);
        membershipChoice.setItems(membershipFunctions);

        membershipChoice.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            if (t1.equals(0)) {
                thirdArgField.setVisible(false);
                forthArgField.setVisible(false);
                thirdArgLabel.setVisible(false);
                forthArgLabel.setVisible(false);
                firstArgLabel.setText("Srednia");
                secondArgLabel.setText("Wariancja");
            } else if (t1.equals(1)) {
                thirdArgField.setVisible(true);
                forthArgField.setVisible(false);
                thirdArgLabel.setVisible(true);
                forthArgLabel.setVisible(false);
                firstArgLabel.setText("x1");
                secondArgLabel.setText("x2");
                thirdArgLabel.setText("x3");
            } else if (t1.equals(2)) {
                thirdArgField.setVisible(true);
                forthArgField.setVisible(true);
            } else if (t1.equals(3)) {
//                thirdArgField.setVisible(false);
//                forthArgField.setVisible(false);
            } else if (t1.equals(4)) {
//                thirdArgField.setVisible(false);
//                forthArgField.setVisible(false);
            }
        });

        EventHandler<MouseEvent> clickEvt = mouseEvent -> {
            int selectedIndex = membershipChoice.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0) {
                selectedMembershipFunction = new GaussianMembershipFunction();
            } else if (selectedIndex == 1) {

                selectedMembershipFunction = new LeftTriangularMembershipFunction();
            } else if (selectedIndex == 2) {
                selectedMembershipFunction = new LeftTrapezoidalMembershipFunction() {
                }
            }
        };

        addBtn.setOnMouseClicked(clickEvt);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        System.out.println(myString);
    }
}
