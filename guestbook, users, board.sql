drop table guestbook;
drop sequence seq_guestbook_no;

drop table users;
drop sequence seq_users_no;

drop table board;
drop sequence seq_board_no;

create table guestbook(
    no          number,
    name        varchar2(80),
    password    varchar2(20),
    content     varchar2(2000),
    reg_date    date,
    primary key(no)
);

create table users(
    no          number,
    id          varchar2(20) unique not null,
    password    varchar2(20) not null,
    name        varchar2(20),
    gender      varchar2(10),
    primary key(no)
);

create table board(
    no          number,
    title       varchar2(500) not null,
    content     varchar2(4000),
    hit         number,
    reg_date    date not null,
    user_no     number not null,
    primary key(no),
    constraint board_fk foreign key(user_no)
    references users(no)
);

create sequence seq_guestbook_no
increment by 1
start with 1
nocache;

create sequence seq_users_no
increment by 1
start with 1
nocache;

create sequence seq_board_no
increment by 1
start with 1
nocache;

select * from user_sequences;

insert into guestbook
values(seq_guestbook_no.nextval, '이름', '비밀번호', '내용', sysdate);

insert into users
values(seq_users_no.nextval, '아이디', '비밀번호', '이름', '성별');

insert into board
values(seq_board_no.nextval, '제목', '내용1', 0, sysdate, 1);
commit;

select  no,
        name,
        password,
        content,
        to_char(reg_date, 'YYYY-MM-DD HH24:MI:SS') regDate
from guestbook
order by reg_date desc;

select  no,
        id,
        password,
        name,
        gender
from users
order by no asc;

select  no,
        name
from users
where id = ''
and password = '';

select  no,
        id,
        password,
        name,
        gender
from users
where no = 5;

select  no,
        id
from users
where id like 'keyword';

select  bo.no,
        title,
        hit,
        to_char(reg_date, 'YY-MM-DD HH24:MI') regDate,
        user_no userNo,
        name
from board bo, users us
where bo.user_no = us.no
order by regDate desc;

select  name,
        bo.no,
        user_no userNo,
        hit,
        to_char(reg_date, 'YY-MM-DD HH24:MI') regDate,
        title,
        content
from board bo, users us
where bo.user_no = us.no
and bo.no = 1;

select  rt.rn,
		rt.no,
		rt.title,
		rt.content,
		rt.hit,
		rt.regDate,
		rt.userNo,
		rt.name
from (select  rownum rn,
			  ot.no,
              ot.title,
              ot.content,
              ot.hit,
              ot.regDate,
              ot.userNo,
              ot.name
      from (select  bo.no,
                    title, 
                    content,
                    hit,
                    to_char(reg_date, 'YY-MM-DD HH24:MI') regDate,
                    us.no userNo,
                    name
            from board bo, users us
            where bo.user_no = us.no
            order by reg_date desc) ot
      ) rt
where rt.rn >= 21
and rt.rn <30;

select count(*)
from board bo, users us
where bo.user_no = us.no;

update users
set password = '1234',
    name = '태윤',
    gender = 'female'
where no = 2;

update board
set title = '',
    content = ''
where no = ''
and user_no = '';

update board
set hit = hit + 1
where no = 1;

delete from guestbook
where password = '1234'
and no = '1';

delete from board
where no = '1'
and user_no = '';