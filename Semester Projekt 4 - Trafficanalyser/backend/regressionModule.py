#Imports.
import sys
import pandas as pd
import matplotlib.pyplot as plt 
import numpy as np
from pathlib import Path
from sklearn.linear_model import LinearRegression 
from sklearn.model_selection import train_test_split 
import seaborn as sns
from sklearn.metrics import mean_absolute_error
from sklearn.metrics import mean_squared_error


absolutePath = str(Path().absolute())
pathlist = absolutePath.split('\\')
editedpath = '\\'.join(pathlist[:-1])
sys.path.insert(0, editedpath)

import backend.dBGetData as data 

#Getting the data from the database and loading it into a dataframe
dataset = pd.DataFrame(list(data.getAllData()))

#Getting the regression and later showing it in a scatter plot and a histogram. 
def getRegression():
    
    #Defining X and y
    X = dataset[['distance']]
    y = dataset['speed']

    #Splitting the data into a train set and a test set. It will then put a random state on it after which will mix the data. 
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.1, random_state=101)

    #The linear regression method is called and put into a variable. 
    lm = LinearRegression()

    #Fitting X_train and y_train to the model
    lm.fit(X_train, y_train)

    #Getting the prediction based on the X_test
    predictions = lm.predict(X_test)
    
    #Showing the Mean Absolute Error in console
    #print('MAE:', mean_absolute_error(y_test, predictions))

    #Showing the mean squared error in console
    #print('MSE:', mean_squared_error(y_test, predictions))

    #Showing the Root mean squared error in console
    #print('RMSE:', np.sqrt(mean_squared_error(y_test, predictions)))


    return {"y_test": y_test, "predictions" : predictions, "lm.intercept_" : lm.intercept_}

    






