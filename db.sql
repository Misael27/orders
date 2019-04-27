-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema orderdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema orderdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `orderdb` ;
USE `orderdb` ;

-- -----------------------------------------------------
-- Table `orderdb`.`Product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orderdb`.`Product` ;

CREATE TABLE IF NOT EXISTS `orderdb`.`Product` (
  `ProductID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Price` VARCHAR(45) NOT NULL,
  `SKU` VARCHAR(45) NOT NULL,
  `CurrencyISO` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`ProductID`),
  UNIQUE INDEX `SKU_UNIQUE` (`SKU` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orderdb`.`Currency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orderdb`.`Currency` ;

CREATE TABLE IF NOT EXISTS `orderdb`.`Currency` (
  `CurrencyID` INT NOT NULL AUTO_INCREMENT,
  `CurrencyISO` VARCHAR(10) NOT NULL,
  `Enable` TINYINT(1) NOT NULL,
  PRIMARY KEY (`CurrencyID`),
  UNIQUE INDEX `ISOCode_UNIQUE` (`CurrencyISO` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orderdb`.`Stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orderdb`.`Stock` ;

CREATE TABLE IF NOT EXISTS `orderdb`.`Stock` (
  `StockID` INT NOT NULL AUTO_INCREMENT,
  `Quanty` INT NOT NULL,
  `Product_ProductID` INT NOT NULL,
  PRIMARY KEY (`StockID`),
  INDEX `fk_Stock_Product1_idx` (`Product_ProductID` ASC) VISIBLE,
  CONSTRAINT `fk_Stock_Product1`
    FOREIGN KEY (`Product_ProductID`)
    REFERENCES `orderdb`.`Product` (`ProductID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orderdb`.`Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orderdb`.`Order` ;

CREATE TABLE IF NOT EXISTS `orderdb`.`Order` (
  `OrderID` INT NOT NULL AUTO_INCREMENT,
  `Date` DATE NOT NULL,
  PRIMARY KEY (`OrderID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orderdb`.`OrderProduct`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orderdb`.`OrderProduct` ;

CREATE TABLE IF NOT EXISTS `orderdb`.`OrderProduct` (
  `OrderProductID` INT NOT NULL AUTO_INCREMENT,
  `Price` DOUBLE NOT NULL,
  `Quanty` INT(10) NOT NULL,
  `CurrencyISO` VARCHAR(10) NOT NULL,
  `Order_OrderID` INT NOT NULL,
  `Product_ProductID` INT NOT NULL,
  PRIMARY KEY (`OrderProductID`),
  INDEX `fk_OrderProduct_Order_idx` (`Order_OrderID` ASC) VISIBLE,
  INDEX `fk_OrderProduct_Product1_idx` (`Product_ProductID` ASC) VISIBLE,
  CONSTRAINT `fk_OrderProduct_Order`
    FOREIGN KEY (`Order_OrderID`)
    REFERENCES `orderdb`.`Order` (`OrderID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderProduct_Product1`
    FOREIGN KEY (`Product_ProductID`)
    REFERENCES `orderdb`.`Product` (`ProductID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
