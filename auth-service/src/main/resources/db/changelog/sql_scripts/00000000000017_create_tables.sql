create table sto
(
    id            uuid
        primary key,
    created_at    timestamp,
    deleted       boolean not null,
    updated_at    timestamp,
    title         varchar(255),
    phone         varchar(12),
    description   varchar(255),
    reviews_count int,
    average_grade float,
    location      varchar(255)
);

create table sto_images
(
    sto_id    uuid not null
        constraint fkknbxvdsaazgokidd4of94mjuh
            references sto,
    images_id uuid not null
        constraint uk_fo9ensuuxidpqnww8a9qdagh
            references images
);

alter table sto
    owner to postgres;

alter table sto_images
    owner to postgres;
