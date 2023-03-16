create table fines
(
    id         uuid
        primary key,
    user_iin varchar(12),
    fine_description varchar(255),
    grnz varchar(255),
    vehicle_type varchar(255),
    amount_MCI int,
    paid Boolean
);

INSERT INTO fines (id, user_iin, fine_description, grnz, vehicle_type, amount_MCI, paid) values ('07562ddc-a394-4f12-b356-6e1b2f87b97a', '000000000003', 'Driving a vehicle by a driver who does not have a driver''s license or a temporary license issued in exchange for a driver''s license for the right to drive', 'dsaz', 'BUS', 5, false),
                         ('96c105c2-743e-4d51-9389-63a829dadea1', '000000000003', 'Evasion from concluding a contract of compulsory insurance', 'ds321az', 'CAR', 10, false),
                         ('bc7774f0-6ec4-4990-b535-e28f26f9efec', '000000000001', 'Exceeding the speed limit by 20-40 km/h', 'd21az', 'FREIGHT', 10, false),
                         ('64d6479a-8543-47a9-b246-034ffb6755ff', '000000000001', 'Driving at a traffic light signal or a traffic controller''s prohibition gesture', 'd21azdsa', 'CAR', 10, false);

alter table fines
    owner to postgres;
