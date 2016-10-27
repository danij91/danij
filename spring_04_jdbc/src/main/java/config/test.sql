alter table mem add constraint mem_num_con primary key(num)

insert into mem values(40,'aaa',20,'korea')
select * from mem order by num desc