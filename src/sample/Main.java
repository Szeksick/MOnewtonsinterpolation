package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("Konrad Gugała gr 11A - Interpolacja metodą Newtona");
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("Style.css");
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
