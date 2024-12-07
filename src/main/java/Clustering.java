import java.util.*;

/**
 * Image clustering handling
 */
public class Clustering {
    
    int k;
    int mode;
    ArrayList<ImageHistogram> images;
    ArrayList<ArrayList<ImageHistogram>> cluster;
    Training_Set tr;
    ArrayList<ArrayList<String>> final_output;

    /**
     * Clustering for images class based on image list and k
     * @param images list of images as objects
     * @param k number of clusters needed
     */
    Clustering(ArrayList<ImageHistogram> images, int k, Training_Set tr){
        if (k <= images.size() && k > 0){
            this.k = k;
            this.images = images;
            this.tr = tr;
            if (k != 1) cluster();
            else flatten();
        }
        else {
            System.err.println("Error: Invalid amount of clusters: " + k);
            //System.exit(0);
        }
    }

    /**
         * cluster
         * 1. Start image in own cluster
         * 2. K = N, clustering is done
         * 3. K < N, find most similar and cluster
         * 4. Repeat until 2 is satisfied
    */
    private void cluster(){
        cluster = new ArrayList<ArrayList<ImageHistogram>>();
        createCluster();
        while (k < cluster.size()){
            updateClusterAverage();
            moveImage(toCluster());
        }
    }

    private void flatten(){
        cluster = new ArrayList<ArrayList<ImageHistogram>>();
        ArrayList<ImageHistogram> imageList = new ArrayList<ImageHistogram>();
        for (ImageHistogram image : images){
            imageList.add(image);
        }
        cluster.add(imageList);
    }

    /**
     * takes the clustered list and prints it
     * @return printable list of clusters
     */
    @Override
    public String toString(){
        String output = "";
        sortOut();
        for(ArrayList<String> list : final_output){
            for (String item : list){
                output += item + " ";
            }
            output += "\n";
        }
        return output;
    }

    /**
     * Creates cluster from ImageHistogram object list
     */
    private void createCluster(){
        for (ImageHistogram image : images){
            ArrayList<ImageHistogram> imageList = new ArrayList<ImageHistogram>();
            imageList.add(image);
            cluster.add(imageList);
        }
    }

    /**
     * finds closest cluster from images
     * @param imageA image to compare
     * @return index of closest image
     */
    /**
     * finds closest cluster from images
     * @param imageA image to compare
     * @return index of closest image
     */
    private int[] toCluster(){
        int comp[] = {0,0}; //image 1, image 2 index
        double bestComp = 0.0;
        double currentComp = 0.0;
        for(int i = 0; i<cluster.size(); i++){
            for(int j = 0; j<cluster.size(); j++){
                if (i != j){
                    currentComp = tr.CompareImageHistograms(cluster.get(i).get(0), (cluster.get(j).get(0)));
                    if (bestComp < currentComp){
                        comp[0] = i; comp[1] = j;
                        bestComp = currentComp;
                    }
                }
            }
        }

        //System.out.println(bestComp + " " + currentComp);
        return comp;
    }

    /**
     * moves to closest cluster
     * @param closestIndex index of closest image
     * @param moveIndex image to move
     */
    private void moveImage(int[] moveIndex){
        ArrayList<ImageHistogram> toMove = cluster.get(moveIndex[1]);
        for (ImageHistogram image : toMove){
            cluster.get(moveIndex[0]).add(image);
        }
        cluster.remove(moveIndex[1]);
    }
    
    public void sortOut(){
        final_output = new ArrayList<ArrayList<String>>();
        for(ArrayList<ImageHistogram> imageList : cluster){
            ArrayList<String> line = new ArrayList<String>();
            for(ImageHistogram image : imageList){
                line.add(image.getFilename());
            }
            Collections.sort(line);
            final_output.add(line);
        }
    }

    private void updateClusterAverage(){
        for(ArrayList<ImageHistogram> imageList : cluster){
            double avg[] = imageList.get(0).calcAverage(imageList);
            for(ImageHistogram image : imageList){
                image.setAverage(avg);
            }
        }
    }
}