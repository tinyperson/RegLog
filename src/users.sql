create database users;
use users;
create table userinfo(
	id int primary key auto_increment,
	username varchar(30) not null,
	password varchar(32) not null,
	nickname varchar(30),
	email varchar(30)
);