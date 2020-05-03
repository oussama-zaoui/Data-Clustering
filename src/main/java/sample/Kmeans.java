package sample;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Kmeans {

    private Acp input;
    private int k;





    public Kmeans(Acp input,int k){
        this.input=input;
        this.k=k;
    }

    public Acp getInput() {
        return input;
    }

    public int getK() {
        return k;
    }

    public void setInput(Acp input) {
        this.input = input;
    }

    public void setK(int k) {
        this.k = k;
    }


    public ArrayList<Cluster> clauster() {
        ArrayList<Coordonée> points = new ArrayList<>(this.input.cammon());
        ArrayList<Cluster> clausters = new ArrayList<>(initialClausters(this.k, points));
        ArrayList<Coordonée> centers = new ArrayList<>();
        for (int i = 0; i < clausters.size(); i++) {
            centers.add(clausters.get(i).getCoordonées().get(0));
        }

        double version=1;
        ArrayList<Double> versions=new ArrayList<>();
       while (true) {
            double min = Double.MAX_VALUE;
            int index = 0;
            for (Coordonée point : points) {
                for (int i = 0; i < centers.size(); i++) {
                    if (min > point.distance(centers.get(i))) {
                        min = point.distance(centers.get(i));
                        index = i;
                    }
                }
                clausters.get(index).getCoordonées().add(point);
                centers.set(index, point.center(centers.get(index)));
                min = Double.MAX_VALUE;
                index = 0;
            }
            for (int i = 0; i <centers.size() ; i++) {
                version=version*centers.get(i).getX();
            }
           versions.add(version);

           if (versions.size()>1 && versions.get(versions.size() - 1).equals(versions.get(versions.size() - 2)))
                break;
           else
            {
                for (int i = 0; i <clausters.size() ; i++) {
                    clausters.get(i).getCoordonées().clear();
                }
           }

        }

        return clausters;

    }


    public ArrayList<Cluster> initialClausters(int k,ArrayList<Coordonée> points){
        ArrayList<Cluster> clausters=new ArrayList<>();

        for (int i = 0; i <k ; i++) {
            int randomIndex=1+(int)(Math.random()*points.size()-1);
            clausters.add(new Cluster("#12"+i+"78"+i,new ArrayList<>()));
            clausters.get(i).getCoordonées().add(points.get(randomIndex));
        }


       return clausters;
    }

}

