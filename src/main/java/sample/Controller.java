package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;


import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;



import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;

import java.util.ResourceBundle;



public class Controller implements Initializable {

    public Pane pane;
    public TextField k;
    public TextField maxIterations;

    private Acp acp;
    private DataSet dataSet;
    private Kmeans kmeans;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




    }


    public void plot(ActionEvent evente){
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

            NumberAxis  XAxis=new NumberAxis(-5,5,0.5);
            NumberAxis YAxis=new NumberAxis(-5,5,0.5);

            ScatterChart chart=new ScatterChart<>(XAxis,YAxis);
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


    public void clusterize(ActionEvent event){

        pane.getChildren().clear();
        kmeans=new Kmeans(acp,Integer.parseInt(k.getText()));
        ArrayList<Cluster> clusters=new ArrayList<>(kmeans.clauster(Integer.parseInt(maxIterations.getText())));
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
