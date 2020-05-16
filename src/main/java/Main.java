import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        URL url = new File("src/main/resources/fxml/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(getClass().getResource("src/main/resources/fxml/sample.fxml"));
        primaryStage.setTitle("Data-Clustering");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }
}
