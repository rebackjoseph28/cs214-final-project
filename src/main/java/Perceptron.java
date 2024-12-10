public class Perceptron{
    ImageHistogram image;
    int N,d;
    double y = 0;
    double[] weights = new double[65]; 
    double learningRate;
    double lambda;  
    int num_classes; 

    public Perceptron(ImageHistogram image, int N, int num_classes){
        this.image = image;
        this.N = N;
        this.num_classes = num_classes;
    }

    public void updateImage(ImageHistogram image){
        this.image = image;
        updateLabel(N, image);
    }

    public void updateWeights(){
        for(int index = 0; index < 64; index++){
            //regularized to penalize large values
            weights[index] +=  learningRate * ((d-y) * image.getHistogram()[index] - lambda * weights[index]);
        }
        weights[64] += learningRate * (d - y);
    }

    public void updatePerceptron(){
        y = weights[64];
        for (int i = 0; i < 64; i++){
            y += weights[i]*(image.getHistogram()[i]);
        }
    }

    public double[] getWeight(){
        return weights;
    }

    private void updateLabel(int N, ImageHistogram image){
        d = (findLabel(image.getFilename()));
    }

    private int findLabel(String filename){
        int indexOf_ = filename.lastIndexOf('s')+1;
        int type;
        try { type = Integer.parseInt(filename.substring(indexOf_,indexOf_+1));
        } catch (NumberFormatException e){ type = -1; }
        return type;
    }

    public void evaluate(double learningRate, double lambda){
        this.learningRate = learningRate;
        this.lambda = lambda;
        updatePerceptron();
        updateWeights();
    }

    public double evaluateImage(ImageHistogram im){
        double outputs[] = new double[num_classes];
        for (int i = 0; i < num_classes; i++) {
            outputs[i] = weights[(i * 64 + 64)]; // Bias term for this class
            for (int j = 0; j < 64; j++) {
                outputs[i] += weights[(i * 64 + i)] * im.getHistogram()[i];
            }
        }
        return predictClass(outputs);
    }

    private double predictClass(double outputs[]){
        int predictedClass = -1;
        double maxOutput = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < num_classes; i++) {
            if (outputs[i] > maxOutput) {
                maxOutput = outputs[i];
                predictedClass = i + 1;
            }
        }
        return predictedClass;
    }
}
