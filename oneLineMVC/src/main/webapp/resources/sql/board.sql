create table board (
	num number not null,
	id varchar2(10) not null,
	name nvarchar2(10) not null,
	subject nvarchar2(100) not null,
	content nvarchar2(500) not null,
	regist_day nvarchar2(30),
	hit number,
	recommend number,
	ip nvarchar2(20),
	primary key (num)
);

create sequence board_seq;

insert into board values(board_seq.nextval, 'hgd', '홍길동', '제목1', '내용1', '2022-10-20', 0, 0, '');

select * from board;