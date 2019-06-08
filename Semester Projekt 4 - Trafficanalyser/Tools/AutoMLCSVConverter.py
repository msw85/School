#Imports
import os
from decimal import Decimal, getcontext

#Set amount of numbmers after the decimal seperator
getcontext().prec = 6

#Instantiation and initialization of collections needed in the code
tempfilelist = os.listdir(os.getcwd())
filelist = []
labeldict = {'0': 'van', '1': 'truck', '2': 'car', '3': 'motorbike', '4': 'bus'}
filedict = {}

#Create csv file (if not existing) and open it in append mode
with open('training_csv.csv', 'a+') as csv:

    #Iterate through filenames in tempfilelist.
    #If file is a txt file append it to filelist,
    #else save to filedict
    for f in tempfilelist:
        name, ext = f.split('.')
        if(ext == 'txt'):
            filelist.append(f)
        else:
            filedict[name] = ext

    #Iterate filelist and open each entry as a file.
    for entry in filelist:
        with open(entry) as file:

            #For each line in file, get the box info 
            for line in file:
                label, x_cent, y_cent, width, height = line.split(' ')
                
                #Initialize variables with typecasted Darknet values
                # #for better readability in comming calculations
                x_cent = Decimal(x_cent)
                y_cent = Decimal(y_cent)
                height = Decimal(height)
                width = Decimal(width)

                print(x_cent, y_cent, width, height)

                #Calculate x and y values for Google AutoML
                x_max = x_cent + width/2
                y_max = y_cent + height/2
                x_min = x_cent - width/2
                y_min = y_cent - height/2

                labelstring = labeldict.get(label)
                
                print(x_max, y_max, x_min, y_min)

                print('----------')

                #Join filename with original fileextension
                filename, ext = entry.split('.')
                filename = filename + '.{0}'.format(filedict.get(filename))

                #Format string to write to CSV, based on Google specs,
                #write string to file
                tempstring = 'UNASSIGNED,gs://traffic_test/{1},{0},{2},{3},{4},{5},{6},{7},{8},{9}\n'.format(labelstring, filename, x_min, y_min, x_max, y_min, x_max, y_max, x_min, y_max)

                csv.write(tempstring)
