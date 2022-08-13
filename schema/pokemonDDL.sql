-- 테이블 생성 코드
-- 생성하려는 table이 혹여 존재할 경우 삭제하는 명령어

-- 소문자 대문자로 수정
-- 부모 자식관계로 인한 삭제 불가로 순서 변경
drop table IF EXISTS pokemon_book;
drop table IF EXISTS pokemon;
drop table IF EXISTS owner;

-- pokemon, owner, pokemon_book table 생성

-- feature가 아니라 age로 수정
 
-- 오타 수정
-- 몇몇 포켓몬 이름이 길어서 pokemon_name varchar 최대길이 수정
CREATE TABLE pokemon (
    pokemon_id          	INT  NOT NULL AUTO_INCREMENT,
    pokemon_name          	VARCHAR(30) NOT NULL,
    pokemon_age         	int,
    pokemon_type          	VARCHAR(20),
    pokemon_power          	INT,
    pokemon_legend          	BOOLEAN,
    CONSTRAINT pk_pokemon          	PRIMARY KEY ( pokemon_id )
 );
 
 -- 오타 수정
CREATE TABLE owner (
    owner_id          	INT  NOT NULL AUTO_INCREMENT,
    owner_name          	VARCHAR(20),
    owner_age          	SMALLINT,
    owner_tier          	VARCHAR(20),
    CONSTRAINT pk_owner          	PRIMARY KEY ( owner_id )
 );
 
CREATE TABLE pokemon_book
(
    pokemon_book_id          	INT AUTO_INCREMENT,
    pokemon_id          	INT NOT NULL,
    owner_id          	INT NULL,
    FOREIGN KEY (pokemon_id)          	REFERENCES pokemon(pokemon_id) ON UPDATE CASCADE,
    FOREIGN KEY (owner_id)          	REFERENCES owner(owner_id) ON UPDATE CASCADE,
    CONSTRAINT pk_pokemon_book          	PRIMARY KEY ( pokemon_book_id  )
);
-- 오토 인크리스는 테이블 이름으로 해야함
ALTER TABLE pokemon_book AUTO_INCREMENT = 1000;
 