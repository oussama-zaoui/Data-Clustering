package sample;

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
}

