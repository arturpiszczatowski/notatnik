package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("U note");
        primaryStage.setScene(new Scene(root, 777, 630));
        primaryStage.setMinWidth(506);
        primaryStage.setMinHeight(360);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
