
#Imports
import backend.dBConnection as conn

#Returns Curser object with all entries
def getAllData():
    cardata = conn.getDBConnection()

    return cardata.find() 


#Returns Curser object with all data (including objectid) for the specified type
def getDataByRoadId(unitid):
    cardata = conn.getDBConnection()

    return cardata.find({"unitid" : "{0}".format(type)})

#Returns Curser object with all data (including objectid) for the specified type
def getDataByVehicletype(type):
    cardata = conn.getDBConnection()

    return cardata.find({"vehicletype" : "{0}".format(type)})

#Returns Curser object with all data (including objectid) for the specified road
def getDataByRoad(road):
    cardata = conn.getDBConnection()

    return cardata.find({"road" : "{0}".format(road)})

#Returns Curser object with all data (including objectid) for the specified road
def getDataByRoadType(roadtype):
    cardata = conn.getDBConnection()

    return cardata.find({"roadtype" : "{0}".format(roadtype)})

#Returns Curser object with all data (including objectid) for the specified speed
def getDataByRoadSpeed(roadspeed):
    cardata = conn.getDBConnection()

    return cardata.find({"roadspeed" : "{0}".format(roadspeed)})

#Returns Curser object with all data (including objectid) for the specified data
def getDataByDate(startdate, enddate):
    cardata = conn.getDBConnection()

    return cardata.find({"timestamp":{"$gte": "{0}".format(startdate), "$lte": "{0}".format(enddate)}})

#Forms JSON with arguments given, persist data to database
def postData(vehicletype, road, roadspeed, roadtype, speed, distance, timestamp):
    cardata = conn.getDBConnection()
    document = {"vehicletype": "{0}".format(vehicletype), "road": "{0}".format(road), "roadspeed": roadspeed, "roadtype": "{0}".format(roadtype),  "speed": speed, "distance": distance, "timestamp": "{0}".format(timestamp)}
    
    cardata.insert(document)
