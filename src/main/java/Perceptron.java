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

    public void updateWeights(){
        for(int index = 0; index < 64; index++){
            weights[index] += (d-y)*(image.getHistogram()[index]);
        }
    }

    public void updatePerceptron(){
        y = weights[64];
        for (int i = 0; i < 64; i++){
            y += weights[i]*(image.getHistogram()[i]);
        }
    }

    public void updateBias(){
        weights[64] += (d-y);
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
        int type = Integer.parseInt(filename.substring(indexOf_,indexOf_+1));
        return type;
    }

    public void evaluate(){
        updatePerceptron();
        updateWeights();
        updateBias();
    }

    public double evaluateImage(ImageHistogram im){
        double y_out = 0.0;
        for (int i = 0; i < 64; i++){
            y_out += weights[i]*(im.getAverage()[i]);
        }
        y_out += findLabel(im.getFilename())-y;
        return y_out;
    }
}
