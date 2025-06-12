create database jdbcdemo;

use jdbcdemo;
create table employee(
emp_id int primary key,
ename varchar(30),
salary int
);

insert into employee values(1,"raja",20000);
insert into employee values(2,"hari",60000);
insert into employee values(3,"jaipal",50000);
select * from employee;

delimiter $$
create procedure getId()
begin
select * from employee;
end $$
delimiter ;
delimiter $$
create procedure getById(In id int)
begin
select * from employee where emp_id=id;
end $$
delimiter ;

delimiter $$
create procedure getByIdOut(In id int,out name varchar(30))
begin
select ename from employee where emp_id=id into name;
end $$
delimiter ;