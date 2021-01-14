drop table RECIPE_CTG_CHEF CASCADE CONSTRAINTS;

create table RECIPE_CTG_CHEF(
	RECIPE_ID		NUMBER,
	CHEF_ID		NUMBER,
	FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(RECIPE_ID),
	FOREIGN KEY (CHEF_ID) REFERENCES CATEGORY_CHEF(CHEF_ID),
	PRIMARY KEY(RECIPE_ID, CHEF_ID)
);

insert into RECIPE_CTG_CHEF 
values(1, 1);

insert into RECIPE_CTG_CHEF 
values(2, 4);

insert into RECIPE_CTG_CHEF 
values(3, 2);
insert into RECIPE_CTG_CHEF 
values(3, 3);

insert into RECIPE_CTG_CHEF 
values(4, 1);

insert into RECIPE_CTG_CHEF 
values(5, 4);

insert into RECIPE_CTG_CHEF 
values(6, 3);

insert into RECIPE_CTG_CHEF 
values(7, 3);

insert into RECIPE_CTG_CHEF 
values(8, 1);
insert into RECIPE_CTG_CHEF 
values(8, 2);

insert into RECIPE_CTG_CHEF 
values(9, 4);

insert into RECIPE_CTG_CHEF 
values(11, 1);
insert into RECIPE_CTG_CHEF 
values(11, 2);

insert into RECIPE_CTG_CHEF 
values(12, 1);
