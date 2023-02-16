CREATE TABLE IF NOT EXISTS sysuser {
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(10),
    role varchar(10),
    PRIMARY KEY (id)
} ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS permission {
    id bigint(10) NOT NULL AUTO_INCREMENT,
    role varchar(10),
    policy varchar(100),
    PRIMARY KEY (id)
} ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS userinfo {
    id bigint(10) NOT NULL AUTO_INCREMENT,
    name varchar(10),
    password varchar(255),
    PRIMARY KEY (id)
} ENGINE=InnoDB DEFAULT CHARSET=utf8;