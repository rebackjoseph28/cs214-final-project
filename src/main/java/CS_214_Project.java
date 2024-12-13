import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CS_214_Project {
    public static boolean debug = false;
    public static void main(String[] args){
        //  /\_/\  
        // ( o.o ) {Welcome to the Final Countdown}
        //  > ^ < 
        // "The end of a melody is not its goal: but nonetheless, had the melody not reached its end it would not have reached its goal either." --Someone 
        long start = System.currentTimeMillis();
        try{
            File_Processor fp_tr = new File_Processor(args[0]);
            File_Processor fp_ts = new File_Processor(args[1]);
            
            ArrayList<ImageHistogram> training_set = fp_tr.getImageHistograms();
            ArrayList<ImageHistogram> test_set = fp_ts.getImageHistograms();

            Training_Set tr = new Training_Set(training_set);
            ClassProcessor cr = new ClassProcessor(args[2]);
            
            Classifier cf = new Classifier(test_set, tr.getEpochs(), cr.getClassList());
            System.out.print(cf.toString());

        
        } catch (IndexOutOfBoundsException e){
            System.err.println("Error: No Arguments Given");
            System.exit(0);
        }
        long end = System.currentTimeMillis();
        if (debug) System.out.println("Runtime: " + (end - start));
    }
}