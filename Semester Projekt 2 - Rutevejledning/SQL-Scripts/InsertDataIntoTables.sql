use dmaa0917_1067407;
GO

--Table Attributes zipAndCities(zip, city)
INSERT INTO zipAndCities VALUES('9000', 'Aalborg');
INSERT INTO zipAndCities VALUES('9670', 'Løgstør');
INSERT INTO zipAndCities VALUES('9681', 'Ranum');
INSERT INTO zipAndCities VALUES('9690', 'Fjerritslev');
INSERT INTO zipAndCities VALUES('9700', 'Brønderslev');
INSERT INTO zipAndCities VALUES('9740', 'Jerslev J');
INSERT INTO zipAndCities VALUES('9750', 'Østervrå');
INSERT INTO zipAndCities VALUES('9760', 'Vrå');
INSERT INTO zipAndCities VALUES('9800', 'Hjørring');
INSERT INTO zipAndCities VALUES('9830', 'Tårs');
INSERT INTO zipAndCities VALUES('9850', 'Hirtshals');
INSERT INTO zipAndCities VALUES('9870', 'Sindal');
INSERT INTO zipAndCities VALUES('9881', 'Bindslev');
INSERT INTO zipAndCities VALUES('9990', 'Frederikshavn');

--Table Attributes brands(id, name)
INSERT INTO brands VALUES(0,'Carlsberg');
INSERT INTO brands VALUES(1,'Coca');
INSERT INTO brands VALUES(2,'Cocio');
INSERT INTO brands VALUES(3,'Egekilde');
INSERT INTO brands VALUES(4,'Fanta');
INSERT INTO brands VALUES(5,'Faxe');
INSERT INTO brands VALUES(6,'Harboe');
INSERT INTO brands VALUES(7,'Jolly');
INSERT INTO brands VALUES(8,'Pepsi');

--Table Attributes vertices(name, visited)
INSERT INTO vertices VALUES('Hans'' Grillbar', 0);
INSERT INTO vertices VALUES('Skagen Landevej', 0);
INSERT INTO vertices VALUES('Vejkryds 1', 0);
INSERT INTO vertices VALUES('Vejkryds 2', 0);
INSERT INTO vertices VALUES('Vejkryds 3', 0);
INSERT INTO vertices VALUES('Vejkryds 4', 0);
INSERT INTO vertices VALUES('Vejkryds 5', 0);
INSERT INTO vertices VALUES('Vejkryds 6', 0);
INSERT INTO vertices VALUES('Vejkryds 7', 0);
INSERT INTO vertices VALUES('Vejkryds 8', 0);
INSERT INTO vertices VALUES('Vejkryds 9', 0);
INSERT INTO vertices VALUES('Vejkryds 10', 0);
INSERT INTO vertices VALUES('Vejkryds 11', 0);
INSERT INTO vertices VALUES('Asdalvej', 0);
INSERT INTO vertices VALUES('Dalvænget', 0);
INSERT INTO vertices VALUES('Vestervænget', 0);
INSERT INTO vertices VALUES('Vestervænget 5', 0);
INSERT INTO vertices VALUES('Præstevænget', 0);
INSERT INTO vertices VALUES('Præstevænget 54', 0);
INSERT INTO vertices VALUES('Degnevænget', 0);
INSERT INTO vertices VALUES('Baekvernet', 0);
INSERT INTO vertices VALUES('Baekvernet 32', 0);
INSERT INTO vertices VALUES('Træholtvej', 0);
INSERT INTO vertices VALUES('Kirkevænget', 0);
INSERT INTO vertices VALUES('Brudehøjvej', 0);
INSERT INTO vertices VALUES('Søværnet 34',0);
INSERT INTO vertices VALUES('Søværnet',0);
INSERT INTO vertices VALUES('Kirkevænget 17',0);
INSERT INTO vertices VALUES('Lærkevej 5',0);
INSERT INTO vertices VALUES('Lærkevej',0);
INSERT INTO vertices VALUES('Vejkryds 12',0);

--Table Attributes bjergbyGraph(vertCount, noOfEdges)
INSERT INTO bjergbyGraph VALUES(31, 68);

