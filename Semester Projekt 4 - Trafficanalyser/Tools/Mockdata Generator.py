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


#Selecting a random type of vehicle from the given list of vehicletypes. 
def randomCarType():
    types = ['van', 'truck', 'car', 'motorbike', 'bus']
    return random.choice(types)

#Selecting a random road from the given list of roads.
def randomRoad():
    roads = ['Kongevej', 'Prinsevej', 'Janusvej', 'Abevej', '´Thomasvej',
        'Princessevej', 'Elefantvej', 'Egevej', 'Kakaovej', 'Pinsevej',
        'E20', 'E45', 'E39', 'E47', 'E55'
        ]
    return random.choice(roads)

#Generating data with a specific amount. 
def generateData(quantity):
    print('Generating {0} database entries...'.format(quantity))
    print(' ')

    #Generating an id for a specific unit. 
    for i in range(quantity):

        print('Generating entry #{0}'.format(i))
        
        #Makes it sleep for a random time. This will make the timestamp to not be the same.
        time.sleep(randomNumGen(0.05, 1.5))
        
        #Picking a random vehicletype.
        vtype = randomCarType()

        #Picking a random roadname within the roadtype and sets the speed for the vehicle after.
        road = randomRoad()
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

        #Picking a random roadname within the roadtype and sets the speed for the vehicle after. 
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

        #Picking a random roadname within the roadtype and sets the speed for the vehicle after. 
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
        data = {"vehicletype": "{0}".format(vtype), "road": "{0}".format(road), "roadtype": "{0}".format(roadtype), "roadspeed": "{0}".format(roadSpeed), "speed": "{0}".format(speed), "distance": "{0}".format(dist), "timestamp": "{0}".format(timestamp)}

        #The data is getting inserted to the database.
        cardata.insert_one(data)
    
    print(' ')
    print('Done!')
    
#Calls the method with 1 system argument
generateData(int(sys.argv[1]))