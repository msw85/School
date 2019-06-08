#Imports.
import sys
import random
import datetime
import time

from pymongo import MongoClient
from bson.objectid import ObjectId

#DB Connection.
client = MongoClient('localhost', 27017)
db = client.trafficanalyser
cardata = db.cardata
    
#Generating a random number between selectected min and max. 
def randomNumGen(min, max):
    return random.uniform(min, max)

#Simulation a live demo of the program. It will take the vehicle type in as an argument and later decide depending on which one the speed on a specific road. 
def livesimulator(vtype, road, id):

        unitid = id

        #Checking which road it is and then sets the speed random between the min and max value depending on which vehicletype. 
        if(road == 'Kongevej' or road == 'Prinsevej' or road == 'Janusvej' or road == 'Abevej' or road == 'Thomasvej'):
            if(vtype == 'van'):
                speed = randomNumGen(20.0, 70.0)
            elif(vtype == 'truck'):
                speed = randomNumGen(20.0, 65.0)
            elif(vtype == 'car'):
                speed = randomNumGen(20.0, 80.0)
            elif(vtype == 'motorbike'):
                speed = randomNumGen(20.0, 80.0)
            elif(vtype == 'bus'):
                speed = randomNumGen(20.0, 65.0)
            roadSpeed = 50
            roadtype = "villavej"

        #Checking which road it is and then sets the speed random between the min and max value depending on which vehicletype.     
        elif(road == 'Princessevej' or road == 'Elefantvej' or road == 'Egevej' or road == 'Kakaovej' or road == 'Pinsevej'):
            if(vtype == 'van'):
                speed = randomNumGen(50.0, 105.0)
            elif(vtype == 'truck'):
                speed = randomNumGen(50.0, 100.0)
            elif(vtype == 'car'):
                speed = randomNumGen(50.0, 115.0)
            elif(vtype == 'motorbike'):
                speed = randomNumGen(50.0, 115.0)
            elif(vtype == 'bus'):
                speed = randomNumGen(50.0, 100.0)
            roadSpeed = 80
            roadtype = "landevej"

        #Checking which road it is and then sets the speed random between the min and max value depending on which vehicletype. 
        elif(road == 'E20' or road == 'E45' or road == 'E39' or road == 'E47' or road == 'E55'):
            if(vtype == 'van'):
                speed = randomNumGen(85.0, 150.0)
            elif(vtype == 'truck'):
                speed = randomNumGen(70.0, 100.0)
            elif(vtype == 'car'):
                speed = randomNumGen(85.0, 200.0)
            elif(vtype == 'motorbike'):
                speed = randomNumGen(85.0, 200.0)
            elif(vtype == 'bus'):
                speed = randomNumGen(70.0, 100.0)
            roadSpeed = 110
            roadtype = "motorvej"

        #A random distance to the next car is being set.   
        dist = randomNumGen(1.0, 25.0)
        
        #A timestamp is being set. 
        timestamp = datetime.datetime.utcnow().isoformat()

        #The data is formatted so it fits.
        data = {"unitid": "{0}".format(unitid), "vehicletype": "{0}".format(vtype), "road": "{0}".format(road), "roadtype": "{0}".format(roadtype), "roadspeed": "{0}".format(roadSpeed), "speed": "{0}".format(speed), "distance": "{0}".format(dist), "timestamp": "{0}".format(timestamp)}

        #The data is being inserted into the database
        cardata.insert_one(data)