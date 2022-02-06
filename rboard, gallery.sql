drop table rboard;
drop sequence seq_rboard_no;
drop sequence seq_rgroup_no;

drop table gallery;
drop sequence seq_gallery_no;

create table rboard(
    no          number,
    user_no     number not null,
    title       varchar2(500),
    content     varchar2(4000),
    hit         number,
    reg_date    date,
    group_no    number,
    order_no    number,
    depth       number,
    primary key(no),
    constraint rboard_fk foreign key(user_no)
    references users(no)
);

create table gallery(
    no          number,
    user_no     number,
    content     varchar2(1000),
    filePath    varchar2(500),
    orgName     varchar2(200),
    saveName    varchar2(500),
    fileSize    number,
    primary key(no),
    constraint gallery_fk foreign key(user_no)
    references users(no)
);

create sequence seq_rboard_no
increment by 1
start with 1
nocache;

create sequence seq_rgroup_no
increment by 1
start with 1
nocache;

create sequence seq_gallery_no
increment by 1
start with 1
nocache;

select * from user_sequences;

insert into rboard
values(seq_rboard_no.nextval, 4, '제목', '내용', 0, sysdate,
    seq_rgroup_no.nextval, 1, 0);

insert into rboard
values(seq_rboard_no.nextval, 4, '제목', '내용', 0, sysdate,
    'group_no', 'order_no', 'depth');

insert into gallery
values(seq_gallery_no.nextval, 'userNo', 'content', 'filePath', 'orgName',
    'saveName', 'fileSize');
    
select  rb.no no,
        title,
        name,
        hit,
        to_char(reg_date, 'YY-MM-DD HH24:MI') regDate,
        user_no userNo,
        group_no groupNo,
        order_no orderNo,
        depth
from rboard rb, users us
where rb.user_no = us.no
order by group_no desc, order_no asc;

select  name,
        rb.no no,
        user_no userNo,
        hit,
        to_char(reg_date, 'YY-MM-DD HH24:MI') regDate,
        title,
        content
from rboard rb, users us
where rb.user_no = us.no
and rb.no = 1;

select  group_no groupNo,
        order_no orderNo,
        depth
from rboard
where no = 1;

select  ga.no,
        user_no userNo,
        content,
        filePath,
        orgName,
        saveName,
        fileSize,
        name writer
from gallery ga, users us
where ga.user_no = us.no
order by ga.no desc;

select  no,
        user_no userNo,
		content,
        saveName
from gallery			
where no = '';

update rboard
set hit = hit + 1
where no = 1;

update rboard
set    order_no = order_no + 1,
where order_no >= ''
and group_no = '';

update rboard
set title = '',
    content = ''
where no = '';

update rboard
set title = '삭제된 댓글입니다.'
where no = '';

delete from rboard
where no = 2;

delete from gallery
where no = ''
and user_no = '';
commit;