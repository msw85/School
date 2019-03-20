use dmaa0917_1067407;

create table zipAndCities(
	id int primary key identity(0,1),
	zip varchar(10) not null,
	city varchar(50) not null,
);

create table persons(
	id int primary key identity(0,1),
	name varchar(50) not null,
	address varchar(50) not null,
	zacId int not null,
	phoneNo varchar(25) not null unique,
	eMail varchar(100) not null,
	type char(8) not null,
	foreign key(zacId) references zipAndCities(id) on delete no action
);

create table employees(
	pId int primary key not null,
	salary int not null,
	accountInfo varchar(100) not null,
	title varchar(50) not null,
	foreign key(pId) references persons(id) on delete cascade
);

create table customers(
	pId int primary key not null,
	isValuedCust bit not null,
	foreign key(pId) references persons(id) on delete cascade
);

create table foods(
	id int primary key identity(0,1),
	name varchar(50) not null,
	menuNo int not null unique,
	price int not null,
	type varchar(10) not null,
	description varchar(255) not null,
	size varchar(20) not null
);

create table brands(
	id int primary key not null,
	name varchar(50) not null,
);

create table drink(
	fId int primary key not null,
	bId int not null,
	isDiet bit not null,
	foreign key(fId) references foods(id) on delete cascade,
	foreign key(bId) references brands(id) on delete no action
);

create table pizza(
	fId int primary key not null,
	isLunchOffer bit not null,
	foreign key(fId) references foods(id) on delete cascade
);

create table fastFood(
	fId int primary key not null,
	foreign key(fId) references foods(id) on delete cascade
);

create table vertices(
	id int primary key identity(0,1),
	name varchar(50) not null,
	visited bit not null
);

create table routes(
	id int primary key identity(0,1),
	noOfVerts int not null
);

create table bjergbyGraph(
	id int primary key identity(0,1),
	vertCount int not null,
	noOfEdges int not null,
);

create table edges(
	id int primary key identity(0,1),
	description varchar(50) not null,
	weightVal int not null,
	destinationId int not null,
	sourceId int not null,
	foreign key(destinationId) references vertices(id) on delete no action,
	foreign key(sourceId) references vertices(id) on delete no action
);

create table routeAndVertices(
	id int primary key identity(0,1),
	vId int not null,
	rId int not null,
	foreign key(vId) references vertices(id) on delete no action,
	foreign key(rId) references routes(id) on delete no action
);

create table orders(
	id int primary key identity(0,1),
	orderDate date not null,
	dueTime date not null,
	toDeliver bit not null,
	isDelivered bit not null,
	isReady bit not null,
	totalPrice int not null,
	pId int not null,
	foreign key(pId) references persons(id) on delete no action,
);

create table orderLines(
	id int primary key identity(0,1),
	fId int not null,
	quantity int not null,
	price int not null,
	oId int not null,
	foreign key(fId) references foods(id) on delete no action,
	foreign key(oId) references orders(id) on delete no action
);

create table discounts(
	id int primary key identity(0,1),
	type varchar(25) not null unique,
	percentage int not null
);
