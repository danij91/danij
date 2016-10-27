create table mem(
 num number,
 name varchar2(50),
 age number,
 loc varchar2(100)
)

create sequence mem_num_seq
start with 1
increment by 1
nocache
nocycle;

drop sequence mem_num_seq

delete from mem

select * from mem order by num desc
delete from mem;
commit

alter table mem add constraint mem_num_con primary key(num);

insert into mem values(40,'aaa',20,'korea')
insert into mem values(40,'bbb',20,'korea')