package sample;


import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;


public class Acp {

    DataSet dataSet;

    public Acp(DataSet dataSet){
        this.dataSet=dataSet;
    }


    public ArrayList<Double> moy(){

        ArrayList<Double> moys=new ArrayList<>();
        double moy=0;

        for (int i = 0; i <this.dataSet.getCol() ; i++) {
            for (int j = 0; j <this.dataSet.getRow() ; j++) {
                moy=moy+this.dataSet.getData()[j][i];
            }
            moy=moy/this.dataSet.getRow();
            moys.add(moy);
            moy=0;
        }

        return moys;

    }



    public ArrayList<ArrayList<Double>> calculMoyAndEcart(){
        ArrayList<Double> ecarts =new ArrayList<>();
        ArrayList<Double> moys=new ArrayList<>(this.moy());
        ArrayList<ArrayList<Double>> moyAndEcart=new ArrayList<>();
        double ecart;
        double Xbar=0;
        for (int i = 0; i <this.dataSet.getCol() ; i++) {
            for (int j = 0; j <this.dataSet.getRow(); j++) {

               Xbar=Xbar+Math.pow(this.dataSet.getData()[j][i]-moys.get(i),2);
            }

            Xbar=Xbar/this.dataSet.getRow();

            ecart=Math.sqrt(Xbar);
            ecarts.add(ecart);
            Xbar=0;

        }
        moyAndEcart.add(moys);
        moyAndEcart.add(ecarts);
        System.out.println(moys);
        System.out.println(ecarts);

        return moyAndEcart;
    }

    public void centerAndReduce(){

        ArrayList<ArrayList<Double>> moyAndEcart=new ArrayList<>(this.calculMoyAndEcart());

        for (int i = 0; i <this.dataSet.getCol() ; i++) {
            for (int j = 0; j <this.dataSet.getRow() ; j++) {
                this.dataSet.getData()[j][i]=(this.dataSet.getData()[j][i]-moyAndEcart.get(0).get(i))/moyAndEcart.get(1).get(i);
            }

        }

    }



}
