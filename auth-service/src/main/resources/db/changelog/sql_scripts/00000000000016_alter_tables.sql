DROP TABLE items_comments;

ALTER TABLE comments ADD FOREIGN KEY (item_id) REFERENCES items (id);
ALTER TABLE comments ADD COLUMN user_name varchar(255);