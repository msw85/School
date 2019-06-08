#Imports
import numpy as np  
import matplotlib.pyplot as plt  
import pandas as pd  

import sys
from pathlib import Path
from sklearn.model_selection import train_test_split 
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import LabelEncoder 
from sklearn.neighbors import KNeighborsClassifier 
from sklearn.metrics import classification_report, confusion_matrix   

from collections import Counter

#Add workingdir path to allow import of modules placed in the project.
absolutePath = str(Path().absolute())
pathlist = absolutePath.split('\\')
editedpath = '\\'.join(pathlist[:-1])
sys.path.insert(0, editedpath)

import backend.dBGetData as data
import backend.ticketCalc as ticketcalc


#Set split value to where the split between high and low profit is.
split = 125000

#Based on argument given,
#get data from database and load into dataframe.


def getPrediction(unitid):
#Adskild ny data og gammel data
#Fjern alle fra gammel dataset der ikke er samme vejtype som det i dataset med ny data
    dataset = pd.DataFrame(list(data.getAllData()))
    testdf = dataset.loc[dataset['unitid'] == unitid]
    testdf.reset_index(inplace = True, drop = True)
    dataset = dataset[dataset.unitid != unitid]
    dataset = dataset[dataset.roadtype == testdf.at[0, 'roadtype']]
    dataset.reset_index(inplace = True, drop = True)
    

    #Create new columns and populate with string 'None'.
    dataset['vehicle_norm'] = "None"
    dataset["class"] = "None"
    testdf['vehicle_norm'] = "None"

    #Call the ticket calculation module and aggregate all ticket amounts by road.
    aggregated = {}
    for index, row in dataset.iterrows():
        temp = ticketcalc.calcTicketAmount(float(row[5]),int(row[3]))
        if(aggregated.get("{0}".format(row[2])) is not None): 
            temp += temp + aggregated.get("{0}".format(row[2]))
        aggregated["{0}".format(row[2])] = temp

    #For every row in dataset mark as class 'høj profit' or 'lav profit'
    #based on set split.
    for index, row in dataset.iterrows():
        for key, value in aggregated.items():
            if(row["class"] == 'None'):
                if(value >= split and row["road"] == key):
                    dataset.at[index, "class"] = "høj profit"
                elif(value < split and row["road"] == key):
                    dataset.at[index, "class"] = "lav profit"

    #Using a labelencoder from sklearn, convert string labels into numbers
    #and add to column for every row.
    labelencoder = LabelEncoder()
    for index, row in dataset.iterrows():
        vehicle_encoded = labelencoder.fit_transform(dataset['vehicletype'])
        dataset.at[index, "vehicle_norm"] = vehicle_encoded[index]
    
    for index, row in testdf.iterrows():
        vehicle_encoded = labelencoder.fit_transform(testdf['vehicletype'])
        testdf.at[index, "vehicle_norm"] = vehicle_encoded[index]

    #Drop data from dataset not needed for KNN.
    dataset.drop(["_id","roadtype", "road", "timestamp", "vehicletype", 'roadspeed', 'distance', 'unitid'], axis = 1, inplace = True)
    testdf.drop(["_id","roadtype", "road", "timestamp", "vehicletype", 'roadspeed', 'distance', 'unitid'], axis = 1, inplace = True)
    
    #Set values for X and y.
    X_train = dataset.iloc[:, :-1].values 
    y_train = dataset.iloc[:, 2].values

    #Use a scaler to calculate new mean and unit variance and standardize data.
    scaler = StandardScaler()  
    scaler.fit(X_train)

    X_train = scaler.transform(X_train)  
    testdf = scaler.transform(testdf)

    #Instantiate and initialize classifier for KNN with set k-value,
    #fit the model to the data.
    classifier = KNeighborsClassifier(n_neighbors=25)  
    classifier.fit(X_train, y_train)

    #Make a prediction based on test set
    y_pred = classifier.predict(testdf)

    return Counter(y_pred.flat).most_common(1)
