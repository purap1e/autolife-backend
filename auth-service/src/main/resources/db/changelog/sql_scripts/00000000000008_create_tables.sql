create table images
(
    id         uuid
        primary key,
    name varchar(255),
    type varchar(255),
    data oid
);

create table items
(
    id         uuid
        primary key,
    created_at timestamp,
    deleted    boolean not null,
    updated_at timestamp,
    amount int,
    title varchar(255),
    price decimal
);

create table items_images
(
    item_id       uuid not null
        constraint fkknbxvg28cl7okidd4of94mjuh
            references items,
    image_id uuid not null
        constraint uk_fo9ensuuxidpqnww8a9qe8yyo
            unique
        constraint fk99rxpfnwk9o89ldate8hrb7c1
            references images
);

alter table images
    owner to postgres;

alter table items
    owner to postgres;

alter table items_images
    owner to postgres;
