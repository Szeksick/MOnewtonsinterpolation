package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Controller {
@FXML private TextArea result;
@FXML private AnchorPane ap;
private ArrayList<Float> tabx, taby, templist;


    public void calculate(ActionEvent e) {
        Stage stage = (Stage) ap.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Błąd kalkulatora");
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrano pliku");
            alert.showAndWait();
        } else {
            try {
                templist = new ArrayList<Float>();
                try (Scanner scanner = new Scanner(selectedFile)) {
                        while (scanner.hasNext())
                            templist.add(Float.parseFloat(scanner.next()));
                    } catch (FileNotFoundException fileexept) {
                        fileexept.printStackTrace();
                }
                tabx = new ArrayList<Float>(templist.subList(0, (templist.size()/2)));
                taby = new ArrayList<Float>(templist.subList(templist.size()/2,templist.size()));
                templist.clear();

                result.setText("x="+String.valueOf(tabx)+'\n'+"y="+String.valueOf(taby));
            } catch (Exception exept) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Błąd kalkulatora");
                alert.setHeaderText(null);
                alert.setContentText("Niewłaściwy plik");
                alert.showAndWait();
                }
            }
        }
    }



