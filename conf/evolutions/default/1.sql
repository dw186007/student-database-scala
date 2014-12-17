# --- First database schema

# --- !Ups

CREATE TABLE department (
	id   BIGINT       NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	CONSTRAINT pk_department PRIMARY KEY (id)
)
	ENGINE = innodb;

CREATE TABLE student (
	id            BIGINT       NOT NULL AUTO_INCREMENT,
	name          VARCHAR(255) NOT NULL,
	debts         INTEGER               DEFAULT 0,
	introduced    DATETIME     NULL,
	discontinued  DATETIME     NULL,
	department_id BIGINT,
	CONSTRAINT pk_student PRIMARY KEY (id)
)
	ENGINE = innodb;

ALTER TABLE student ADD CONSTRAINT fk_student_department_1 FOREIGN KEY (department_id) REFERENCES department (id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;
CREATE INDEX ix_student_department_1 ON student (department_id);


# --- !Downs

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS student;

SET FOREIGN_KEY_CHECKS = 1;

