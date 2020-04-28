package sample;

import javafx.fxml.Initializable;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.DoubleToIntFunction;

public class Controller implements Initializable {

    public Pane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ImportData data=new ImportData();
        try{
            DataSet dataSet=data.readExcel("/home/ouss/chekla.xlsx");

            Acp acp=new Acp(dataSet);
            acp.centerAndReduce();
            ArrayList<Coordonée> coordonées=new ArrayList<>(acp.cammon());

            final NumberAxis xAxis=new NumberAxis(-2,3,0.5);
            final NumberAxis yAxis=new NumberAxis(-2,3,0.5);
            ScatterChart<Number,Number> chart= new ScatterChart<>(xAxis, yAxis);
            XYChart.Series series1=new XYChart.Series();
            for (int i = 0; i <coordonées.size() ; i++) {
                series1.getData().add(new XYChart.Data(coordonées.get(i).getX(),coordonées.get(i).getY()));
                System.out.println(coordonées.get(i).getX()+"  "+coordonées.get(i).getY() );
            }







            chart.getData().add(series1);
            pane.getChildren().add(chart);


        }catch (IOException e){
            e.printStackTrace();
        }








    }
}
