# CS214 Final Project
This code is based on the final perceptron evaluation created in PA9 for CS214. 

## The Goal
Attempt to classify specific test images, for handwriting detection in this example

## Running the Code
The program takes three arguments: training images list, test image list, class names

It will then output the filename followed by what the code thinks is the most similar class.

What's nice is you can name your classes based on what images are given, in my test example
I've created a dataset of over 40 images of handwritten numbers, and then a test set of some more
Instead of seeing just a confidence number, I can actually see what class it thinks the image
is most like, i.e. if I drew a two, it should be classified and labeled as a two in the final output.

### To Run
```
gradle run -q --args="'input_files/train.txt' 'input_files/test.txt' 'input_files/class.txt'"
```
Output (as of 12/11 8:45pm)
```
Number of Classes: 10
classa_1.pgm: seven
classa_10.pgm: nine
classa_11.pgm: seven
classa_12.pgm: eight
classa_13.pgm: eight
classa_14.pgm: eight
classa_15.pgm: eight
classa_16.pgm: eight
classa_17.pgm: eight
classa_18.pgm: eight
classa_19.pgm: seven
classa_2.pgm: nine
classa_20.pgm: eight
classa_3.pgm: eight
classa_4.pgm: nine
classa_5.pgm: nine
classa_6.pgm: eight
classa_7.pgm: nine
classa_8.pgm: eight
classa_9.pgm: nine
```
### The Images
Example Training Image:
![number](input_files/train/class0_1.pgm?raw=true "class0_1.pgm")
Example Augmented Image:
![number](input_files/train/class0_1_aug_1.pgm?raw=true "class0_1_aug_1.pgm")
Example Test Image:
![number](input_files/test/classa_1.pgm?raw=true "classa_1.pgm")

## Issues came across along the way
- Gimp adds an extra line to pgm files
- Index out of range
- The comparison not working

## Things I've modified
- Added a class name reader
- Added a new classifier class
- Added class labels
- Added regularization
- dataset augmentation
- Shifted to a many vs one perceptron

## Dataset Augmetor
- called dataset.py
- to run you'll have to run it on your own machine I couldn't get it working on this machine
I tried using a venv, but it worked on my machine so I just copied the files over, not integral
to the code, but a nice quality of life improvement.

One thing that's also nice is that it outputs all filenames to filenames.txt in your working directory
in the format that the code takes.

to run, read the header of the python file.