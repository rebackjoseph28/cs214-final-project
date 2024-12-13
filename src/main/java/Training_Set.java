import java.util.ArrayList;
import java.util.HashSet;

public class Training_Set {
    ArrayList<Epoch> training_set = new ArrayList<Epoch>();
    ArrayList<ImageHistogram> images = new ArrayList<ImageHistogram>();
    int num_classes = 0;

    public Training_Set(ArrayList<ImageHistogram> images){
        this.images = images;
        findNumClasses();
        TrainEpochs();
    }

    private void TrainEpochs(){
        for(int i = 0; i < num_classes; i++){
            Epoch e = new Epoch(images,i,num_classes);
            e.runNEpoch(150);
            training_set.add(e);
        }
    }

    private void findNumClasses(){
        HashSet<Integer> classTypes = new HashSet<>();
        for(ImageHistogram image : images){
            classTypes.add(image.getClassType());
        }
        num_classes = classTypes.size();
        if (CS_214_Project.debug) System.out.println("Number of Classes: " + num_classes);
        if (num_classes < 2) System.err.println("ERROR TOO FEW CLASSES");
    }

    public double CompareImageHistograms(ImageHistogram i1, ImageHistogram i2){
        double total = 0.0;
        double yi,yj,denominator;
        for(Epoch e : training_set){
            yi = e.getPerceptron().evaluateImage(i1);
            yj = e.getPerceptron().evaluateImage(i2);
            denominator = (Math.pow(yi - yj,2));
            if (denominator != 0) total += 1/denominator;
        }
        return total;
    }

    public ArrayList<Epoch> getEpochs(){
        return training_set;
    }
}
