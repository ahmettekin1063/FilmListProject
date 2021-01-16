package sample.Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Model.FilmData;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        FilmData.getInstance().readFilmFromFile();
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("My Favorite Films");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();



    }

    @Override
    public void stop() throws Exception {
        FilmData.getInstance().writeFilmToFile();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
