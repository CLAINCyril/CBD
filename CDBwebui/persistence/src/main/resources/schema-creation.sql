--drop schema if exists `computer-database-db`;
  create schema if not exists `computer-database-db`;
  use `computer-database-db`;
  
  drop table if exists computer;
  drop table if exists company;

  create table company (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    constraint pk_company primary key (id))
  ;
create table user (name varchar(20) not null, password varchar(20) not null, constraint pk_example primary key (name));
  create table computer (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    introduced                timestamp NULL,
    discontinued              timestamp NULL,
    company_id                bigint default NULL,
    constraint pk_computer primary key (id))
  ;

  alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
  create index ix_computer_company_1 on computer (company_id);

  insert into company (id,name) values (  1,'Apple Inc.');
insert into company (id,name) values (  2,'Thinking Machines');
insert into company (id,name) values (  3,'RCA');
insert into company (id,name) values (  4,'Netronics');
insert into company (id,name) values (  5,'Tandy Corporation');
insert into company (id,name) values (  6,'Commodore International');
insert into company (id,name) values (  7,'MOS Technology');
insert into company (id,name) values (  8,'Micro Instrumentation and Telemetry Systems');
insert into company (id,name) values (  9,'IMS Associates, Inc.');
insert into company (id,name) values ( 10,'Digital Equipment Corporation');
insert into company (id,name) values ( 11,'Lincoln Laboratory');
insert into company (id,name) values ( 12,'Moore School of Electrical Engineering');
insert into company (id,name) values ( 13,'IBM');
insert into company (id,name) values ( 14,'Amiga Corporation');
insert into company (id,name) values ( 15,'Canon');
insert into company (id,name) values ( 16,'Nokia');
insert into company (id,name) values ( 17,'Sony');
insert into company (id,name) values ( 18,'OQO');
insert into company (id,name) values ( 19,'NeXT');    
insert into company (id,name) values ( 20,'Atari');

insert into computer (id,name,introduced,discontinued,company_id) values (  1,'MacBook Pro 15.4 inch',null,null,1);
insert into computer (id,name,introduced,discontinued,company_id) values (  2,'CM-2a',null,null,2);
insert into computer (id,name,introduced,discontinued,company_id) values (  3,'CM-200',null,null,2);
insert into computer (id,name,introduced,discontinued,company_id) values (  4,'CM-5e',null,null,2);
insert into computer (id,name,introduced,discontinued,company_id) values (  5,'CM-5','1991-01-01',null,2);
insert into computer (id,name,introduced,discontinued,company_id) values (  6,'MacBook Pro','2006-01-10',null,1);
insert into computer (id,name,introduced,discontinued,company_id) values (  7,'Apple IIe',null,null,null);
insert into computer (id,name,introduced,discontinued,company_id) values (  8,'Apple IIc',null,null,null);
insert into computer (id,name,introduced,discontinued,company_id) values (  9,'Apple IIGS',null,null,null);
insert into computer (id,name,introduced,discontinued,company_id) values ( 10,'Apple IIc Plus',null,null,null);
insert into computer (id,name,introduced,discontinued,company_id) values ( 11,'Apple II Plus',null,null,null);
insert into computer (id,name,introduced,discontinued,company_id) values ( 12,'Apple III','1980-05-01','1984-04-01',1);
insert into computer (id,name,introduced,discontinued,company_id) values ( 13,'Apple Lisa',null,null,1);
insert into computer (id,name,introduced,discontinued,company_id) values ( 14,'CM-2',null,null,2);
insert into computer (id,name,introduced,discontinued,company_id) values ( 15,'Connection Machine','1987-01-01',null,2);
insert into computer (id,name,introduced,discontinued,company_id) values ( 16,'Apple II','1977-04-01','1993-10-01',1);
insert into computer (id,name,introduced,discontinued,company_id) values ( 17,'Apple III Plus','1983-12-01','1984-04-01',1);
insert into computer (id,name,introduced,discontinued,company_id) values ( 18,'COSMAC ELF',null,null,3);
insert into computer (id,name,introduced,discontinued,company_id) values ( 19,'COSMAC VIP','1977-01-01',null,3);
insert into computer (id,name,introduced,discontinued,company_id) values ( 20,'ELF II','1977-01-01',null,4);


