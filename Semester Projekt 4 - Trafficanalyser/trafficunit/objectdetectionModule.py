#file_path: Local path to imagefile 
#project_id: 141138170468
#model_id: IOD5011670756426252288

#Imports
import sys

from google.cloud import automl_v1beta1
from google.cloud.automl_v1beta1.proto import service_pb2

# 'content' is base-64-encoded image data.
def getDetectedObjects(file_path, project_id = 141138170468, model_id = "IOD5011670756426252288"):
    with open(file_path, 'rb') as image:
        content = image.read()
    
    prediction_client = automl_v1beta1.PredictionServiceClient()

    name = 'projects/{}/locations/us-central1/models/{}'.format(project_id, model_id)
    payload = {'image': {'image_bytes': content }}
    params = {}
    request = prediction_client.predict(name, payload, params)
    return request  # waits till request is returned