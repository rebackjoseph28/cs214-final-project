import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import java.io.*;
import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CS_214_Project_Tester {
    // "A test is just a test, and thankfully there is a final one"
    String filename[] = new String[3];

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(outContent);
    PrintStream originalOutput = System.out;

    @BeforeEach
    public void setup() {
        System.out.println("Starting setup for test: " + this.getClass().getSimpleName());
        outContent.reset();  // Reset before each test
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(outContent));
    }

    @AfterAll
    public void tearDown() {
        System.out.println("Tearing down tests: " + this.getClass().getSimpleName());
        System.setOut(originalOutput);
    }

    @Test
    public void test_1_epoch(){
        String filename = "input_files/test.txt";
        File_Processor fp = new File_Processor(filename);
        int N = 1;
        Epoch system = new Epoch(fp.getImageHistograms(),N);
        system.runNEpoch(1);
        //assertEquals("0.000000 0.000000 -0.003036 -0.002811 -0.001237 -0.006409 -0.010794 -0.011173 -0.015042 -0.007380 -0.008767 -0.001017 -0.003573 -0.002523 -0.003595 -0.002759 -0.000865 -0.002376 -0.003907 -0.024029 -0.012140 -0.009425 -0.001343 0.012536 0.022109 0.031438 0.041817 0.026599 0.021055 0.012668 0.005685 0.006936 0.000528 0.000150 -0.001088 0.002593 0.004873 0.001583 0.001281 -0.003268 -0.002738 0.000937 -0.005165 -0.009541 -0.005767 -0.008418 -0.006354 -0.009256 -0.000953 -0.011590 -0.007000 -0.010662 -0.008188 -0.010651 -0.007693 -0.008961 -0.006099 -0.006542 -0.006152 -0.006669 -0.010416 -0.006408 -0.006327 -0.553977 -0.661296 ",system);
    }

    @Test
    public void test_100_epochs(){
        String filename = "input_files/test.txt";
        File_Processor fp = new File_Processor(filename);
        int N = 1;
        Epoch system = new Epoch(fp.getImageHistograms(),N);
        system.runNEpoch(1);
        //assertEquals("0.000000 0.000000 -0.162961 -0.150890 -0.066392 -0.344029 -0.579417 -0.595082 -0.802414 -0.539632 -0.547547 -0.162618 -0.238202 -0.231405 -0.266466 -0.205797 -0.158790 -0.263238 -0.308527 -1.402909 -1.145041 -0.539526 -0.151028 1.552156 2.500679 4.055540 5.143948 3.412265 2.204883 1.272654 0.688577 0.841457 0.468032 0.534119 0.283319 0.576049 0.668979 0.226852 0.250680 -0.010704 -0.108878 0.175210 -0.199557 -0.494828 -0.292237 -0.442466 -0.332151 -0.514940 -0.142412 -0.654257 -0.414058 -0.574871 -0.452563 -0.608509 -0.422489 -0.491849 -0.343828 -0.343319 -0.328115 -0.356954 -0.568112 -0.243945 -0.344032 -4.903972 2.404443 ",system);
    }

    @Test
    public void test_1_image(){
        ImageHistogram image = new ImageHistogram("input_files/test/class0_1.pgm");
        ArrayList<ImageHistogram> images = new ArrayList<ImageHistogram>();
        images.add(image);
        Epoch system = new Epoch(images,1);
        system.runNEpoch(1);
        //assertEquals("0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000122 0.004456 0.023804 0.031006 0.052917 0.063904 0.041260 0.023926 0.013489 0.010376 0.010620 0.008301 0.010437 0.006958 0.008667 0.008911 0.003845 0.004333 0.003662 0.002625 0.002563 0.001709 0.002136 0.001892 0.001099 0.001099 0.001770 0.000488 0.000183 0.000610 0.000671 0.000305 0.000732 0.000793 0.000427 0.000244 0.000488 0.000732 0.000305 0.000427 0.002197 0.000610 0.644897 1.000000 ",system);
    }

    @Test
    public void test_2_images(){
        ImageHistogram image1 = new ImageHistogram("input_files/train/class1_10.pgm");
        ImageHistogram image2 = new ImageHistogram("input_files/train/class2_138.pgm");
        ArrayList<ImageHistogram> images = new ArrayList<ImageHistogram>();
        images.add(image1);
        images.add(image2);
        Epoch system = new Epoch(images,1);
        system.runNEpoch(1);
        //assertEquals("0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000122 0.004456 0.015264 -0.000562 0.010217 0.021051 0.009692 -0.007489 -0.025551 -0.013567 -0.016830 -0.029519 -0.005881 -0.016985 -0.007041 -0.012439 -0.018877 -0.015644 -0.021806 -0.017658 -0.027327 -0.017354 -0.009606 -0.004970 -0.003324 -0.004239 -0.002500 -0.002104 -0.002867 0.000153 -0.000854 -0.001067 0.000580 -0.001647 -0.000945 -0.001281 -0.000732 0.000275 -0.000305 -0.000335 0.001130 -0.000152 -1.270056 -1.498574 ",system);
    }

    @Test
    public void test_3_images(){
        ImageHistogram image1 = new ImageHistogram("input_files/train/class1_8.pgm");
        ImageHistogram image2 = new ImageHistogram("input_files/train/class2_144.pgm");
        ImageHistogram image3 = new ImageHistogram("input_files/train/class3_256.pgm");
        ArrayList<ImageHistogram> images = new ArrayList<ImageHistogram>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        Epoch system = new Epoch(images,1);
        system.runNEpoch(1);
        //assertEquals("0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000000 0.000122 0.004456 0.015264 -0.000562 0.010217 0.021051 0.009692 -0.007489 -0.025551 -0.013567 -0.016830 -0.029519 -0.005881 -0.016985 -0.007041 -0.012439 -0.018877 -0.015644 -0.021806 -0.017658 -0.027327 -0.017354 -0.009606 -0.004970 -0.003324 -0.004239 -0.002500 -0.002104 -0.002867 0.000153 -0.000854 -0.001067 0.000580 -0.001647 -0.000945 -0.001281 -0.000732 0.000275 -0.000305 -0.000335 0.001130 -0.000152 -1.270056 -1.498574 ",system);
    }

    @Test
    public void test_file_invalid_name() {
        assertThrows(NullPointerException.class, () -> {
            ImageHistogram image_ghost = new ImageHistogram("input_files/ghost_image.pgm");
        });
    }

    @Test
    public void test_all(){
        String args[] = new String[3];
        args[0] = "input_files/train.txt";
        args[1] = "input_files/test.txt";
        args[2] = "3";
        CS_214_Project.main(args);
    }
}