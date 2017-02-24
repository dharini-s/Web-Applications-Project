DELIMITER $$

DROP PROCEDURE IF EXISTS `add_movie` $$ 

CREATE PROCEDURE add_movie (
	IN p_title VARCHAR(100), 
	IN p_year INT, 
	IN p_director VARCHAR(100), 
	IN p_banner_url VARCHAR(200), 
	IN p_trailer_url VARCHAR(200), 
	IN p_star_fname VARCHAR(50),
	IN p_star_lname VARCHAR(50),
	IN p_genre VARCHAR(32),
    OUT msg_addmovie VARCHAR(200),
    OUT msg_linkstar VARCHAR(200),
    OUT msg_linkgenre VARCHAR(200))

BEGIN
	   -- Error handle for primary key (movie id)
	   -- DECLARE EXIT HANDLER FOR 1062 SELECT CONCAT('Duplicate movie id (primary key) ', id, ' found.') AS msg;
	   DECLARE starid, genreid, movieid INT DEFAULT 0;

	   -- Check the movie title to see if the movie already exists
	IF EXISTS (SELECT title FROM movies WHERE title = p_title) THEN
   		SET msg_addmovie = 'The movie already exists.';
   	ELSE
   		INSERT INTO movies (title, year, director, banner_url, trailer_url) VALUES (p_title, p_year, p_director, p_banner_url, p_trailer_url);
        SET msg_addmovie = 'The movie is successfully added.';
   	END IF;

   	-- Check if the star exists (same first name and last name)
   	IF ((p_star_fname = '') OR p_star_fname IS NULL) AND ((p_star_lname = '') OR p_star_lname IS NULL) THEN
   		SET msg_linkstar = 'Star is empty. No star is linked to the movie.';
   	ELSEIF EXISTS (SELECT first_name FROM stars WHERE first_name = p_star_fname) AND EXISTS (SELECT last_name FROM stars WHERE last_name = p_star_lname) THEN
   		SELECT id INTO starid FROM stars WHERE first_name = p_star_fname AND last_name = p_star_lname;
   		SELECT id INTO movieid FROM movies WHERE title = p_title;
   		INSERT INTO stars_in_movies VALUES (starid, movieid);
        SET msg_linkstar = 'The star is successfully linked to the movie.';
   	ELSE
   		INSERT INTO stars (first_name, last_name) VALUES (p_star_fname, p_star_lname);
   		SELECT id INTO starid FROM stars WHERE first_name = p_star_fname AND last_name = p_star_lname;
   		SELECT id INTO movieid FROM movies WHERE title = p_title;
   		INSERT INTO stars_in_movies VALUES (starid, movieid);
        SET msg_linkstar = 'The star is successfully added into moviedb and linked to the movie.';
   	END IF;

   	-- Check if the genre exists
   	IF (p_genre = '') OR p_genre IS NULL THEN
   		SET msg_linkgenre = 'Genre is empty. No genre is linked to the movie.';
   	ELSEIF EXISTS (SELECT name FROM genres WHERE name = p_genre) THEN
   		SELECT id INTO genreid FROM genres WHERE name = p_genre;
   		SELECT id INTO movieid FROM movies WHERE title = p_title;
   		INSERT INTO genres_in_movies VALUES (genreid, movieid);
        SET msg_linkgenre = 'The genre is successfully linked to the movie.';
   	ELSE
   		INSERT INTO genres (name) VALUES (p_genre);
   		SELECT id INTO genreid FROM genres WHERE name = p_genre;
   		SELECT id INTO movieid FROM movies WHERE title = p_title;
   		INSERT INTO genres_in_movies VALUES (genreid, movieid);
        SET msg_linkgenre = 'The genre is successfully added into moviedb and linked to the movie.';
   	END IF;

END
$$

-- Change back DELIMITER to ; 
DELIMITER ;
