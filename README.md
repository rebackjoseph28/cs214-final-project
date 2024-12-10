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

TO RUN:
```
gradle run -q --args="'input_files/train.txt' 'input_files/test.txt' 'input_files/class.txt'"
```

## Issues came across along the way
- Gimp adds an extra line to pgm files
- Index out of range
- The comparison not working

## Things I've modified
- Added a class name reader
- Added a new classifier class
- Added class labels
- Added regularization

## Dataset Augmetor
- called dataset.py
- to run you'll have to run it on your own machine I couldn't get it working on this machine
I tried using a venv, but it worked on my machine so I just copied the files over