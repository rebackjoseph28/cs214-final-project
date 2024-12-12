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
            int prediction [] = new int[names.size()];
            for (int i = 0; i < epochs.size(); i++) {
                Epoch e = epochs.get(i);
                int current = e.getPerceptron().evaluateImage(image)-1;
                prediction[current]++;
            }
            predictionOut(prediction, image);
            addToList(findBestFit(prediction), image);
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
            if (prediction[i] > max){
                maxIndex = i;
                max = prediction[i];
            } 
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
