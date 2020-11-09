drop table if exists entry;
drop table if exists activity;
drop table if exists project;

create table project (
     id int primary key auto_increment,
     name varchar(100) not null,
     description varchar(250)
);

create table activity (
      id int primary key auto_increment,
      name varchar(100) not null,
      description varchar(250),
      project_id int
          constraint activity_project_id_fk
          references project
);

create table entry (
       id int primary key auto_increment,
       activity_id int not null
           constraint entry_activity_id_fk
           references activity,
       date date not null default now(),
       comment varchar(500) not null,
       unique (activity_id, date)
);