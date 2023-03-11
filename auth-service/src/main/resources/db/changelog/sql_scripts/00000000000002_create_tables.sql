-- generated by default as identity
create table taxes
(
    id         bigint generated by default as identity
        primary key,
    created_at timestamp,
    deleted    boolean not null,
    updated_at timestamp,
    user_iin varchar(12),
    grnz varchar(255),
    vehicle_type varchar(255),
    amount decimal,
    paid Boolean
);

alter table taxes
    owner to postgres;
