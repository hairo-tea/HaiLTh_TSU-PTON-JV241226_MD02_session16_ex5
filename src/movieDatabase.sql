create database moviesDB;
use moviesDB;

Create table Movies
(
    id       int primary key auto_increment,
    title    varchar(200),
    director varchar(100),
    year     int
);
DELIMITER //
CREATE PROCEDURE add_movie(
    IN in_title varchar(200),
    IN in_director varchar(100),
    IN in_year INT
)
BEGIN
insert into Movies(title, director, year)
values (in_title, in_director, in_year);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE list_movie()
BEGIN
select * from Movies;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE update_movie(
    IN in_id int,
    IN in_title varchar(200),
    IN in_director varchar(100),
    IN in_year INT
)
BEGIN
update Movies
set title=in_title,
    director=in_director,
    year=in_year
where id = in_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_movie(
    IN in_id INT
)
BEGIN
delete
from Movies
where id = in_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE find_id(
    IN in_id int
)
BEGIN
select *
from Movies
where id = in_id;
end //
DELIMITER ;