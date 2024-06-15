drop table if exists "todo";

CREATE TABLE "todo" (
                           "id" integer primary key AUTOINCREMENT,
                           "description" varchar(255) not null,
                           "status" varchar(255)
);