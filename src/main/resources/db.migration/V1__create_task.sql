CREATE TABLE IF NOT EXISTS `task`
(
    `id`                     BIGINT        NOT NULL AUTO_INCREMENT,
    `title`                  VARCHAR(100)  NULL DEFAULT NULL,
    `description`            VARCHAR(255)  NULL DEFAULT NULL,
    `created_at`             TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    `updated_at`             TIMESTAMP     ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 504
    DEFAULT CHARACTER SET = UTF8MB4;