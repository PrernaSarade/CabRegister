create database CMS;
use CMS;
create table Driver(
	D_No varchar(10) primary key,
    D_Name varchar(20) not null,
    C_No varchar(20) not null,
    D_Addr varchar(30) not null,
    D_Phone varchar(20) not null,
    Process varchar(20) not null,
    Date_Join varchar(20) not null
    );
    
create table Employee(
	E_Id varchar(10) primary key,
    E_Name varchar(20) not null,
    E_Addr varchar(30) not null,
    E_Phone varchar(20) not null,
    E_Process varchar(20) not null,
    E_Loc varchar(20) not null
    );
    
create table Admin(
	A_No varchar(10) primary key,
    A_Name varchar(20) not null,
    A_Addr varchar(30) not null,
    A_Phone varchar(20) not null,
    A_Login varchar(20) not null,
    A_Pass varchar(20) not null
    );
    
create table Cab(
	C_No varchar(20) primary key,
    C_Name varchar(20) not null,
    C_Capacity int not null
    );
    
create table LogTable(
	D_No varchar(10) not null,
    D_Name varchar(20) not null,
    C_No varchar(20) not null,
    TimeIn varchar(20) not null,
    TimeOut varchar(20) not null,
    Date varchar(20) not null,
    MeterIn int not null,
    MeterOut int not null
    );
    
create table Del_Driver(
	D_No varchar(10) not null,
    D_Name varchar(20) not null,
    C_No varchar(20) not null,
    D_Addr varchar(30) not null,
    D_Phone varchar(20) not null,
    Date_Join varchar(20) not null,
    Date_Left varchar(20) not null
    );

insert into Driver values(107,'Sayee','MH 12 RS 1946','Akluj',
'7249180219','Working','15 May 2023');
select * from Driver;

insert into Employee values(20,'Aishu','Karmala',
'8436593285','Working','Jeur');

insert into Admin values(101,'Prerna','Pune',
'9511846119','Prerna101','Prerna101');

insert into Cab values('MH 12 TP 0609','Ertiga',
'6');

insert into LogTable values(1,'Tejas','MH 12 RS 9629','12:20','13:00',
'9 May 2023',1200,1300);

insert into Del_Driver values(1,'Tejas','MH 12 RS 9629','Pune',
'9146598209','5 Nov 2020','9 May 2023');