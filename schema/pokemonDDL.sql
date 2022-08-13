-- 테이블 생성 코드
-- 생성하려는 table이 혹여 존재할 경우 삭제하는 명령어
drop table IF EXISTs pokemon;
drop table IF EXISTs owner;
drop table IF EXISTs pokemon_book;

-- pokemon, owner, pokemon_book table 생성

CREATE TABLE pokemon (
    pokemon_id               INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pokemon_name             VARCHAR(20) NOT NULL,
    pokemon_feature          VARCHAR(20),
    pokmeon_type				VARCHAR(20),
    pokemon_power			INT,
    pokemon_legend 			BOOLEAN,
    CONSTRAINT pk_pokemon PRIMARY KEY ( pokemon_id )
 );
 
CREATE TABLE owner (
    owner_id                  INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    onwer_name                VARCHAR(20),
    owner_age                 SMALLINT,
    owner_tier                VARCHAR(20),
    CONSTRAINT pk_owner PRIMARY KEY ( owner_id )
 );
 
 CREATE TABLE pokemon_book (
    pokemon_book_id                INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
 );
 
CREATE TABLE pokemon_book
(
    pokemon_book_id INT AUTO_INCREMENT PRIMARY KEY,

    pokemon_id INT NOT NULL,

    owner_id INT NULL,

    FOREIGN KEY (pokemon_id) REFERENCES pokemon(pokemon_id) ON UPDATE CASCADE
    FOREIGN KEY (owner_id) REFERENCES owner(owner_id) ON UPDATE CASCADE

    CONSTRAINT pk_pokemon_book PRIMARY KEY ( pokemon_book_id  )
);

ALTER TABLE pokemon_book_id AUTO_INCREMENT = 1000;
 