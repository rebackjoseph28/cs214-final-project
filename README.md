# CS214 Final Project
This code is based on the final perceptron evaluation created in PA9 for CS214. 

## The Goal
Attempt to classify specific test images, for handwriting detection in my example

## Running the Code
The program takes three arguments: training images list, test image list, class names

It will then output the filename followed by what the code thinks is the most similar class.

What's nice is you can name your classes based on what images are given, in my test example
I've created a dataset of over 360 images of handwritten numbers, and then a test set of some more
Instead of seeing just a confidence number, I can actually see what class it thinks the image
is most like too, i.e. if I drew a two, it should be classified and labeled as a two in the final output.

Final Demonstation Video: https://youtu.be/P6qeYYjfTvc

### To Run
```
gradle run -q --args="'input_files/train.txt' 'input_files/test0.txt' 'input_files/class.txt'"
```
Output (as of 12/13 11:00am)
```
classa_1.pgm - 
         Best Guess: seven
         Confidence: 100%
classa_2.pgm - 
         Best Guess: nine
         Confidence: 100%
classa_3.pgm - 
         Best Guess: eight
         Confidence: 100%
classa_4.pgm - 
         Best Guess: nine
         Confidence: 100%
classa_5.pgm - 
         Best Guess: nine
         Confidence: 100%
classa_6.pgm - 
         Best Guess: eight
         Confidence: 100%
```
### The Images
Example Training Image:
![number](.github/class8_4.png?raw=true "class8_4.pgm")
Example Augmented Image:
![number](.github/class8_4_aug_5.png?raw=true "class8_4_aug_5.pgm")
Example Test Image:
![number](.github/classa_17.png?raw=true "classa_17.pgm")

## Issues came across along the way
- GIMP adds an extra line to pgm files
- Index out of range
- The comparison not working
- Predicting the same image

## Things I've modified
- Added a class name reader
- Added a new classifier class
- Added class labels
- Added regularization
- dataset augmentation
- Added a debug flag that adds some more outputs
- Shifted to a many vs one perceptron
- Added a confidence measure

## Dataset Augmetor
- called dataset.py
- to run you'll have to run it on your own machine I couldn't get it working on this machine
I tried using a venv, but it worked on my machine so I just copied the files over, not integral
to the code, but a nice quality of life improvement.

One thing that's also nice is that it outputs all filenames to filenames.txt in your working directory
in the format that the code takes.

to run, read the header of the python file.