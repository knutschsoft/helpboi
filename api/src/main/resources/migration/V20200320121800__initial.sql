CREATE SCHEMA IF NOT EXISTS `helpboi` DEFAULT CHARACTER SET = utf8 DEFAULT COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `organisation`
(
    `id`      BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(255) NOT NULL,
    `zipcode` VARCHAR(255) NOT NULL,
    `city`    VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `user`
(
    `id`        BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `email`     VARCHAR(255) NOT NULL,
    `password`  VARCHAR(255) NOT NULL,
    `firstname` VARCHAR(255) DEFAULT NULL,
    `lastname`  VARCHAR(255) DEFAULT NULL,
    `phone`     VARCHAR(255) DEFAULT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY `uq_email` (`email`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `patient`
(
    `id`            BIGINT(20) NOT NULL AUTO_INCREMENT,
    `firstname`     VARCHAR(255)    DEFAULT NULL,
    `lastname`      VARCHAR(255)    DEFAULT NULL,
    `gender`        VARCHAR(255)    DEFAULT NULL,
    `phone`         VARCHAR(255)    DEFAULT NULL,
    `date_of_birth` TIMESTAMP  NULL DEFAULT NULL,
    `zipcode`       VARCHAR(255)    DEFAULT NULL,
    `city`          VARCHAR(255)    DEFAULT NULL,
    `address`       VARCHAR(255)    DEFAULT NULL,
    `status`        VARCHAR(255)    DEFAULT NULL,
    `notes`         LONGTEXT        DEFAULT NULL,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `symptom`
(
    `id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY `uq_name` (`name`)
) ENGINE = InnoDB;