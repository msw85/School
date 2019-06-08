
#Imports
from pymongo import MongoClient

#Create and return a database connection set to the cardata collection, 
def getDBConnection():
    client = MongoClient ('localhost', 27017)
    db = client.trafficanalyser
    connection = db.cardata 

    return connection