--Table Attributes edges(description, weightVal, sourceId , destinationId, sourceId)
INSERT INTO edges VALUES('hansgrillbarskagenlandevej',1,1,0);
INSERT INTO edges VALUES('skagenlandevejhansgrillbar',1,0,1);
INSERT INTO edges VALUES('skagenlandevejvejkryds1',5,2,1);
INSERT INTO edges VALUES('vejkryds1skagenlandevej',5,1,2);
INSERT INTO edges VALUES('skagenlandevejvejkryds4',1,5,1);
INSERT INTO edges VALUES('vejkryds4skagenlandevej',1,1,5);
INSERT INTO edges VALUES('skagenlandevejvejkryds5',10,6,1);
INSERT INTO edges VALUES('vejkryds5skagenlandevej',10,1,6);
INSERT INTO edges VALUES('vejkryds1asdalvej',5,13,2);
INSERT INTO edges VALUES('asdalvejvejkryds1',5,2,13);
INSERT INTO edges VALUES('vejkryds2asdalvej',5,13,3);
INSERT INTO edges VALUES('asdalvejvejkryds2',5,3,13);
INSERT INTO edges VALUES('vejkryds2dalvaenget',7,14,3);
INSERT INTO edges VALUES('dalvaengetvejkryds2',7,3,14);
INSERT INTO edges VALUES('dalvaengetvejkryds3',3,4,14);
INSERT INTO edges VALUES('vejkryds3dalvaenget',3,14,4);
INSERT INTO edges VALUES('vejkryds3vestervaenget',3,15,4);
INSERT INTO edges VALUES('vestervaengetvejkryds3',3,4,15);
INSERT INTO edges VALUES('vestervaengetvestervaenget5',1,16,15);
INSERT INTO edges VALUES('vestervaenget5vestervaenget',1,15,16);
INSERT INTO edges VALUES('vejkryds4praestevaenget',2,17,5);
INSERT INTO edges VALUES('praestevaengetvejkryds4',2,5,17);
INSERT INTO edges VALUES('praestevaengetpraestevaenget54',1,18,17);
INSERT INTO edges VALUES('praestevaenget54praestevaenget',1,17,18);
INSERT INTO edges VALUES('vejkryds5degnevaenget',4,19,6);
INSERT INTO edges VALUES('degnevaengetvejkryds5',4,6,19);
INSERT INTO edges VALUES('degnevaengetvejkryds6',2,7,19);
INSERT INTO edges VALUES('vejkryds6degnevaenget',2,19,7);
INSERT INTO edges VALUES('vejkryds6baekvernet',2,20,7);
INSERT INTO edges VALUES('baekvernetvejkryds6',2,7,20);
INSERT INTO edges VALUES('baekvernetbaekvernet32',1,21,20);
INSERT INTO edges VALUES('baekvernet32baekvernet',1,20,21);
INSERT INTO edges VALUES('skagenlandevejvejkryds7',2,8,1);
INSERT INTO edges VALUES('vejkryds7skagenlandevej',2,1,8);
INSERT INTO edges VALUES('vejkryds7traeholtvej',4,22,8);
INSERT INTO edges VALUES('traeholtvejvejkryds7',4,8,22);
INSERT INTO edges VALUES('vejkryds8traeholtvej',2,22,9);
INSERT INTO edges VALUES('traeholtvejvejkryds8',2,9,22);
INSERT INTO edges VALUES('kirkevaengetvejkryds8',3,9,23);
INSERT INTO edges VALUES('vejkryds8kirkevaenget',3,23,9);
INSERT INTO edges VALUES('kirkevaengetvejkryds9',4,10,23);
INSERT INTO edges VALUES('vejkryds9kirkevaenget',4,23,10);
INSERT INTO edges VALUES('degnevaengettvejkryds9',3,10,19);
INSERT INTO edges VALUES('vejkryds9degnevaenget',3,19,10);
INSERT INTO edges VALUES('vejkryds10skagenlandevej',1,1,11);
INSERT INTO edges VALUES('skagenlandevejvejkryds10',1,11,1);
INSERT INTO edges VALUES('vejkryds10brudehoejvej',2,24,11);
INSERT INTO edges VALUES('brudehoejvejvejkryds10',2,11,24);
INSERT INTO edges VALUES('brudehoejvejvejkryds11',2,12,24);
INSERT INTO edges VALUES('vejkryds11brudehoejvej',2,24,12);
INSERT INTO edges VALUES('vejkryds11asdalvej',1,13,12);
INSERT INTO edges VALUES('asdalvejvejkryds11',1,12,13);
INSERT INTO edges VALUES('søværnetsøværnet34',1,25,26);
INSERT INTO edges VALUES('søværnet34søværnet',1,26,25);
INSERT INTO edges VALUES('søværnetvejkryds5',3,25,6);
INSERT INTO edges VALUES('vejkryds5søværnet',3,6,25);
INSERT INTO edges VALUES('vejkryds6søværnet',2,7,25);
INSERT INTO edges VALUES('kirkevænget17kirkevænget',1,27,23);
INSERT INTO edges VALUES('kirkevængetkirkevænget17',1,23,27);
INSERT INTO edges VALUES('præstevængetvejkryds12',2,17,30);
INSERT INTO edges VALUES('vejkryds12præstevænget',2,30,17);
INSERT INTO edges VALUES('brudehøjvejvejkryds12',5,24,30);
INSERT INTO edges VALUES('vejkryds12brudehøjvej',5,30,24);
INSERT INTO edges VALUES('vestervængetvejkryds12',2,15,30);
INSERT INTO edges VALUES('vejkryds12lærkevej',2,30,29);
INSERT INTO edges VALUES('lærkevejvejkryds12',2,29,30);
INSERT INTO edges VALUES('lærkevejlærkevej5',1,29,28);
INSERT INTO edges VALUES('lærkevej5lærkevej',1,28,29);

