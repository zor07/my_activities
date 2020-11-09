drop table if exists project;
create table project (
    id serial primary key,
    name varchar(100) not null,
    description varchar(250)
);

drop table if exists activity;
create table activity (
    id serial primary key,
    name varchar(100) not null,
    description varchar(250),
    project_id int
        constraint activity_project_id_fk
        references project
);

drop table if exists entry;
create table entry (
    id serial primary key,
    activity_id int not null
        constraint entry_activity_id_fk
        references activity,
    date date not null default now(),
    comment varchar(500) not null,
    unique (activity_id, date)
);