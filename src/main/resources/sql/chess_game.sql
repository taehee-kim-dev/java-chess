create user 'inbi'@'localhost' identified by '1234';

grant all privileges on *.* to 'inbi'@'localhost';

flush privileges;

CREATE DATABASE chess_game DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

show tables;


-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema chess_game
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema chess_game
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chess_game` DEFAULT CHARACTER SET utf8 ;
USE `chess_game` ;

-- -----------------------------------------------------
-- Table `chess_game`.`position`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`position` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `file_value` VARCHAR(255) NOT NULL,
  `rank_value` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess_game`.`piece`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`piece` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `color` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess_game`.`chess_game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`chess_game` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `current_turn_team_color` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess_game`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`player` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `team_color` VARCHAR(255) NOT NULL,
  `chess_game_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `chess_game_id`),
  INDEX `fk_player_chess_game1_idx` (`chess_game_id` ASC) VISIBLE,
  CONSTRAINT `fk_player_chess_game1`
    FOREIGN KEY (`chess_game_id`)
    REFERENCES `chess_game`.`chess_game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess_game`.`player_piece_position`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`player_piece_position` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `player_id` BIGINT UNSIGNED NOT NULL,
  `piece_id` BIGINT UNSIGNED NOT NULL,
  `position_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `player_id`, `piece_id`, `position_id`),
  INDEX `fk_piece_position_piece_idx` (`piece_id` ASC) VISIBLE,
  INDEX `fk_piece_position_position1_idx` (`position_id` ASC) VISIBLE,
  INDEX `fk_piece_position_player1_idx` (`player_id` ASC) VISIBLE,
  CONSTRAINT `fk_piece_position_piece`
    FOREIGN KEY (`piece_id`)
    REFERENCES `chess_game`.`piece` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_piece_position_position1`
    FOREIGN KEY (`position_id`)
    REFERENCES `chess_game`.`position` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_piece_position_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `chess_game`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- ----------------------------------------------------
-- queries
-- ----------------------------------------------------

use chess_game;

-- ----------------------------------------------------
-- position
-- ----------------------------------------------------

INSERT INTO position (file_value, rank_value) VALUES ('a', '1');
INSERT INTO position (file_value, rank_value) VALUES ('b', '1');
INSERT INTO position (file_value, rank_value) VALUES ('c', '1');
INSERT INTO position (file_value, rank_value) VALUES ('d', '1');
INSERT INTO position (file_value, rank_value) VALUES ('e', '1');
INSERT INTO position (file_value, rank_value) VALUES ('f', '1');
INSERT INTO position (file_value, rank_value) VALUES ('g', '1');
INSERT INTO position (file_value, rank_value) VALUES ('h', '1');

INSERT INTO position (file_value, rank_value) VALUES ('a', '2');
INSERT INTO position (file_value, rank_value) VALUES ('b', '2');
INSERT INTO position (file_value, rank_value) VALUES ('c', '2');
INSERT INTO position (file_value, rank_value) VALUES ('d', '2');
INSERT INTO position (file_value, rank_value) VALUES ('e', '2');
INSERT INTO position (file_value, rank_value) VALUES ('f', '2');
INSERT INTO position (file_value, rank_value) VALUES ('g', '2');
INSERT INTO position (file_value, rank_value) VALUES ('h', '2');

INSERT INTO position (file_value, rank_value) VALUES ('a', '3');
INSERT INTO position (file_value, rank_value) VALUES ('b', '3');
INSERT INTO position (file_value, rank_value) VALUES ('c', '3');
INSERT INTO position (file_value, rank_value) VALUES ('d', '3');
INSERT INTO position (file_value, rank_value) VALUES ('e', '3');
INSERT INTO position (file_value, rank_value) VALUES ('f', '3');
INSERT INTO position (file_value, rank_value) VALUES ('g', '3');
INSERT INTO position (file_value, rank_value) VALUES ('h', '3');

