--liquibase formatted sql

--changeset alexeyz:1

create table if not exists track_time_info
(
    id          bigserial
        primary key,
    class_name  varchar(255),
    end_time    timestamp(6),
    method_name varchar(255),
    start_time  timestamp(6)
);