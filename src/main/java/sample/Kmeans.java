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


    public ArrayList<Cluster> clauster(){
        ArrayList<Coordonée> points=new ArrayList<>(this.input.cammon());
        ArrayList<Cluster> clausters=new ArrayList<>(initialClausters(this.k,points));
        ArrayList<Coordonée> centers=new ArrayList<>();
        for (int i = 0; i <clausters.size() ; i++) {
            centers.add(clausters.get(i).getCoordonées().get(0));
        }
      double min=Double.MAX_VALUE;
      int index=0;
        for (Coordonée point : points) {
            for (int j = 0; j < centers.size(); j++) {
                if (min < point.distance(centers.get(j))) {
                    min = point.distance(centers.get(j));
                    index = j;
                }
                clausters.get(index).getCoordonées().add(point);
                centers.set(index,point.center(clausters.get(index).getCoordonées().get(clausters.get(index).getCoordonées().size()-1)));
            }
        }
         return clausters;
    }


    public ArrayList<Cluster> initialClausters(int k,ArrayList<Coordonée> points){
        ArrayList<Cluster> clausters=new ArrayList<>();
        int randomIndex=1+(int)(Math.random()*points.size());
        for (int i = 0; i <k ; i++) {
            clausters.add(new Cluster("#12"+i+"78"+i,new ArrayList<>()));
            clausters.get(i).getCoordonées().add(points.get(randomIndex));
        }


       return clausters;
    }

}

