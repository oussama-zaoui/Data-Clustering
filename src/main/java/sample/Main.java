package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        URL url = new File("src/main/resources/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(getClass().getResource("src/main/resources/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ImportData data=new ImportData();
        try{
        double [][] dataset=data.readExel("/home/ouss/file_example_XLS_10.xls");

            for (int i = 0; i <10 ; i++) {
                for (int j = 0; j <8 ; j++) {
                    System.out.print(dataset[i][j]);
                }
                System.out.println("");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        launch(args);
    }
}
