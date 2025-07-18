
/**
 * Author:  Israel Reyes
 * Created: 3 jul 2025
 */


 
create database Tienda;

create table UserProfile (
	profile_id integer generated by default as identity primary key,
	first_name varchar(40) not null,
	paternal_lastname varchar(20) not null,
	maternal_lastname varchar(20) null,
	phone_number varchar(25) not null unique
)

create table AccountRole (
	role_id integer generated by default as identity primary key,
	role_name varchar(20) not null unique
)

create table Account (
	account_id integer generated by default as identity primary key,
	nickname varchar(15) not null unique,
	email varchar(50) not null unique,
	account_password varchar(30) not null,
	registration_date timestamp default current_timestamp,

	role_id int not null,
	profile_id int not null,

	constraint FK_ACCOUNT_ROLE foreign key (role_id) references AccountRole(role_id) on update cascade,
	constraint FK_ACCOUNT_PROFILE foreign key (profile_id) references UserProfile(profile_id) on update cascade

)
