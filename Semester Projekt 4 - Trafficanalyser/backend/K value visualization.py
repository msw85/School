#Imports
import sys
from pathlib import Path

import numpy as np  
import matplotlib.pyplot as plt  
import pandas as pd  

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

#Based on argument given to script,
#set split value to where the split between high and low profit is.
split = int(sys.argv[2])

#Based on argument given to script,
#get data from database and load into dataframe.
vejtype = str(sys.argv[1])
dataset = pd.DataFrame(list(data.getDataByRoadType(vejtype)))

#Create new columns and populate with string 'None'.
dataset['vehicle_norm'] = 'None'
dataset['class'] = 'None'

print(dataset.info())

#Call the ticket calculation module and aggregate all ticket amounts by road.
aggregated = {}
for index, row in dataset.iterrows():
    temp = ticketcalc.calcTicketAmount(float(row[5]),int(row[3]))
    if(aggregated.get('{0}'.format(row[2])) is not None): 
        temp += temp + aggregated.get('{0}'.format(row[2]))
    aggregated['{0}'.format(row[2])] = temp

#For every row in dataset mark as class 'høj profit' or 'lav profit'
#based on set split.
for index, row in dataset.iterrows():
    for key, value in aggregated.items():
        if(row['class'] == 'None'):
            if(value >= split and row['road'] == key):
                dataset.set_value(index, 'class', 'høj profit')
            elif(value < split and row['road'] == key):
                dataset.set_value(index, 'class', 'lav profit')

#Using a labelencoder from sklearn, convert string labels into numbers
#and add to column for every row.
labelencoder = LabelEncoder()
for index, row in dataset.iterrows():
    vehicle_encoded = labelencoder.fit_transform(dataset['vehicletype'])
    dataset.set_value(index, 'vehicle_norm', vehicle_encoded[index])

#Drop data from dataset not needed for KNN.
dataset.drop(['unitid', '_id','roadtype', 'road', 'timestamp', 'vehicletype', 'roadspeed', 'distance'], axis = 1, inplace = True)

#Set values for X and y.
X = dataset.iloc[:, :-1].values 
y = dataset.iloc[:, 2].values

#Using sklearn split dataset into training and testing sets.
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.20)

#Use a scaler to calculate new mean and unit variance and standardize data.
scaler = StandardScaler()  
scaler.fit(X_train)

X_train = scaler.transform(X_train)  
X_test = scaler.transform(X_test)

#Instantiate and initialize classifier for KNN with set k-value,
#fit the model to the data.
classifier = KNeighborsClassifier(n_neighbors=25)  
classifier.fit(X_train, y_train)

#Make a prediction based on test set
y_pred = classifier.predict(X_test)

#Print prediction
print('------------------')
print(y_pred)
print('------------------')

#Print the most common entry in the predictions
print(Counter(y_pred.flat).most_common(1))
print('------------------')

#Print confusion matrix and classification report
print(confusion_matrix(y_test, y_pred))
print('------------------')
print(classification_report(y_test, y_pred))
print('------------------')

error = []

# Calculating error for K values between 1 and 40
for i in range(1, 40):  
    knn = KNeighborsClassifier(n_neighbors=i)
    knn.fit(X_train, y_train)
    pred_i = knn.predict(X_test)
    error.append(np.mean(pred_i != y_test))

#Plot the test and show it
plt.figure(figsize=(12, 6))  
plt.plot(range(1, 40), error, color='red', linestyle='dashed', marker='o',  
         markerfacecolor='blue', markersize=10)
plt.title('Error Rate K Value')  
plt.xlabel('K Value')  
plt.ylabel('Mean Error')
plt.show()