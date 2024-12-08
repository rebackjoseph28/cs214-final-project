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

    public void classify(){
        int bestFitIndex = -1;
        double current = 0;
        double bestFit = Double.NEGATIVE_INFINITY;
        for (ImageHistogram image : images){
            for(Epoch e : epochs){
                current = e.getPerceptron().evaluateImage(image);
                if (current >= bestFit){
                    bestFit = current;
                    bestFitIndex = epochs.indexOf(e);
                }
            }
            //System.out.println("Best Fit: " + bestFit + ", " + bestFitIndex);
            addToList(epochs.get(bestFitIndex), image, current);
            bestFitIndex = -1;
            bestFit = Double.NEGATIVE_INFINITY;
        }
    }

    private void addToList(Epoch e, ImageHistogram image, double current){
        int class_num = e.N;
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
