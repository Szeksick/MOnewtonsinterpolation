package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Controller {
@FXML private TextArea resultout;
@FXML private Label filename;
@FXML private AnchorPane ap;
private ArrayList<Double> tabx, taby, templist;
private ArrayList<ArrayList<Double>> subresult;
private Double h;


    public void calculate(ActionEvent e) {
        Stage stage = (Stage) ap.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        filename.setText(selectedFile.getName());
        if (selectedFile == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Błąd kalkulatora");
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrano pliku");
            alert.showAndWait();
        } else {
            try {
                templist = new ArrayList<Double>();
                try (Scanner scanner = new Scanner(selectedFile)) {
                        while (scanner.hasNext())
                            templist.add(Double.parseDouble(scanner.next()));
                    } catch (FileNotFoundException fileexept) {
                        fileexept.printStackTrace();
                }
                filename.setText("Wybrany plik: "+selectedFile.getName());
                tabx = new ArrayList<Double>(templist.subList(0, (templist.size()/2)));
                taby = new ArrayList<Double>(templist.subList(templist.size()/2,templist.size()));
                templist.clear();
                h = tabx.get(1) - tabx.get(0);
                subresult = new ArrayList<ArrayList<Double>>();
                for(int i = 0; i<tabx.size()-1; i++) {
                    subresult.add(new ArrayList<Double>());
                    if(i==0){
                        for (int j = 1; j <tabx.size(); j++) {
                            subresult.get(i).add(taby.get(j)-taby.get(j-1));
                        }
                    }else {
                        for (int j = 1; j <tabx.size() - i; j++) {
                            subresult.get(i).add(subresult.get(i-1).get(j)-subresult.get(i-1).get(j-1));
                        }
                    }
                }

                resultout.setText("W"+String.valueOf(tabx.size()-1)+"(x)=");
                resultout.appendText(String.valueOf(taby.get(0)));
                for(int i = 0; i<tabx.size()-1; i++) {
                    resultout.appendText(String.format("%s%.1f%s%d%s%.1f%s%d%s", "+(", subresult.get(i).get(0), "/(",i+1,"!*",h,"^",i+1,"))"));
                        for (int j = 0; j <i+1; j++) {
                            resultout.appendText(String.format("%s%.1f%s", "*(x-", tabx.get(j), ")"));
                        }
                }
            } catch (Exception exept) {
                exept.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Błąd kalkulatora");
                alert.setHeaderText(null);
                alert.setContentText("Niewłaściwy plik");
                alert.showAndWait();
                }
            }
        }
    }



