'''
Created by Joey Reback
Goal: manipulate the dataset
for more different images for
better training and a more accurate set
inspired by 
https://pyimagesearch.com/2019/07/08/keras-imagedatagenerator-and-data-augmentation/
https://www.geeksforgeeks.org/python-data-augmentation/#

Running:
python3 dataset.py <class_name> <file_path> <output_dir>
'''

import sys
import os
import numpy as np
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.utils import img_to_array, load_img

# save pgm in ascii format
def save_as_pgm_ascii(image_array, output_path):
    height, width = image_array.shape
    max_val = 255
    with open(output_path, 'w') as f:
        f.write("P2\n")  # PGM format arg
        f.write(f"{width} {height}\n")  # image size
        f.write(f"{max_val}\n")  # gray minimum
        for row in image_array:
            f.write(" ".join(map(str, row.astype(int))) + "\n")

# command line arguments
class_name = sys.argv[1]
file_path = sys.argv[2]
output_dir = sys.argv[3]

# make the output dir if it doesn't exist
os.makedirs(output_dir, exist_ok=True)

# textfile for saving filenames
output_txt_path = os.path.join(output_dir, "filenames.txt")

# Open the file in append mode
with open(output_txt_path, 'a') as txt_file:

    # Data aug configs
    datagen = ImageDataGenerator(
        rotation_range=40,
        shear_range=0.2,
        zoom_range=0.2,
        horizontal_flip=True,
        brightness_range=(0.5, 1.5)
    )

    # make images for said class
    index = 1
    while index <= 6:
        filename = os.path.join(file_path, f"{class_name}_{index}.pgm")
        img = load_img(filename, color_mode="grayscale")  # Load as grayscale
        x = img_to_array(img).squeeze()  # make image into array
        x = x.reshape((1,) + x.shape + (1,))  # reshape image

        # make the augmented image
        i = 0
        for batch in datagen.flow(x, batch_size=1):
            augmented_image = batch[0].squeeze()  # get the image made
            output_filename = os.path.join(output_dir, f"{class_name}_{index}_aug_{i + 1}.pgm")
            
            save_as_pgm_ascii(augmented_image, output_filename)  # save as ascii pgm file

            txt_file.write(output_filename + "\n")  # append the filename to the text file

            i += 1
            if i >= 5:  # make 5 images per input
                break

        index += 1
