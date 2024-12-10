import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * ImageHistogram Class
 * Processes an image and converts the pixel values to a histogram
 */
public class ImageHistogram {
    /** histogram of image */
    public double histogram[];
    /** image in array form */
    public int image[];
    /** filename of image */
    public String filename;
    /** bincount of histogram */
    public int binCount;
    /** filescanner object */
    public Scanner scanner;
    /** file object of image */
    public File file;
    /** maximum value, width of image, height of image, size of image */
    public int maximum, width, height, size;

    public double average[];

    public Perceptron perceptron;

    private String buffer = "";
    private String bestFit;

    /**
     * Constructs the image histogram using helper classes
     * @see initImage
     * @see getFileScanner
     * @see bucketing
     * @param filename - filename of image file
     */

    public ImageHistogram(String filename) {
        this.filename = filename;
        scanner = getFileScanner(filename);
        initImage();
        readImage();
        histogram = bucketing();
        average = histogram;
    }

    public ImageHistogram(int[] image){
        this.image = image;
        histogram = bucketing();
    }

    /**
     * setHistogram
     * setter for histograms
     * @param histogram - histogram double array
     */

    public void setHistogram(double[] histogram) {
        histogram = this.histogram;
    }

    /**
     * GetFileScanner
     * gets a file scanner for scanning an image file
     * @param fileName create a file scanner from the filename
     * @return scanner if it works
     * @throws FileNotFoundException
     */

    private Scanner getFileScanner(String fileName) {
        try {
            file = new File(fileName);
            Scanner scnr = new Scanner(file);
            return scnr;
        } catch (FileNotFoundException e) {
            System.err.println("Error: File - \"" + fileName + "\" not found");
            //System.exit(0);
        }
        return null;
    }

    public String getFilename(){
        return file.getName();
    }

    /**
     * initImage
     * Initializes image histogram reading the first line to check sizes and such 
     */
    private void initImage() {
        String format = scanner.next();
        if (format.equals("P2")) {
            while (buffer.compareTo("# Created by GIMP version 2.10.32 PNM plug-in") == 0 || buffer.compareTo("") == 0){
                buffer = scanner.nextLine();
            }
            width = readInt(scanner.next());
            height = readInt(scanner.next());
            maximum = readInt(scanner.next());
            size = width * height;
        }
        else {
            System.err.println("Error: Invalid File Format");
            //System.exit(0);
        }
    }

    /** 
     * bucketing
     * converts image into bucked histogram
     * @see readInt
     * @see findIndex
     * @return bucketed histogram
     */

    private void readImage(){
        image = new int[size];
        int count = 0;
        while (scanner.hasNext()) {
            int currentNum = readInt(scanner.next());
            image[count] = currentNum;
            count++;
        }
        scanner.close();
    }

    private double[] bucketing() {
        histogram = new double[64];
        for (double value : image) {
            int index = findIndex((int)(value));
            histogram[index]++;
            binCount++;
        }
        for (int i = 0; i < 64; i++) {
            histogram[i] /= binCount;
        }
        return histogram;
    }

    /**
     * readInt
     * converts a string to an integer, checks if its a valid int
     * @param num, reads possible number and checks if valid
     * @throws NumberFormatException, throws if string is not number
     * @return an integer from the string
     */
    private int readInt(String num) {
        int i = 0;
        try {
            i = Integer.parseInt(num);
            i = checkInput(i);
        } catch (NumberFormatException numerr) {
            System.err.println("Error: Invalid Character, non integer");
            //System.exit(0);
        }
        return i;
    }

    /**
     * getHistogram
     * getter for the histogram
     * @return histogram as double array
     */
    public double[] getHistogram() {
        return histogram;
    }

    /**
     * getImage
     * getter for the image
     * @return image as double array
     */
    public int[] getImage() {
        return image;
    }

    /**
     * findIndex
     * finds index from number
     * @param num value for bucketing
     * @return num/4, what bucket the num adds into
     */
    private int findIndex(int num) {
        return num / 4;
    }

    /**
     * checkInput
     * checks to see if character inputted is in valid range
     * @param num
     * @throws Error if invalid character
     * @return num if is a valid integer
     */
    private int checkInput(int num) {
        if (num < 0 || num > 255) {
            System.err.println("Error: Invalid Character, out of bounds");
            //System.exit(0);
            return 0;
        } else
            return num;
    }


    public double[] getAverage(){
        return average;
    }

    public void setAverage(double average[]){
        this.average = average;
    }

    public double[] calcAverage(ArrayList<ImageHistogram> list){
        double newAverage[] = new double[histogram.length];
        for(int i = 0; i < newAverage.length; ++i){
            for(ImageHistogram image : list){
                newAverage[i] += image.getHistogram()[i];
            }
            if (list.size() != 0) newAverage[i] /= list.size();
        }
        return newAverage;
    }
    
    public int getClassType(){
        int indexOf_ = filename.lastIndexOf('s')+1;
        int type = Integer.parseInt(filename.substring(indexOf_,indexOf_+1));
        return type;
    }

    public void setBestFit(String bf){
        bestFit = bf;
    }

    public String getBestFit(){
        return bestFit;
    }
}
