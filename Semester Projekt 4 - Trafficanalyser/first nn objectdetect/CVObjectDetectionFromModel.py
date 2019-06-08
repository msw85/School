#Imports
import torch
import torch.nn as nn
import torch.nn.functional as F
import torch.optim as optim

import torchvision
import torchvision.transforms as transforms

import os, sys, time, datetime, random

import matplotlib.pyplot as plt
import matplotlib.patches as patches

from PIL import Image

import numpy as np

testdatasetdir = "./Dataset/small_test/"

transform = transforms.Compose([torchvision.transforms.Resize((32,32)), transforms.ToTensor()]) 

testset = torchvision.datasets.ImageFolder(testdatasetdir, transform=transform)
testloader = torch.utils.data.DataLoader(testset, batch_size=4, shuffle=False, drop_last=True)


class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.conv1 = nn.Conv2d(3, 6, 5)
        self.pool = nn.MaxPool2d(2, 2)
        self.conv2 = nn.Conv2d(6, 16, 5)
        self.fc1 = nn.Linear(16 * 5 * 5, 120)
        self.fc2 = nn.Linear(120, 84)
        self.fc3 = nn.Linear(84, 10)
        
    def forward(self, x):
        x = self.pool(F.relu(self.conv1(x)))
        x = self.pool(F.relu(self.conv2(x)))
        x = x.view(-1, 16 * 5 * 5)
        x = F.relu(self.fc1(x))
        x = F.relu(self.fc2(x))
        x = self.fc3(x)
        
        return x

net = Net()

net = torch.load('small_model.pt')
net.eval()

print(net)

device = torch.device("cuda:0")
device_name = torch.cuda.get_device_name(device)

if device:
    print('Net transfering to GPU')
    net.to(device) 
print('Net transfered to GPU: ' + device_name)

classes = ('bus', 'car', 'motorbike', 'truck', 'van')

dataiter = iter(testloader)
images, labels = dataiter.next()

#Performance on the whole dataset:
correct = 0
total = 0

with torch.no_grad():
    for data in testloader:
        images, labels = data
        
        images, labels = images.to(device), labels.to(device) #transfer data to GPU
        
        outputs = net(images)
        _, predicted = torch.max(outputs.data, 1)
        total += labels.size(0)
        correct += (predicted == labels).sum().item()
        
print('Accuracy of the network on the 2500 test images: %d %%' % (100 * correct / total)) 

#See that classes perfomed well, and which didn't:
class_correct = list(0. for i in range(10))
class_total = list(0. for i in range(10))

with torch.no_grad():
    for data in testloader:
        images, labels = data
        
        images, labels = images.to(device), labels.to(device) #transfer data to GPU
        
        outputs = net(images).cuda()
        _, predicted = torch.max(outputs, 1)
        c= (predicted == labels).squeeze()
        
        for i in range(4):
            label = labels[i]
            class_correct[label] += c[i].item()
            class_total[label] += 1

for i in range(5):
    print('Accuracy of %5s : %2d %%' % (classes[i], 100 * class_correct[i] / class_total[i]))

img_size=416
conf_thres=0.8
nms_thres=0.4

### Works with one image/object ###
def predict_image(image):
    image_tensor = transform(image).float()
    image_tensor = image_tensor.unsqueeze_(0)
    input = image_tensor
    input = input.to(device)
    output = net(input)
    print(output)
    index = output.data.cpu().numpy().argmax()
    return index
    
to_pil = transforms.ToPILImage()
to_tensor = transforms.ToTensor()
labels = classes
image = Image.open("./images/bus.jpg")
image = to_tensor(image).cpu()
fig=plt.figure(figsize=(10, 10))
image = to_pil(image) # <----- Problem child
index = predict_image(image)
sub = fig.add_subplot(1, 1, 1)
#res = int(labels[index]) == index
#sub.set_title(str(classes[index]) + ":" + str(res))
sub.set_title(str(classes[index]))
plt.axis('off')
plt.imshow(image)
plt.show()

""" def detect_image(img):
    # scale and pad image
    ratio = min(img_size/img.size[0], img_size/img.size[1])
    imw = round(img.size[0] * ratio)
    imh = round(img.size[1] * ratio)
    img_transforms=transforms.Compose([transforms.Resize((imh,imw)),
         transforms.Pad((max(int((imh-imw)/2),0), 
              max(int((imw-imh)/2),0), max(int((imh-imw)/2),0),
              max(int((imw-imh)/2),0)), (128,128,128)),
         transforms.ToTensor(),
         ])
    # convert image to Tensor
    image_tensor = img_transforms(img).float()
    image_tensor = image_tensor.unsqueeze_(0)
    input_img = image_tensor
    # run inference on the model and get detections
    with torch.no_grad():
        detections = net(img)
        #detections = utils.non_max_suppression(detections, 80, 
                        #conf_thres, nms_thres)
    return detections[0]

# load image and get detections
to_tensor = transforms.ToTensor()
img_path = "./images/cars.jpg"
prev_time = time.time()
img = Image.open(img_path)
#img = to_tensor(img)
detections = predict_image(img) # <--- Problem child
print('hej')
inference_time = datetime.timedelta(seconds=time.time() - prev_time)
print ('Inference Time: %s' % (inference_time))
# Get bounding-box colors
cmap = plt.get_cmap('tab20b')
colors = [cmap(i) for i in np.linspace(0, 1, 20)]
img = np.array(img)
plt.figure()
fig, ax = plt.subplots(1, figsize=(12,9))
ax.imshow(img)
pad_x = max(img.shape[0] - img.shape[1], 0) * (img_size / max(img.shape))
pad_y = max(img.shape[1] - img.shape[0], 0) * (img_size / max(img.shape))
unpad_h = img_size - pad_y
unpad_w = img_size - pad_x
if detections is not None:
    unique_labels = detections[:, -1].cpu().unique()
    n_cls_preds = len(unique_labels)
    bbox_colors = random.sample(colors, n_cls_preds)
    # browse detections and draw bounding boxes
    for x1, y1, x2, y2, conf, cls_conf, cls_pred in detections:
        box_h = ((y2 - y1) / unpad_h) * img.shape[0]
        box_w = ((x2 - x1) / unpad_w) * img.shape[1]
        y1 = ((y1 - pad_y // 2) / unpad_h) * img.shape[0]
        x1 = ((x1 - pad_x // 2) / unpad_w) * img.shape[1]
        color = bbox_colors[int(np.where(
             unique_labels == int(cls_pred))[0])]
        bbox = patches.Rectangle((x1, y1), box_w, box_h,
             linewidth=2, edgecolor=color, facecolor='none')
        ax.add_patch(bbox)
        plt.text(x1, y1, s=classes[int(cls_pred)], 
                color='white', verticalalignment='top',
                bbox={'color': color, 'pad': 0})
plt.axis('off')
# save image
plt.savefig(img_path.replace(".jpg", "-det.jpg"),        
                  bbox_inches='tight', pad_inches=0.0)
plt.show() """