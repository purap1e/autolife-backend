create table comments
(
    id         uuid
        primary key,
    created_at timestamp,
    user_id uuid,
    item_id uuid,
    comment text,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

create table items_comments
(
    item_id       uuid not null
        constraint fk_item_id
            references items,
    comments_id uuid not null
        constraint fk_comment_id
            references comments
);

alter table comments
    owner to postgres;

alter table items_comments
    owner to postgres;