INSERT
    IGNORE INTO sysuser(name, role)
values
    ("root", "admin");

INSERT
    IGNORE INTO userinfo(name, password)
values
    ("root", "root");

INSERT
    IGNORE INTO permission(role, policy)
values
    ("admin", "all");

INSERT
    IGNORE INTO permission(role, policy)
values
    ("developer", "readwrite");

INSERT
    IGNORE INTO permission(role, policy)
values
    ("reviewer", "read");