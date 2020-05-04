package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;


public class Controller implements Initializable {

    public Pane pane;

    private Acp acp;
    private DataSet dataSet;
    private Kmeans kmeans;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ImportData data=new ImportData();
        try{
         dataSet=data.readExcel("/home/ouss/maDATA.xlsx");

            for (int i = 0; i <dataSet.getRow() ; i++) {
                for (int j = 0; j <dataSet.getCol() ; j++) {
                    System.out.print(dataSet.getData()[i][j]);
                                    }
                System.out.println();
            }

             acp=new Acp(dataSet);
            acp.centerAndReduce();
            ArrayList<Coordonée> coordonées=new ArrayList<>(acp.cammon());

            final NumberAxis xAxis=new NumberAxis(-5,5,0.5);
            final NumberAxis yAxis=new NumberAxis(-5,5,0.5);
            ScatterChart<Number,Number> chart= new ScatterChart<>(xAxis, yAxis);
            XYChart.Series series1=new XYChart.Series();
            for (int i = 0; i <coordonées.size() ; i++) {
                series1.getData().add(new XYChart.Data(coordonées.get(i).getX(),coordonées.get(i).getY()));
                //System.out.println(coordonées.get(i).getX()+"  "+coordonées.get(i).getY() );
            }

            chart.getData().add(series1);
            pane.getChildren().add(chart);


        }catch (IOException e){
            e.printStackTrace();
        }

    }


    public void goClustering(ActionEvent evente){
           pane.getChildren().clear();
            kmeans=new Kmeans(acp,4);
            ArrayList<Cluster> clusters=new ArrayList<>(kmeans.clauster());
        final NumberAxis xAxis=new NumberAxis(-5,5,0.5);
        final NumberAxis yAxis=new NumberAxis(-5,5,0.5);
        ScatterChart<Number,Number> chart= new ScatterChart<>(xAxis, yAxis);

        for (int i = 0; i <clusters.size() ; i++) {

                XYChart.Series series1=new XYChart.Series();

            for (int j = 0; j <clusters.get(i).getCoordonées().size() ; j++) {
                series1.getData().add(new XYChart.Data(clusters.get(i).getCoordonées().get(j).getX(),clusters.get(i).getCoordonées().get(j).getY()));
            }

            chart.getData().add(series1);


            //System.out.println(coordonées.get(i).getX()+"  "+coordonées.get(i).getY() );
        }

        pane.getChildren().add(chart);

    }

}
