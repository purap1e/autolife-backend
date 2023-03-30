ALTER TABLE items_images
ADD CONSTRAINT fk_images
FOREIGN KEY (images_id) REFERENCES images