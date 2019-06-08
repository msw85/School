#Imports
import os
from pathlib import Path

#Get the working directory path
absolutePath = str(Path().absolute())

#For every file in the path write filenames to train.txt
for file in os.listdir(absolutePath):
    if file.endswith(".jpg") or file.endswith(".png"):
        print(os.path.join(absolutePath, file))
        f = open("train.txt", "a")
        f.write(os.path.join(absolutePath, file))
        f.close()