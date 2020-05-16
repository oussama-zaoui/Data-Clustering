package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import model.*;
import util.ImportData;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;

import java.util.ResourceBundle;



public class Controller implements Initializable {

    @FXML
    public Pane pane;
    public TextField k;
    public TextField maxIterations;
    public GridPane table;


    private Acp acp;
    private DataSet dataSet;
    private Kmeans kmeans;
    private String filePath="";




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    public void displayInTable() throws IOException{
        //table.getChildren().clear();
        ImportData data =new ImportData();
        TableView tableView;
        tableView=data.readExcel(filePath);
        dataSet=tableView.getDataSet();
        System.out.println(table.getColumnCount());
        System.out.println(table.getRowCount());


        for (int i = 0; i <tableView.getEntet().size() ; i++) {
            table.add(new TextField(tableView.getEntet().get(i)+i),i,
                    0);
            System.out.println("column is: "+table.getColumnCount());

        }
        for (int i = 0; i <dataSet.getCol() ; i++) {
            for (int j = 1; j <dataSet.getRow() ; j++) {
                Text text=new Text(String.valueOf(dataSet.getData()[j][i]));
                text.setTextAlignment(TextAlignment.CENTER);
                table.add(text,i,j);
            }
        }
        table.setGridLinesVisible(true);

    }

    @FXML
    public void openFile(ActionEvent event){
        FileChooser chooser=new FileChooser();
        chooser.setTitle("Open Resource File");
       File selectedFile= chooser.showOpenDialog(null);
       if (selectedFile!=null)
           filePath=selectedFile.getPath();

        try {
            displayInTable();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void plot(ActionEvent evente){
        ImportData data=new ImportData();
        try{
            dataSet=data.readExcel(filePath).getDataSet();

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

     @FXML
    public void clustering(ActionEvent event){

        pane.getChildren().clear();
        kmeans=new Kmeans(acp,Integer.parseInt(k.getText()));
        ArrayList<Cluster> clusters=new ArrayList<>(kmeans.cluster(Integer.parseInt(maxIterations.getText())));
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