INSERT INTO position (file_value, rank_value) VALUES ('a', '4');
INSERT INTO position (file_value, rank_value) VALUES ('b', '4');
INSERT INTO position (file_value, rank_value) VALUES ('c', '4');
INSERT INTO position (file_value, rank_value) VALUES ('d', '4');
INSERT INTO position (file_value, rank_value) VALUES ('e', '4');
INSERT INTO position (file_value, rank_value) VALUES ('f', '4');
INSERT INTO position (file_value, rank_value) VALUES ('g', '4');
INSERT INTO position (file_value, rank_value) VALUES ('h', '4');

INSERT INTO position (file_value, rank_value) VALUES ('a', '5');
INSERT INTO position (file_value, rank_value) VALUES ('b', '5');
INSERT INTO position (file_value, rank_value) VALUES ('c', '5');
INSERT INTO position (file_value, rank_value) VALUES ('d', '5');
INSERT INTO position (file_value, rank_value) VALUES ('e', '5');
INSERT INTO position (file_value, rank_value) VALUES ('f', '5');
INSERT INTO position (file_value, rank_value) VALUES ('g', '5');
INSERT INTO position (file_value, rank_value) VALUES ('h', '5');

INSERT INTO position (file_value, rank_value) VALUES ('a', '6');
INSERT INTO position (file_value, rank_value) VALUES ('b', '6');
INSERT INTO position (file_value, rank_value) VALUES ('c', '6');
INSERT INTO position (file_value, rank_value) VALUES ('d', '6');
INSERT INTO position (file_value, rank_value) VALUES ('e', '6');
INSERT INTO position (file_value, rank_value) VALUES ('f', '6');
INSERT INTO position (file_value, rank_value) VALUES ('g', '6');
INSERT INTO position (file_value, rank_value) VALUES ('h', '6');

INSERT INTO position (file_value, rank_value) VALUES ('a', '7');
INSERT INTO position (file_value, rank_value) VALUES ('b', '7');
INSERT INTO position (file_value, rank_value) VALUES ('c', '7');
INSERT INTO position (file_value, rank_value) VALUES ('d', '7');
INSERT INTO position (file_value, rank_value) VALUES ('e', '7');
INSERT INTO position (file_value, rank_value) VALUES ('f', '7');
INSERT INTO position (file_value, rank_value) VALUES ('g', '7');
INSERT INTO position (file_value, rank_value) VALUES ('h', '7');

INSERT INTO position (file_value, rank_value) VALUES ('a', '8');
INSERT INTO position (file_value, rank_value) VALUES ('b', '8');
INSERT INTO position (file_value, rank_value) VALUES ('c', '8');
INSERT INTO position (file_value, rank_value) VALUES ('d', '8');
INSERT INTO position (file_value, rank_value) VALUES ('e', '8');
INSERT INTO position (file_value, rank_value) VALUES ('f', '8');
INSERT INTO position (file_value, rank_value) VALUES ('g', '8');
INSERT INTO position (file_value, rank_value) VALUES ('h', '8');

select * from position;

-- ----------------------------------------------------
-- piece
-- ----------------------------------------------------

INSERT INTO piece (name, color) VALUES ('Pawn', 'white');
INSERT INTO piece (name, color) VALUES ('Rook', 'white');
INSERT INTO piece (name, color) VALUES ('Bishop', 'white');
INSERT INTO piece (name, color) VALUES ('Knight', 'white');
INSERT INTO piece (name, color) VALUES ('Queen', 'white');
INSERT INTO piece (name, color) VALUES ('King', 'white');

INSERT INTO piece (name, color) VALUES ('Pawn', 'black');
INSERT INTO piece (name, color) VALUES ('Rook', 'black');
INSERT INTO piece (name, color) VALUES ('Bishop', 'black');
INSERT INTO piece (name, color) VALUES ('Knight', 'black');
INSERT INTO piece (name, color) VALUES ('Queen', 'black');
INSERT INTO piece (name, color) VALUES ('King', 'black');

select * from piece;