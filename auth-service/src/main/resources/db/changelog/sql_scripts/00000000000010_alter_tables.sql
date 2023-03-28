ALTER TABLE fines DROP COLUMN user_iin;
ALTER TABLE fines DROP COLUMN grnz;
ALTER TABLE fines DROP COLUMN vehicle_type;

ALTER TABLE fines ADD COLUMN vehicle_id uuid;
ALTER TABLE fines ADD FOREIGN KEY (vehicle_id) REFERENCES vehicles (id);
