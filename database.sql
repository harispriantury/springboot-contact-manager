drop database contact_manager;

create database contact_manager;

create table users (
id varchar primary key,
name varchar(50) not null,
username varchar(50) not null unique,
password varchar(50) not null,
token varchar,
token_expired_at bigint
);


create table contacts (
id varchar primary key,
user_id varchar references users,
first_name varchar not null,
last_name varchar,
email varchar not null unique,
phone_number varchar not null unique
);

create table addresses (
id varchar primary key,
village varchar not null,
district varchar not null,
province varchar not null,
postal_code varchar not null,
street varchar not null unique,
contact_id varchar references contacts
);