INSERT INTO user (`name`,`password`,`role`) VALUES ("Reese","AXD32UXL2BW","user"),("Jerry","FKF60FQK2EZ"),("Colby","DAG70BBA5XF"),("Yardley","ACG31SHF6ZP"),("Ralph","WMV66LLD0HY"),("Travis","YEW02DTM3YV"),("Brady","HNJ11KEX3YF"),("Griffith","OWL53LTK5EY"),("Griffith","GVI84KZP0WH"),("Octavius","UPY69IFI2XO");
INSERT INTO user (`name`,`password`) VALUES ("Emerson","YZQ07YHA4CJ"),("Aquila","COM39OLE7NB"),("Trevor","BNC34OPH1TX"),("Seth","ZYT58ZES2UE"),("Amal","YTW27NVM2ED"),("Ishmael","YSN58OMH2YC"),("Aladdin","OSP97MRG2ZC"),("Cole","BDP14FXX8YY"),("Macon","HHU70LOP8TL"),("Wyatt","AGJ01CAN4GV");
INSERT INTO user (`name`,`password`) VALUES ("Chaney","RMD65GDG7IP"),("Blaze","UOX95KVP8TZ"),("Ezekiel","CAC06XKC6YB"),("Damian","AKN30UWI0EN"),("Macaulay","KXA33GEZ5UW"),("Clarke","DLK98RTB9YD"),("Vance","JRF21XGV2PQ"),("Jerry","YXK14LMQ7WW"),("Tanek","JCM01DEX1VD"),("Lev","PUM90LME9TP");
INSERT INTO user (`name`,`password`) VALUES ("Galvin","VAW32VFG9ZW"),("Jordan","HMA73YRW8VO"),("Ferdinand","FYX59JZY9SN"),("David","QAO63CTX7SS"),("Vance","NSF89BXM9WK"),("Judah","PVO55FHE0GH"),("Abraham","DEL57BSU6QU"),("Tate","QGQ46TMV8GH"),("Griffith","KQF04MZS5VC"),("Rooney","YXX59MZE4BW");
INSERT INTO user (`name`,`password`) VALUES ("Simon","UUN04EVG5WZ"),("Orson","AMW43NTY1GH"),("Drake","EAP15YTX7BW"),("Amir","MGR13ALS9ZR"),("Burton","PIP86ZTJ4KN"),("Ray","VZJ91ZJC7WX"),("Grady","KBF43MST6CP"),("Mohammad","ZIB72CEN5LK"),("Keefe","XXH48TTY4YF"),("Stuart","UFG59UNU7WX");
INSERT INTO user (`name`,`password`) VALUES ("Keith","NDS11IPO6FL"),("Byron","GAO02ZHU0HK"),("Kamal","NVF50JST2BB"),("Yoshio","XDV43XJC0TY"),("Lucius","PBN66RVA8AN"),("Upton","QAD19BQJ0HJ"),("Jared","SYK77GKW7JX"),("Lewis","RMM63DTI6YZ"),("Amery","SIN59YSV9TJ"),("Keegan","EQA15TAX3VH");
INSERT INTO user (`name`,`password`) VALUES ("Elijah","BNN04OHN9BW"),("Marshall","KEN09IBX4NU"),("Hayes","SUH60OLW3EZ"),("Patrick","QDK47LUH4RR"),("Armand","RCJ75BKX6VR"),("Brett","ERV65IIX2LA"),("Paul","HNJ28EJC7XP"),("Abbot","NAP63DUQ4JR"),("Geoffrey","YDT03VVA4RU"),("Vernon","XOE19GQI7SQ");
INSERT INTO user (`name`,`password`) VALUES ("Drew","AIB53MMO4TA"),("Rudyard","UKU14LOI6XN"),("August","RZJ77FWF8WM"),("Xanthus","EAD05ZLS5FM"),("Rooney","JIE94SDS1NU"),("Yuli","MZB33CYN7OS"),("Andrew","CBT96MBP0XU"),("Oscar","UPR93VCF4AM"),("Dennis","EHH15XKN2TC"),("Norman","OJR07USG3YP");
INSERT INTO user (`name`,`password`) VALUES ("Joseph","RHR24MPR0KE"),("Dylan","NQT77XUR3MN"),("Hasad","EDU85TAV7QC"),("Brent","FJT12MAJ7AX"),("Mark","BRU42YUD7VS"),("Daquan","SSG42NRG9RK"),("Callum","DAE49FDE5DY"),("Colorado","KCH73BLG0ZG"),("Ethan","VXI07WNR6TG"),("Mark","DLB11ZPT4HK");
INSERT INTO user (`name`,`password`) VALUES ("Clinton","UXW13VRF7NM"),("Linus","ZHZ89BMT9XD"),("Emery","ATX72IQA5FS"),("Hayden","UAZ07XQO4VP"),("Fletcher","ZHZ25YPV4ZF"),("Warren","LEJ91YZR8ZD"),("Henry","BAK29SFE9TC"),("Kuame","WCW34VVA1ZZ"),("Wang","WTG43CRE5IU"),("Joshua","ABV24JEF3NT");

