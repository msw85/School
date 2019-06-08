#Imports
import csv

#Read speed percentages and ticket amounts from file
#then add to dictionary
percent_amount = {}
percentreader = csv.DictReader(open('../backend/ticket_data/percent_amount.csv', 'r'))
for line in percentreader:
    percent_amount[line['percent']] = line['amount']

#Read speeds and additional amounts from file
#then add to dictionary
additional_fine = {}
additionalreader = csv.DictReader(open('../backend/ticket_data/additional_fine.csv', 'r'))
for line in additionalreader:
    additional_fine[line['speed']] = line['amount']

#Based on given (vehicle)speed and (max allowed) roadspeed
#return the ticket amount, if any.
#TODO:
# - Make it take a collection so the amount of method calls is limited
def calcTicketAmount(speed, roadspeed):
    amount = 0

    #Adjust speed based on how the police would to it
    if(speed < 100):
            speed = speed - 3
    if(speed > 100):
        speed = speed * 0.97
    
    #If subject is speeding calculate amount
    if(speed > roadspeed):
        percent = ((speed-roadspeed)/roadspeed)*100

        for key, value in percent_amount.items():
            first, last = key.split('-')
            if(percent >= int(first) and percent <= int(last)):
                amount = int(value)
                
        #If speed is above 140kmh add additional fine
        if(speed >= 140):
            for key, value in additional_fine.items():
                first, last = key.split('-')
                if(speed >= int(first) and speed <= int(last)):
                    amount += int(value)
    
    return amount