--Table Attributes persons(name, address, zacId, phoneNo, eMail, type)
INSERT INTO persons VALUES('Jane Doe', 'Baekvernet 32', 8, '90109000', 'Jane@doe.dk', 'Customer');
INSERT INTO persons VALUES('John Doe', 'Præstevænget 54', 1, '98971212', 'John@doe.dk', 'Customer');
INSERT INTO persons VALUES('Hans Calvalius', 'Disevænget 100', 8, '98971010', 'Hans@calvalius.dk', 'Employee');
INSERT INTO persons VALUES('John Hansen', 'Søværnet 34', 5, '98971011', 'John@hansen.dk', 'Employee');
INSERT INTO persons VALUES('Alic Jensen', 'Ryesgade 12', 2, '98212133', 'Alic@jensen.dk', 'Employee');
INSERT INTO persons VALUES('Bob Jensen', 'Vejgaarden 10', 4, '98001212', 'Bob@jense.dk', 'Employee');

--Table Attributes custommers(personId, isValuedCust)
INSERT INTO customers VALUES(0, 1);
INSERT INTO customers VALUES(1, 0);

--Table Attributes employee(pId, salary, accountInfo, title)
INSERT INTO employees VALUES(2, 20000, 1010-8829139, 'Ejer');
INSERT INTO employees VALUES(3, 15000, 1041-8812319, 'Bager');
INSERT INTO employees VALUES(4, 20000, 2110-1214324, 'Chauffør');
INSERT INTO employees VALUES(5, 20000, 8710-8434151, 'Chauffør');

--Table Attributes foods(name, menuNo, price, type, description, size)
INSERT INTO foods VALUES('Margarita', 1, 6000, 'pizza', 'Pizza med tomat og ost', 'Stor');
INSERT INTO foods VALUES('Hawaii', 2, 6000, 'pizza', 'Pizza med tomat, ost, skinke og ananas', 'Stor');
INSERT INTO foods VALUES('Peperoni', 3, 6500, 'pizza', 'Pizza med tomat, 2x ost, peperoni', 'Stor');
INSERT INTO foods VALUES('Kylling delux', 4, 6500, 'pizza', 'Salat pizza med kylling', 'Stor');
INSERT INTO foods VALUES('Kebab heaven', 5, 6500, 'pizza', 'Salat pizza med kebab', 'Stor');
INSERT INTO foods VALUES('CheeseLovers', 6, 7000, 'pizza', 'Pizza med tomat, 2x ost, blåskimmel og cheddar', 'Stor');
INSERT INTO foods VALUES('MeatLovers', 7, 7000, 'pizza', 'Pizza med tomat, ost, kødsovs, skinke, bacon og pepernoi', 'Stor');
INSERT INTO foods VALUES('Hans special', 8, 7000, 'pizza', 'Hemlig opskrift', 'Stor');
INSERT INTO foods VALUES('Cheeseburger', 9, 5000, 'fast food', 'Klassisk cheeseburger', 'Standard');
INSERT INTO foods VALUES('Hotdog', 10, 1500, 'fast food', 'En hotdog', 'Standard');
INSERT INTO foods VALUES('Pommesfrites', 11, 2500, 'fast food', 'Pommesfrites', 'Stor');
INSERT INTO foods VALUES('Cola', 12, 2500, 'drink', 'Cocacola', '0.5 liter');
INSERT INTO foods VALUES('Orange', 13, 2500, 'drink', 'Fanta orange', '0.5 liter');
INSERT INTO foods VALUES('Sport', 14, 2500, 'drink', 'Carlsberg sport', '1 liter');

--Table Attributes discounts(type, percentage)
INSERT INTO discounts VALUES('Employee', 15);
INSERT INTO discounts VALUES('Valued Customers', 10);
INSERT INTO discounts VALUES('Lunch offer', 30);

--Table Attributes drink(fId, bId, isDiet)
INSERT INTO drink VALUES(11, 1, 0);
INSERT INTO drink VALUES(12, 4, 0);
INSERT INTO drink VALUES(13, 0, 0);

--Table Attributes pizza(fId, lunchOffer)
INSERT INTO pizza VALUES(0,1);
INSERT INTO pizza VALUES(1,1);
INSERT INTO pizza VALUES(2,1);
INSERT INTO pizza VALUES(3,0);
INSERT INTO pizza VALUES(4,0);
INSERT INTO pizza VALUES(5,0);
INSERT INTO pizza VALUES(6,0);
INSERT INTO pizza VALUES(7,0);

--Table Attributes fastfood(fId)
INSERT INTO fastFood VALUES(8);
INSERT INTO fastFood VALUES(9);
INSERT INTO fastFood VALUES(10);
