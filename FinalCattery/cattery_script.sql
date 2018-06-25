-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cattery
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cattery
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cattery` DEFAULT CHARACTER SET latin1 ;
USE `cattery` ;

-- -----------------------------------------------------
-- Table `cattery`.`cat_colour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cattery`.`cat_colour` (
  `EMS_code` VARCHAR(15) NOT NULL,
  `name` VARCHAR(45) NOT NULL COMMENT 'имя цвета',
  PRIMARY KEY (`EMS_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Окрас животных. (blacksmoketortie, blacktortie tabby, Cremetabby, Creme, Bluetortie, Redtabby, red, redsilver mach tabby, Redsmoke, Blacksmoke etc). У каждого окраса есть свой международный код, который будет естественным первичным ключом';

CREATE UNIQUE INDEX `EMS_code_UNIQUE` ON `cattery`.`cat_colour` (`EMS_code` ASC);


-- -----------------------------------------------------
-- Table `cattery`.`cat_eyes_colour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cattery`.`cat_eyes_colour` (
  `FIFe_eyes_colour_code` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`FIFe_eyes_colour_code`))
ENGINE = InnoDB
AUTO_INCREMENT = 65
DEFAULT CHARACTER SET = latin1
COMMENT = 'FIFe code - starting at 61';


-- -----------------------------------------------------
-- Table `cattery`.`cat_pedigree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cattery`.`cat_pedigree` (
  `pedigree_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_female` VARCHAR(45) NOT NULL,
  `parent_male` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pedigree_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1
COMMENT = 'Таблица хранит информацию о предках животного. Здесь, возможно, надо сделать рекурсивную связь';

CREATE UNIQUE INDEX `pedigree_id_UNIQUE` ON `cattery`.`cat_pedigree` (`pedigree_id` ASC);


-- -----------------------------------------------------
-- Table `cattery`.`cat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cattery`.`cat` (
  `cat_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL COMMENT 'Фамилия кота - это имя питомника, то есть по умолчанию надо ставить имя данного питомника, если только это не приобретенный от пользователя котенок или просто кот из другого питомника',
  `gender` TINYINT(1) UNSIGNED NOT NULL COMMENT '0 - мужской, 1 - женский',
  `age` DATETIME NOT NULL,
  `photo` BLOB NULL DEFAULT NULL,
  `description` TEXT NOT NULL,
  `EMS_code` VARCHAR(15) NOT NULL COMMENT 'код цвета',
  `FIFe_eyes_colour_code` INT(10) UNSIGNED NOT NULL,
  `pedigree_id` INT(10) UNSIGNED NOT NULL,
  `price` DECIMAL(20,2) UNSIGNED NOT NULL,
  `flag_approved` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'если кота предложил пользователь, то он будет иметь значение 0, оценщик сможет увидеть заявки на оценку, принять кота, тогда администратор сможет его добавить, изменив значение на 1',
  `flag_sold` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '1 - продан. Чтобы можно было показывать, как котят вообще, так доступных для покупки',
  PRIMARY KEY (`cat_id`),
  CONSTRAINT `EMS_code`
    FOREIGN KEY (`EMS_code`)
    REFERENCES `cattery`.`cat_colour` (`EMS_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FIFe_eyes_colour_code`
    FOREIGN KEY (`FIFe_eyes_colour_code`)
    REFERENCES `cattery`.`cat_eyes_colour` (`FIFe_eyes_colour_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pedigree_id`
    FOREIGN KEY (`pedigree_id`)
    REFERENCES `cattery`.`cat_pedigree` (`pedigree_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1
COMMENT = 'Таблица хранит животных для продажи';

CREATE UNIQUE INDEX `cat_id_UNIQUE` ON `cattery`.`cat` (`cat_id` ASC);

CREATE INDEX `EMS_code_idx` ON `cattery`.`cat` (`EMS_code` ASC);

CREATE INDEX `FIFe_eyes_colour_code_idx` ON `cattery`.`cat` (`FIFe_eyes_colour_code` ASC);

CREATE INDEX `pedigree_id_idx` ON `cattery`.`cat` (`pedigree_id` ASC);


-- -----------------------------------------------------
-- Table `cattery`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cattery`.`user_role` (
  `role_id` TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1
COMMENT = 'Таблица с ролями. 1) Пользователь - покупает котенка / предлагает котенка в питомник 2) Админ - будет наполнять сайт контентом и редактировать его 3) Оценщик - оценивать предложенных пользователем котят, которых после Админ будет размещать. (Хотя, может, эти роли можно совместить)';


-- -----------------------------------------------------
-- Table `cattery`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cattery`.`user` (
  `user_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` BINARY(60) NOT NULL COMMENT 'Binary(60), потому что пароль шифруется с помощью BCrypt и в зашифрованном виде занимает такой размер',
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role_id` TINYINT(3) UNSIGNED NOT NULL DEFAULT '1' COMMENT '1 - пользователь\n2 - админ\n3 - оценщик',
  `colour_preference` VARCHAR(15) NULL DEFAULT NULL,
  `discount` TINYINT(3) UNSIGNED NULL DEFAULT '0' COMMENT 'скидка в процентах',
  `flag_banned` TINYINT(1) UNSIGNED NULL DEFAULT '0' COMMENT '1 - забанен админом за нарушение правил',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `colour_preference`
    FOREIGN KEY (`colour_preference`)
    REFERENCES `cattery`.`cat_colour` (`EMS_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `cattery`.`user_role` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = latin1
COMMENT = 'Таблица, которая будет хранить зарегистрированных пользователей';

CREATE UNIQUE INDEX `login_UNIQUE` ON `cattery`.`user` (`login` ASC);

CREATE UNIQUE INDEX `user_id_UNIQUE` ON `cattery`.`user` (`user_id` ASC);

CREATE UNIQUE INDEX `email_UNIQUE` ON `cattery`.`user` (`email` ASC);

CREATE INDEX `role_id_idx` ON `cattery`.`user` (`role_id` ASC);

CREATE INDEX `colour_preference_idx` ON `cattery`.`user` (`colour_preference` ASC);


-- -----------------------------------------------------
-- Table `cattery`.`user_feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cattery`.`user_feedback` (
  `feedback_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) UNSIGNED NOT NULL,
  `feedback` TEXT NOT NULL COMMENT 'Может, использовать VARCHAR(2000)?',
  PRIMARY KEY (`feedback_id`),
  CONSTRAINT `feedback_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `cattery`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1
COMMENT = 'Отзыв, который пользователь может оставить о питомнике';

CREATE UNIQUE INDEX `user_id_UNIQUE` ON `cattery`.`user_feedback` (`user_id` ASC);

CREATE INDEX `user_id_idx` ON `cattery`.`user_feedback` (`user_id` ASC);


-- -----------------------------------------------------
-- Table `cattery`.`user_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cattery`.`user_order` (
  `order_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) UNSIGNED NOT NULL,
  `cat_id` INT(10) UNSIGNED NOT NULL COMMENT 'Это котенок, которого покупают',
  `order_date` DATETIME NOT NULL,
  `total` DECIMAL(20,2) UNSIGNED NOT NULL COMMENT 'Это должна быть сумма оплаты с учетом скидки',
  PRIMARY KEY (`order_id`),
  CONSTRAINT `cat_id`
    FOREIGN KEY (`cat_id`)
    REFERENCES `cattery`.`cat` (`cat_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `cattery`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1
COMMENT = 'Эта таблица представляет собой заказ пользователя';

CREATE UNIQUE INDEX `order_id_UNIQUE` ON `cattery`.`user_order` (`order_id` ASC);

CREATE UNIQUE INDEX `user_id_UNIQUE` ON `cattery`.`user_order` (`user_id` ASC);

CREATE UNIQUE INDEX `cat_id_UNIQUE` ON `cattery`.`user_order` (`cat_id` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
