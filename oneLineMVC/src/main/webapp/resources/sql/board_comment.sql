create table board_comment (
    comment_num number not null,
    comment_board number not null,
    comment_id nvarchar2(10),
    comment_date nvarchar2(30),
    comment_content nvarchar2(1000),
    primary key(comment_num)
);

create sequence boardcomment_seq;

alter table board_comment add constraint fk_comment foreign key(comment_board)
	references board(num) on delete cascade;

insert into board_comment values(boardcomment_seq.nextval, '1', 'hgd', '2022/12/22', '³»¿ë1');

select * from board_comment;