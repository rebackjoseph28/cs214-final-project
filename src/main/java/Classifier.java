import java.util.*;
import java.util.stream.Stream;
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
            int prediction [] = new int[names.size()];
            for (int i = 0; i < epochs.size(); i++) {
                Epoch e = epochs.get(i);
                int current = (int)(e.getPerceptron().evaluateImage(image));
                prediction[current]++;
            }
            predictionOut(prediction, image);
            bestFitIndex = findBestFit(prediction); 
            addToList(bestFitIndex-1, image);
        }
    }

    private void predictionOut(int[] prediction, ImageHistogram image){
        if (CS_214_Project.debug) {
            System.out.println("Class Predictions: " + image.getFilename());
            for(int q = 0; q < prediction.length; q++){
                System.out.println("\t Class " + q + ":" + prediction[q]);
            }
        }
    }
    
    private int findBestFit(int[] prediction){
        int max = -1;
        int maxIndex = -1;
        for (int i = 0; i < prediction.length; i++){
            if (prediction[i] > max) maxIndex = i;
        }
        return maxIndex;
    }

    private void addToList(int bestFitIndex, ImageHistogram image){
        String out = names.get(bestFitIndex);
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
