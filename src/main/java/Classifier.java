import java.util.*;
public class Classifier {
    ArrayList<ImageHistogram> images;
    ArrayList<Epoch> epochs;
    ArrayList<String> names;

    public Classifier(ArrayList<ImageHistogram> images, ArrayList<Epoch> epochs, ArrayList<String> names){
        this.images = images;
        this.epochs = epochs;
        this.names = names;
        classify();
    }

    public void classify() {
        for (ImageHistogram image : images) {
            int bestFitIndex = -1;
            double bestFit = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < epochs.size(); i++) {
                Epoch e = epochs.get(i);
                double current = e.getPerceptron().evaluateImage(image);
                if (current > bestFit) {
                    bestFit = current;
                    bestFitIndex = i;
                }
            }
            if (bestFitIndex != -1) {
                addToList(epochs.get(bestFitIndex), image, bestFit);
            }
        }
    }
    

    private void addToList(Epoch e, ImageHistogram image, double current){
        int class_num = e.N-1;
        //System.out.println("Class: " + class_num);
        String out = names.get(class_num);
        image.setBestFit(out);
    }

    @Override
    public String toString(){
        String out = "";
        for (ImageHistogram image : images){
            out += image.getFilename() + ": " + image.getBestFit();
            out += "\n";
        }
        return out;
    }
}
