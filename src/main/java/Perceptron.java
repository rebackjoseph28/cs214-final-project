public class Perceptron{
    ImageHistogram image;
    int N,d;
    double y = 0;
    double[] weights = new double[65];    

    public Perceptron(ImageHistogram image, int N){
        this.image = image;
        this.N = N;
    }

    public void updateImage(ImageHistogram image){
        this.image = image;
        updateLabel(N, image);
    }

    public void updateWeights(double learningRate, double lambda){
        for(int index = 0; index < 64; index++){
            //regularized to penalize large values
            weights[index] +=  learningRate * ((d-y) * image.getHistogram()[index] - lambda * weights[index]);
        }
    }

    public void updatePerceptron(){
        y = weights[64];
        for (int i = 0; i < 64; i++){
            y += weights[i]*(image.getHistogram()[i]);
        }
    }

    public void updateBias(double learningRate){
        weights[64] += learningRate*(d-y);
    }

    public double[] getWeight(){
        return weights;
    }

    private void updateLabel(int N, ImageHistogram image){
        if (findLabel(image.getFilename()) == N) d = 1;
        else d = -1;
    }

    private int findLabel(String filename){
        int indexOf_ = filename.lastIndexOf('s')+1;
        int type;
        try { type = Integer.parseInt(filename.substring(indexOf_,indexOf_+1));
        } catch (NumberFormatException e){ type = -1; }
        return type;
    }

    public void evaluate(double learningRate, double lambda){
        updatePerceptron();
        updateWeights(learningRate,lambda);
        updateBias(learningRate);
    }

    public double evaluateImage(ImageHistogram im){
        double y_out = 0.0;
        for (int i = 0; i < 64; i++){
            y_out += weights[i]*(im.getHistogram()[i]);
        }
        y_out += findLabel(im.getFilename()) == N ? 1-y : -1-y;
        System.out.println("EVAL: " + N + ":" + im.getFilename() + ", " + y_out);
        return y_out;
    }
}
