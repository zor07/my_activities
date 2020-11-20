drop table if exists project;
create table project (
    id serial primary key,
    user_id int not null constraint entry_user_id_fk
        references "user",
    name varchar(100) not null,
    description varchar(250)
);

drop table if exists activity;
create table activity (
    id serial primary key,
    user_id int not null constraint entry_user_id_fk
        references "user",
    name varchar(100) not null,
    description varchar(250),
    project_id int
        constraint activity_project_id_fk
        references project
);

drop table if exists entry;
create table entry (
    id serial primary key,
    user_id int not null constraint entry_user_id_fk
        references "user",
    activity_id int not null
        constraint entry_activity_id_fk
        references activity,
    date date not null default now(),
    comment varchar(500) not null,
    unique (activity_id, date)
);

drop table if exists "user";
create table "user" (
    id serial primary key,
    username varchar(30) not null,
    password varchar(30) not null,
    accountNonExpired bool default false,
    accountNonLocked bool default false,
    credentialsNonExpired bool default false,
    enabled bool default true,
    unique (username)
);