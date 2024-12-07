import java.util.ArrayList;

public class Epoch{
    int N;
    ArrayList<ImageHistogram> images;
    Perceptron p;
    
    public Epoch(ArrayList<ImageHistogram> images, int N){
        this.images = images;
        this.N = N;
        p = new Perceptron((images.get(0)),N);
    }

    public void runEpoch(){
        for (int i = 0; i < images.size(); i++){
            p.updateImage(images.get(i));
            p.evaluate();
        }
    }
    
    public void runNEpoch(int n){
        for(int i = 0; i<n; i++) runEpoch();
    }

    public String toString(){
        String out = "";
        double[] weights = p.getWeight();
        for (double item : weights){
            out += String.format("%.6f", item) + " ";
        }
        return out;
    }

    public Perceptron getPerceptron(){
        return p;
    }

    public Perceptron trainImage(ImageHistogram i){
        Perceptron p_out = p;
        p_out.updateImage(i);
        p_out.evaluate();
        return p_out;
    }
}
