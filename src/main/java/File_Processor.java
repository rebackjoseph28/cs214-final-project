import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
/**
 * Handler for creating and processing image files
 */
public class File_Processor {
    String filename;
    ArrayList<String> filenames;

    /**
     * File_Processor
     * creates class, makes list of file from filename
     * @param filename of list of images
     */
    public File_Processor(String filename){
        this.filename = filename;
        makeFileList(Process_Scanner(filename));
    }

    /**
     * Process_Scanner
     * creates a scanner object from filename
     * @param filename of list of images
     * @throws FileNotFoundException if file not found
     * @return scanner for files
     */
    private Scanner Process_Scanner(String filename){
        try {
            File file = new File(filename);
            Scanner scnr = new Scanner(file);
            return scnr;
        } catch (FileNotFoundException e) {
            System.err.println("Error: File - \"" + filename + "\" not found");
        }
        return null;
    }

    /**
     * makeFileList
     * makes the list of files based on the scanner object
     * @param scnr scanner of file list
     * @throws Error if not enough arguments given 
     */
    private void makeFileList(Scanner scnr){
        filenames = new ArrayList<String>();
        while (scnr.hasNext()){
            filenames.add(scnr.next());
        }
        Collections.sort(filenames);
        if (filenames.size()<=1){
            System.err.println("Error: Not Enough Arguments Given");
            System.exit(0);
        }
        scnr.close();
    }

    /**
     * getFileNames
     * gets a list of filenames as strings
     * @return filenames of images
     */
    public ArrayList<String> getFilenames(){
        return filenames;
    }

    /**
     * getImageComparison
     * gets the list of ImageComparison
     * @return ArrayList of image histograms created
     */
    public ArrayList<ImageHistogram> getImageHistograms(){
        ArrayList<ImageHistogram> images = new ArrayList<ImageHistogram>();
        for(String filename : filenames) images.add(new ImageHistogram(filename));
        return images;
    }

}
