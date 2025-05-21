CREATE KEYSPACE IF NOT EXISTS springcloud
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE springcloud;

CREATE TABLE IF NOT EXISTS accounts
(
    id         text PRIMARY KEY,
    uname      text,
    email      text,
    pwd        text,
    created_at timestamp,
    is_active  boolean
);

INSERT INTO accounts (id, uname, email, pwd, created_at, is_active)
VALUES ('a1f4d3e1-1234-4a56-b789-1234567890ab', 'kanka01', 'kanka01@example.com', 'pass123', toTimestamp(now()), true);

INSERT INTO accounts (id, uname, email, pwd, created_at, is_active)
VALUES ('b2e5f4c2-2345-4b67-c890-2345678901bc', 'kanka02', 'kanka02@example.com', 'mypassword', toTimestamp(now()), false);

INSERT INTO accounts (id, uname, email, pwd, created_at, is_active)
VALUES ('c3f6a5d3-3456-4c78-d901-3456789012cd', 'kanka03', 'kanka03@example.com', 'securepass', toTimestamp(now()), true);


select * from accounts;