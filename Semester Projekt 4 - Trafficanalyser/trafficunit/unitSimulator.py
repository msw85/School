#Imports
import os, sys

import objectdetectionModule as odm
import liveDataSimulator as lds

tempfilelist = os.listdir(os.getcwd())
filelist = []

for f in tempfilelist:
	if(f[-2:] != "__"):
		print(f)
		name, ext = f.split('.')
		if(ext == 'png' or ext == 'jpg' or ext == 'jpeg'):
			filelist.append(f)

for f in filelist:
	predictionsjson = odm.getDetectedObjects(f)
	for p in predictionsjson.payload:
		lds.livesimulator(p.display_name, sys.argv[1], sys.argv[2])

print('Done!')