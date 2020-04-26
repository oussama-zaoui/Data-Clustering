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
        DataSet dataSet=data.readExcel("/home/ouss/file_example_XLS_10.xls");

        Acp acp=new Acp(dataSet);
        acp.centerAndReduce();

            for (int i = 0; i <dataSet.getRow() ; i++) {
                for (int j = 0; j <dataSet.getCol() ; j++) {
                    System.out.print(acp.dataSet.getData()[i][j]+" ");
                }
                System.out.println("");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        launch(args);
    }
}
