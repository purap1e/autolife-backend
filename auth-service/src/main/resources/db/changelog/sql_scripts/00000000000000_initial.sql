-- auto-generated definition
create table users
(
    id         uuid
        primary key,
    created_at timestamp,
    deleted    boolean not null,
    updated_at timestamp,
    first_name varchar(255),
    last_name  varchar(255),
    mid_name   varchar(255),
    password   varchar(255),
    phone      varchar(255)
        constraint uk_du5v5sr43g5bfnji4vb8hg5s3
            unique,
    uin        varchar(255)
        constraint uk_89u3i6agv520oej5ijbblxvpb
            unique
);

create table cards
(
    id         uuid
        primary key,
    created_at timestamp,
    deleted    boolean not null,
    updated_at timestamp,
    full_name varchar(255),
    card_number varchar(20)
        constraint card_number_unique
            unique,
    card_month int,
    card_year int,
    cvv varchar(255),
    amount decimal,
    user_id uuid
        constraint user_not_null
            NOT NULL
);


alter table users
    owner to postgres;

alter table cards
    owner to postgres;

