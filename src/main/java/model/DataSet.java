package model;

public class DataSet {

    private int row;
    private int col;

    private double [][] data;

    public DataSet(int row,int col){
        this.row=row;
        this.col=col;
        data=new double[row][col];
    }


    public double[][] getData() {
        return data;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
