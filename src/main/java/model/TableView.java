package model;

import java.util.ArrayList;

public class TableView {

    private ArrayList<String> entet;
    private DataSet dataSet;


    public TableView(ArrayList<String> entet,DataSet dataSet){
        this.entet=entet;
        this.dataSet=dataSet;
    }

    public TableView() {

    }


    public ArrayList<String> getEntet() {
        return entet;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public void setEntet(ArrayList<String> entet) {
        this.entet = entet;
    }
}
