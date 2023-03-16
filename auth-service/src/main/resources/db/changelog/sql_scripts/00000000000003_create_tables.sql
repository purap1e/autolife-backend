create table transactions
(
    id         uuid
        primary key,
    created_at timestamp,
    service_id uuid,
    service_description varchar(255),
    service_amount decimal,
    user_id uuid
        constraint user_not_null_1
            NOT NULL
);

alter table transactions
    owner to postgres;
