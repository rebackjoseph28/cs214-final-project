import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CS_214_Project {
    public static void main(String[] args){
        //  /\_/\  
        // ( o.o ) {Welcome to the Final Countdown}
        //  > ^ < 
        // "The end of a melody is not its goal: but nonetheless, had the melody not reached its end it would not have reached its goal either." --Someone 
        
        /**
         * - Training set file name
         * - Test set file name
         * - K - Number of clusters
         * 1. Train Perceptrons for every class label, 100 epochs, 1 EPOCH FOR EACH CLASS
         * 2. Cluster the second set of images
         * 3. Compare
         */
        try{
            File_Processor fp_tr = new File_Processor(args[0]);
            File_Processor fp_ts = new File_Processor(args[1]);
            int k = Integer.parseInt(args[2]);
            
            ArrayList<ImageHistogram> training_set = fp_tr.getImageHistograms();
            ArrayList<ImageHistogram> test_set = fp_ts.getImageHistograms();

            Training_Set tr = new Training_Set(training_set);
            
            Clustering cluster = new Clustering(test_set, k, tr);
            System.out.print(cluster.toString());
        } catch (IndexOutOfBoundsException e){
            System.err.println("Error: No Arguments Given");
            System.exit(0);
        }
    }
}