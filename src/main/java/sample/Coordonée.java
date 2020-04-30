package sample;

import org.jetbrains.annotations.NotNull;

public class Coordonée {

    private double x;
    private double y;

    public Coordonée(double x,double y){
        this.x=x;
        this.y=y;
    }



    public double distance(@NotNull Coordonée point1, @NotNull Coordonée point2){
        return Math.sqrt(Math.pow(point2.x-point1.x,2)+Math.pow(point2.y-point1.y,2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
