DROP TABLE IF EXISTS sysuser;

CREATE TABLE IF NOT EXISTS sysuser (
    id bigint(10) NOT NULL AUTO_INCREMENT,
    name varchar(10),
    role varchar(64),
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS permission;

CREATE TABLE IF NOT EXISTS permission (
    id bigint(10) NOT NULL AUTO_INCREMENT,
    role varchar(64) UNIQUE,
    policy varchar(100),
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS userinfo;

CREATE TABLE IF NOT EXISTS userinfo (
    id bigint(10) NOT NULL AUTO_INCREMENT,
    name varchar(10) UNIQUE,
    password varchar(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;