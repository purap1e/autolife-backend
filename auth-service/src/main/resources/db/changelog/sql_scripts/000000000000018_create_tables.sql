create table sto_comments
(
    id         uuid
        primary key,
    created_at timestamp,
    user_id    uuid,
    sto_id     uuid,
    comment    text,
    username   varchar(255),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (sto_id) REFERENCES sto (id)
);

alter table sto_comments
    owner to postgres;
