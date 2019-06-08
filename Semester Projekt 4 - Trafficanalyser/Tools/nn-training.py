#Imports
import torch
import torch.nn as nn
import torch.nn.functional as F
import torch.optim as optim

import torchvision
import torchvision.transforms as transforms

#import matplotlib.pyplot as plt
import numpy as np
import time

transform = transforms.Compose([torchvision.transforms.Resize((32,32)), transforms.ToTensor()]) 

datasetdir = "./Dataset/small_training/"
testdatasetdir = "./Dataset/small_test/"

trainset = torchvision.datasets.ImageFolder(datasetdir, transform = transform)
trainloader = torch.utils.data.DataLoader(trainset, batch_size=10, shuffle=True, drop_last=True)

classes = ('bus', 'car', 'motorbike', 'truck', 'van')

testset = torchvision.datasets.ImageFolder(testdatasetdir, transform=transform)
testloader = torch.utils.data.DataLoader(testset, batch_size=4, shuffle=False, drop_last=True)

#Random training images:
dataiter = iter(trainloader)
images, labels = dataiter.next()

#Define a Convolutional neural Network

#Copy the neural network from the Neural Networks section before
#and modify it to take 3-channel images:
class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.conv1 = nn.Conv2d(3, 6, 5)
        self.pool = nn.MaxPool2d(2, 2)
        self.conv2 = nn.Conv2d(6, 16, 5) #conv2 are float/double tensors
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
net.cuda()

#Define Loss function and optimizer
#Use s Classification Cross-Entropy loss and SGD with momentum:
criterion = nn.CrossEntropyLoss()
optimizer = optim.SGD(net.parameters(), lr=0.001, momentum=0.9) 

#Write this correctly and fit the whole code to cpu or cuda.
device = torch.device("cuda:0")
device_name = torch.cuda.get_device_name(device)

if device:
    print('Net transfering to GPU')
    net.to(device) 
print('Net transfered to GPU: ' + device_name)

#Train the network

start_time = time.time() #start time for benchmark training time

#Loop over data iterator and feed inputs to the network and optimize:
for epoch in range(50): #amount of times to loop
    
    running_loss = 0.0
    for i, data in enumerate(trainloader, 0):
        inputs, labels = data #get inputs
        inputs, labels = inputs.to(device), labels.to(device) #transfer inputs to GPU
        optimizer.zero_grad() #zero parameter gradients
        images, labels = images.to(device), labels.to(device) #transfer data to GPU
    
        
        #forward + backward + optimize
        outputs = net(inputs).cuda()
        loss = criterion(outputs, labels).cuda()
        loss.backward()
        optimizer.step()
        
        #print statistics
        running_loss += loss.item()
        if i % 10 == 9: #print every 10 mini-batches
            print('[%d, %5d] loss: %.3f' % (epoch + 1, i + 1, running_loss / 10))
            running_loss = 0.0
            
print('Finished training.')            
print('Training runtime: %s seconds' % (time.time() - start_time))

# Puts labels and images in variables
dataiter = iter(testloader)
images, labels = dataiter.next()

#Performance on the whole dataset:
correct = 0
total = 0

with torch.no_grad():
    for data in testloader:
        images, labels = data
        
        images, labels = images.to(device), labels.to(device) #transfer data to GPU
        
        outputs = net(images).cuda()
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


torch.save(net, './model/small_model.pt')