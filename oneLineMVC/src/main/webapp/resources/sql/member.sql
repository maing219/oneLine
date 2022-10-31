create table member (
	id varchar2(10) not null,
	password nvarchar2(10) not null,
	name nvarchar2(10) not null,
	primary key(id)
);

insert into member values('admin', '1234', '¾îµå¹Î');
select * from member;