#Imports
import sys
from pathlib import Path

from dateutil import parser

from flask import Flask
from flask import request

from bson.json_util import dumps

#Add workingdir path to allow import of modules placed in the project
absolutePath = str(Path().absolute())
pathlist = absolutePath.split('\\')
editedpath = '\\'.join(pathlist[:-1])
sys.path.insert(0, editedpath)
print(sys.path)

import backend.dBGetData as cardata
import backend.regressionModule as rm
import backend.kNNPredictModule as knn

#Instantiate and initialize Flask
app = Flask(__name__)

#Route to post data to the database.
# - Takes HTML style arguments in URL and initialises variables with the values.
# - Returns confirmation string, mainly for testing/demo purpose
@app.route('/post/data', methods = ['POST', 'GET'])
def postData():
    unitid = int(request.args.get('unitid'))
    vehicletype = str(request.args.get('vehicletype'))
    road = str(request.args.get('road'))
    roadspeed = int(request.args.get('roadspeed'))
    roadtype = str(request.args.get('roadtype'))
    speed = float(request.args.get('speed'))
    distance = float(request.args.get('distance'))
    timestamp = parser.parse(request.args.get('timestamp'))

    cardata.postData(unitid, vehicletype, road, roadspeed, roadtype, speed, distance, timestamp)

    return '{0}, {1}, {2}, {3}, {4}, {5}, {6} inserted'.format(unitid, vehicletype, road, roadspeed, roadtype, speed, distance, timestamp)



#Route to post data to the database.
# - Takes HTML style arguments in URL and initialises variables with the values.
# - Returns confirmation string, mainly for testing/demo purpose
@app.route('/get/prediction/<unitid>', methods = ['GET'])
def getPrediction(unitid):

    return dumps(knn.getPrediction(unitid))


#Route to get all data from database.
# - Returns all documents in JSON.
@app.route('/get/alldata', methods = ['GET'])
def getAllData():
    alldata = cardata.getAllData()
    
    return dumps(alldata)

#Route to get data by vehicletype from database.
# - Takes an argument of vehicletype and parses to database method call.
# - Returns all documents in JSON.
@app.route('/get/databyvehicle/<type>', methods = ['GET'])
def getDataByVehicletype(type):
    vehicletypedata = cardata.getDataByVehicletype(type)

    return dumps(vehicletypedata)

#Route to get data by road name from database.
# - Takes an argument of road and parses to database method call.
# - Returns all documents in JSON.
@app.route('/get/databyroad/<road>', methods = ['GET'])
def getDataByRoad(road):
    roaddata = cardata.getDataByRoad(road)

    return dumps(roaddata)

#Route to get data by road type from database.
# - Takes an argument of roadtype and parses to database method call.
# - Returns all documents in JSON.
@app.route('/get/databyroadtype/<roadtype>', methods = ['GET'])
def getDataByRoadtype(roadtype):
    roadtypedata = cardata.getDataByRoadtype(roadtype)

    return dumps(roadtypedata)

#Route to get data by road speed from database.
# - Takes an argument of roadspeed and parses to database method call.
# - Returns all documents in JSON.
@app.route('/get/databyroadspeed/<roadspeed>', methods = ['GET'])
def getDataByRoadSpeed(roadspeed):
    roadspeeddata = cardata.getDataByRoadSpeed(roadspeed)

    return dumps(roadspeeddata)

#Route to get data by date range from database.
# - Takes an argument of startdate and enddate and parses to database method call.
# - Returns all documents in JSON.
@app.route('/get/databydate/<startdate>/<enddate>', methods = ['GET'])
def getDataByDate(startdate, enddate):
    datedata = cardata.getDataByDate(startdate, enddate)

    return dumps(datedata)

#Route to get regression results from regression module.
# - Returns results in JSON.
@app.route('/get/calcregression', methods = ['GET'])
def calcRegression():
    liste = rm.getRegression()

    return dumps(liste)

#Runs the app, because the __name__ variable is set to string __name__
#when Flask was instantiated and initialized.
if __name__ == '__main__':
    app.run()