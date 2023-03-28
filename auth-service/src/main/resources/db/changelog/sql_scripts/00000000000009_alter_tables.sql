ALTER TABLE taxes DROP COLUMN user_iin;
ALTER TABLE taxes DROP COLUMN grnz;
ALTER TABLE taxes DROP COLUMN vehicle_type;

ALTER TABLE taxes ADD COLUMN vehicle_id uuid;
ALTER TABLE taxes ADD FOREIGN KEY (vehicle_id) REFERENCES vehicles (id);
