package sample;




import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;
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
        //System.out.println(moys);
        //System.out.println(ecarts);

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


    public ArrayList<Coordonée> cammon(){
        ArrayList<Double> inertieCumulé=new ArrayList<>(3);

        RealMatrix realMatrix= MatrixUtils.createRealMatrix(this.dataSet.getData());
        Covariance covariance=new Covariance(realMatrix);
        RealMatrix mariceCorrelaction=covariance.getCovarianceMatrix();
        EigenDecomposition ed=new EigenDecomposition(mariceCorrelaction);
        double tempe=0;
        double sum=0;
        for (int i = 0; i <ed.getRealEigenvalues().length ; i++) {
            sum=sum+ed.getRealEigenvalues()[i];
        }

        for (int i = 0; i <ed.getRealEigenvalues().length ; i++) {
            tempe=tempe+(ed.getRealEigenvalues()[i]*100)/sum;
            inertieCumulé.add(tempe);
        }

        for (int i = 0; i <ed.getEigenvector(1).getDimension(); i++) {
           // System.out.println(ed.getEigenvector(1).getEntry(i));
        }

        ArrayList<Coordonée> coordonées=new ArrayList<>();
        double x=0,y=0;
        for (int i = 0; i <this.dataSet.getRow() ; i++) {
            for (int j = 0; j <this.dataSet.getCol() ; j++) {
               x=x+(ed.getEigenvector(0).getEntry(j)*this.dataSet.getData()[i][j]);
               y=y+(ed.getEigenvector(1).getEntry(j)*this.dataSet.getData()[i][j]);
            }
            coordonées.add(new Coordonée(x,y));
            x=0;y=0;
        }

        return coordonées;


    }

    public int inercie(int taux,ArrayList<Double> array){

        for (int i = 0; i <array.size() ; i++) {
            if (array.get(i)>=taux)
                return i;
        }

        return -1;

    }



}
