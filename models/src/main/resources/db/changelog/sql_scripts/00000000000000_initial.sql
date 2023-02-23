create table users
(
    id         bigint generated by default as identity
        primary key,
    created_at timestamp,
    deleted    boolean not null,
    updated_at timestamp,
    first_name varchar(255),
    last_name  varchar(255),
    patronymic varchar(255),
    uin        bigint
);

alter table users
    owner to postgres;