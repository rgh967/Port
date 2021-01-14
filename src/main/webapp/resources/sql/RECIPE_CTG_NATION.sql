drop table RECIPE_CTG_NATION CASCADE CONSTRAINTS;

create table RECIPE_CTG_NATION(
	RECIPE_ID		NUMBER,
	NATION_ID		NUMBER,
	FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(RECIPE_ID),
	FOREIGN KEY (NATION_ID) REFERENCES CATEGORY_NATION(NATION_ID),
	PRIMARY KEY(RECIPE_ID, NATION_ID)
);

insert into RECIPE_CTG_NATION 
values(1, 1);

insert into RECIPE_CTG_NATION 
values(2, 4);
insert into RECIPE_CTG_NATION 
values(2, 2);

insert into RECIPE_CTG_NATION 
values(3, 1);
insert into RECIPE_CTG_NATION 
values(3, 4);

insert into RECIPE_CTG_NATION 
values(4, 1);

insert into RECIPE_CTG_NATION 
values(5, 1);
insert into RECIPE_CTG_NATION 
values(5, 4);

insert into RECIPE_CTG_NATION 
values(6, 3);

insert into RECIPE_CTG_NATION 
values(7, 3);

insert into RECIPE_CTG_NATION 
values(8, 1);

insert into RECIPE_CTG_NATION 
values(9, 4);

insert into RECIPE_CTG_NATION 
values(10, 1);
insert into RECIPE_CTG_NATION 
values(10, 4);

insert into RECIPE_CTG_NATION 
values(11, 1);

insert into RECIPE_CTG_NATION 
values(12, 1);

select * from RECIPE_CTG_NATION;