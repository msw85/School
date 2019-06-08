#Imports
import cv2
import numpy as np
import os

import objectdetectionModule as odm
import liveDataSimulator as lds

workingdir = os.getcwd()

def makePrediction(filename):
    predictionsjson = odm.getDetectedObjects(filename)
    print(predictionsjson)
    for p in predictionsjson.payload:
        lds.livesimulator(p.display_name,'E45', 1)

cap = cv2.VideoCapture(1)

frameCount = 0

while True:
    status, frame = cap.read()
   
    cv2.imshow("Live View", frame)
    
    if(frameCount % 100 == 0):
        cv2.imwrite('savedframe{0}.jpg'.format(frameCount), frame)
        makePrediction('{0}\savedframe{1}.jpg'.format(workingdir, frameCount))

    frameCount = frameCount + 1

    key = cv2.waitKey(1)
    if key == 27:
        break

cap.release()
cv2.destroyAllWindows()