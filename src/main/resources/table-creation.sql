-- Single line SWL comment

/*
Multi
line
SQL
comments
*/

-- establish a 'namespace' for related DB entities to exist within
create schema tryagain4;

-- convenience command that will help us to reference the schema created above
set search_path to tryagain4;

create table app_users (
    id int generated always as identity,
    first_name varchar(12),
    last_name varchar(12),
    email varchar(20),
    username varchar(8),
    password varchar(8),
    primary key(id)

    );


/*
Ex. of inserting data into table:
    -   postgres=# insert into app_users (first_name, last_name, email, username, password)
    -              values ('Sara','Jones','SJones@cleanup.com','SJones','Qwerty12');
*/

/*
## Files connected to this table:
    -   create-tryagain3-user.sh
    -   is-email-available.sh
    -   is-username-available.sh
    -   sigg-mockdata.sql
*/
