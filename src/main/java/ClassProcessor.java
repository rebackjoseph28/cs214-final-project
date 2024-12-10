import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClassProcessor {
    ArrayList<String> classes;

    public ClassProcessor(String filename){
        makeClassList(Process_Scanner(filename));
    }

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

    public void makeClassList(Scanner scnr){
        classes = new ArrayList<String>();
        while (scnr.hasNext()){
            classes.add(scnr.nextLine());
        }
        //System.out.println("Number of Class Names: " + classes.size());
    }

    public ArrayList<String> getClassList(){
        return classes;
    }
}
