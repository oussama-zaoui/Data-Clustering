package model;

import java.util.ArrayList;

public class Cluster {

    private ArrayList<Coordonée> coordonées;
    private String code_color;
    private double interInertie;
    private double intraInertie;


    public Cluster(String code_color,ArrayList<Coordonée> coordonées){
        this.code_color=code_color;
        this.coordonées=coordonées;
    }


    public ArrayList<Coordonée> getCoordonées() {
        return coordonées;
    }

    public String getCode_color() {
        return code_color;
    }

    public void setCode_color(String code_color) {
        this.code_color = code_color;
    }

    public void setCoordonées(ArrayList<Coordonée> coordonées) {
        this.coordonées = coordonées;
    }

    public double getInterInertie() {
        return interInertie;
    }

    public double getIntraInertie() {
        return intraInertie;
    }

    public void setInterInertie(double interInertie) {
        this.interInertie = interInertie;
    }

    public void setIntraInertie(double intraInertie) {
        this.intraInertie = intraInertie;
    }
}
