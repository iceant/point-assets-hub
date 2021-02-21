create table if not exists assets(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    type VARCHAR(255),
    groupId TEXT,
    artifactId TEXT,
    version TEXT,
    detail TEXT,
    createDatetime DATETIME
);