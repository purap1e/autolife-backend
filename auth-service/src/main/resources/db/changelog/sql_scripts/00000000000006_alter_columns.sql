ALTER TABLE transactions ADD reference_id uuid;
ALTER TABLE transactions ADD currency varchar(20);

create table services
(
    id         uuid
        primary key,
    created_at timestamp,
    deleted    boolean not null,
    updated_at timestamp,
    service_name varchar(255)
);
