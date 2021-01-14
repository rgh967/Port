drop table RECIPE_CTG_PART CASCADE CONSTRAINTS;

create table RECIPE_CTG_PART(
	RECIPE_ID		NUMBER,
	PART_ID		NUMBER,
	FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(RECIPE_ID),
	FOREIGN KEY (PART_ID) REFERENCES CATEGORY_PART(PART_ID),
	PRIMARY KEY(RECIPE_ID, PART_ID)
);

insert into RECIPE_CTG_PART 
values(1, 1);
insert into RECIPE_CTG_PART 
values(1, 6);
insert into RECIPE_CTG_PART 
values(1, 13);

insert into RECIPE_CTG_PART 
values(2, 5);
insert into RECIPE_CTG_PART 
values(2, 8);

insert into RECIPE_CTG_PART 
values(3, 1);
insert into RECIPE_CTG_PART 
values(3, 6);
insert into RECIPE_CTG_PART 
values(3, 13);

insert into RECIPE_CTG_PART 
values(4, 2);
insert into RECIPE_CTG_PART 
values(4, 6);
insert into RECIPE_CTG_PART 
values(4, 8);
insert into RECIPE_CTG_PART 
values(4, 11);

insert into RECIPE_CTG_PART 
values(5, 4);
insert into RECIPE_CTG_PART 
values(5, 6);
insert into RECIPE_CTG_PART 
values(5, 8);
insert into RECIPE_CTG_PART 
values(5, 11);
insert into RECIPE_CTG_PART 
values(5, 13);

insert into RECIPE_CTG_PART 
values(6, 1);
insert into RECIPE_CTG_PART 
values(6, 6);
insert into RECIPE_CTG_PART 
values(6, 8);
insert into RECIPE_CTG_PART 
values(6, 10);
insert into RECIPE_CTG_PART 
values(6, 13);

insert into RECIPE_CTG_PART 
values(7, 5);
insert into RECIPE_CTG_PART 
values(7, 8);
insert into RECIPE_CTG_PART 
values(7, 13);

insert into RECIPE_CTG_PART 
values(8, 6);
insert into RECIPE_CTG_PART 
values(8, 13);

insert into RECIPE_CTG_PART 
values(9, 5);
insert into RECIPE_CTG_PART 
values(9, 6);
insert into RECIPE_CTG_PART 
values(9, 13);

insert into RECIPE_CTG_PART 
values(10, 9);
insert into RECIPE_CTG_PART 
values(10, 13);

insert into RECIPE_CTG_PART 
values(11, 6);
insert into RECIPE_CTG_PART 
values(11, 7);
insert into RECIPE_CTG_PART 
values(11, 8);
insert into RECIPE_CTG_PART 
values(11, 13);

insert into RECIPE_CTG_PART 
values(12, 8);
insert into RECIPE_CTG_PART 
values(12, 13);

select * from RECIPE_CTG_PART;