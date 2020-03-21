CREATE SCHEMA IF NOT EXISTS `helpboi` DEFAULT CHARACTER SET = utf8 DEFAULT COLLATE = utf8_unicode_ci;

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

CREATE TABLE IF NOT EXISTS `organisation`
(
    `id`      BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(255) NOT NULL,
    `zipcode` VARCHAR(255) NOT NULL,
    `city`    VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `organisation_user`
(
    `organisation_id` BIGINT(20) NOT NULL,
    `user_id`         BIGINT(20) NOT NULL,
    `is_admin`        BIT(1)     NOT NULL,
    `is_verified`     BIT(1)     NOT NULL,

    PRIMARY KEY (`organisation_id`, `user_id`),
    FOREIGN KEY `fk_organisation_user_organisation_id` (`organisation_id`) REFERENCES `organisation` (`id`),
    FOREIGN KEY `fk_organisation_user_user_id` (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `symptom`
(
    `id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY `uq_name` (`name`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `patient`
(
    `id`              BIGINT(20) NOT NULL AUTO_INCREMENT,
    `organisation_id` BIGINT(20) NOT NULL,
    `firstname`       VARCHAR(255)    DEFAULT NULL,
    `lastname`        VARCHAR(255)    DEFAULT NULL,
    `gender`          VARCHAR(255)    DEFAULT NULL,
    `phone`           VARCHAR(255)    DEFAULT NULL,
    `date_of_birth`   TIMESTAMP  NULL DEFAULT NULL,
    `zipcode`         VARCHAR(255)    DEFAULT NULL,
    `city`            VARCHAR(255)    DEFAULT NULL,
    `address`         VARCHAR(255)    DEFAULT NULL,
    `status`          VARCHAR(255)    DEFAULT NULL,
    `notes`           LONGTEXT        DEFAULT NULL,

    PRIMARY KEY (`id`),
    FOREIGN KEY `fk_patient_organisation_id` (`organisation_id`) REFERENCES `organisation` (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `patient_symptom`
(
    `patient_id` BIGINT(20) NOT NULL,
    `symptom_id` BIGINT(20) NOT NULL,

    PRIMARY KEY (`patient_id`, `symptom_id`),
    FOREIGN KEY `fk_patient_symptom_patient_id` (`patient_id`) REFERENCES `patient` (`id`),
    FOREIGN KEY `fk_patient_symptom_symptom_id` (`symptom_id`) REFERENCES `symptom` (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `history`
(
    `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `patient_id`  BIGINT(20)   NOT NULL,
    `creator_id`  BIGINT(20)   NOT NULL,
    `created_at`  TIMESTAMP    NULL NOT NULL,
    `modified_at` TIMESTAMP    NULL NOT NULL,
    `type`        VARCHAR(255) NOT NULL,
    `content`     LONGTEXT     NOT NULL,

    PRIMARY KEY (`id`),
    FOREIGN KEY `fk_history_patient_id` (`patient_id`) REFERENCES `patient` (`id`),
    FOREIGN KEY `fk_history_creator_id` (`creator_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB;