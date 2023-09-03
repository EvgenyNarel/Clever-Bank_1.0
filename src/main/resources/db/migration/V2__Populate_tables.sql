/*data for the tables*/
--
INSERT INTO bank
VALUES ('84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a', 'Clever bank');
INSERT INTO bank
VALUES ('0e549729-183f-4c8e-b68c-401855423301', 'RGB bank');
INSERT INTO bank
VALUES ('837dd917-464e-4c1d-80cf-7e2a2de7cc80', 'Seven bank');
INSERT INTO bank
VALUES ('337059d7-793d-40f7-b482-2d628c67b8e6', 'Lord bank');
INSERT INTO bank
VALUES ('23ffa5c8-d534-4216-bb6c-d174ce02e11e', 'Grogu bank');

INSERT INTO customer
VALUES ('c58c7e62-3501-41e0-9461-4af251e3804d', 'Elijah Wood');
INSERT INTO customer
VALUES ('fa1dfe9b-fb59-4490-813d-69abb4201371', 'Ian McKellen');
INSERT INTO customer
VALUES ('f7500bc3-955a-44c2-868b-13576d8eb874', 'Viggo Mortensen');
INSERT INTO customer
VALUES ('26a9446e-5f62-4f2e-92f5-71faafedb538', 'Sean Astin');
INSERT INTO customer
VALUES ('6e52a9a8-603e-4df8-bf5e-5fbd979b4016', 'Billy Boyd');
INSERT INTO customer
VALUES ('a7fec0ed-2a3b-4ff9-bec9-95b284dcafe4', 'Dominic Monaghan');
INSERT INTO customer
VALUES ('5ca7213b-bea0-40ee-a0ce-db3db2b01e90', 'Dominic Monaghan');
INSERT INTO customer
VALUES ('7edcbdb9-8c2f-4dc9-9788-7533994a1488', 'Liv Tyler');
INSERT INTO customer
VALUES ('c8c9a121-4b5e-4dce-8397-5e87f186d416', 'Orlando Bloomd');
INSERT INTO customer
VALUES ('3f2cef77-866a-415e-8240-c922a8291e70', 'John Rhys-Davies');
INSERT INTO customer
VALUES ('5596290d-c4c3-45b0-9d76-486c53bcd713', 'Sean Bean');
INSERT INTO customer
VALUES ('f579ed90-170f-4273-af17-81853f06c208', 'Hugo Weaving');
INSERT INTO customer
VALUES ('9f47624f-00ba-4040-b451-2b0c97d02d07', 'Cate Blanchett');
INSERT INTO customer
VALUES ('d9d63f99-c9a7-47d6-8892-79d5d1ec5ebc', 'Andy Serkis');
INSERT INTO customer
VALUES ('1b917584-3799-40e4-b09d-c14f6dcae97d', 'Miranda Otto');
INSERT INTO customer
VALUES ('ae156372-5133-4cc1-b3a4-0abec86fc20f', 'David Wenham');
INSERT INTO customer
VALUES ('984e9881-d993-463e-9e4d-aafa4edd391b', 'Brad Dourif');
INSERT INTO customer
VALUES ('a3006eb2-938d-439a-831c-ebd9f23ebfcb', 'Ian Holm');
INSERT INTO customer
VALUES ('b8fc7f19-bf01-4507-8a22-327131967576', 'John Noble');
INSERT INTO customer
VALUES ('514b80e6-f65f-48f6-af91-bce45bdc5fd6', 'John Leigh');
INSERT INTO customer
VALUES ('2d55dda9-8e2f-40b8-962e-14bac7ede8cc', 'Sala Baker');

INSERT INTO account
VALUES (uuid_generate_v4(), '01ABCDEF12345678', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        'c58c7e62-3501-41e0-9461-4af251e3804d', 'BYN', 50000, '2022-11-02');
INSERT INTO account
VALUES (uuid_generate_v4(), '02GHIJKLM9012345', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        'fa1dfe9b-fb59-4490-813d-69abb4201371', 'BYN', 75000, '2022-12-03');
INSERT INTO account
VALUES (uuid_generate_v4(), '03NOPQRST5678901', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        'f7500bc3-955a-44c2-868b-13576d8eb874', 'BYN', 60000, '2022-03-13');
INSERT INTO account
VALUES (uuid_generate_v4(), '04UVWXYZA3456789', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        '26a9446e-5f62-4f2e-92f5-71faafedb538', 'BYN', 80000, '2022-03-14');
INSERT INTO account
VALUES (uuid_generate_v4(), '05BCDEFGH6789012', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        '6e52a9a8-603e-4df8-bf5e-5fbd979b4016', 'BYN', 10000, '2023-02-19');
INSERT INTO account
VALUES (uuid_generate_v4(), '06IJKLMNOP901234', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        'a7fec0ed-2a3b-4ff9-bec9-95b284dcafe4', 'BYN', 45000, '2023-08-22');
INSERT INTO account
VALUES (uuid_generate_v4(), '07QRSTUVWXYZ1234', '0e549729-183f-4c8e-b68c-401855423301',
        '5ca7213b-bea0-40ee-a0ce-db3db2b01e90', 'BYN', 55000, '2022-12-05');
INSERT INTO account
VALUES (uuid_generate_v4(), '08ABCDEFGHI23456', '0e549729-183f-4c8e-b68c-401855423301',
        '7edcbdb9-8c2f-4dc9-9788-7533994a1488', 'BYN', 70000, '2022-11-08');
INSERT INTO account
VALUES (uuid_generate_v4(), '09JKLMNOPQR34567', '0e549729-183f-4c8e-b68c-401855423301',
        'c8c9a121-4b5e-4dce-8397-5e87f186d416', 'BYN', 55000, '2023-02-22');
INSERT INTO account
VALUES (uuid_generate_v4(), '10STUVWXYZA67890', '0e549729-183f-4c8e-b68c-401855423301',
        '3f2cef77-866a-415e-8240-c922a8291e70', 'BYN', 90000, '2023-04-22');
INSERT INTO account
VALUES (uuid_generate_v4(), '11BCDEFGHI789012', '0e549729-183f-4c8e-b68c-401855423301',
        '5596290d-c4c3-45b0-9d76-486c53bcd713', 'BYN', 20000, '2022-10-23');
INSERT INTO account
VALUES (uuid_generate_v4(), '12JKLMNOPQRS8901', '837dd917-464e-4c1d-80cf-7e2a2de7cc80',
        'f579ed90-170f-4273-af17-81853f06c208', 'BYN', 35000, '2022-05-17');
INSERT INTO account
VALUES (uuid_generate_v4(), '13TUVWXYZAB56789', '837dd917-464e-4c1d-80cf-7e2a2de7cc80',
        '9f47624f-00ba-4040-b451-2b0c97d02d07', 'BYN', 60000, '2022-04-24');
INSERT INTO account
VALUES (uuid_generate_v4(), '14CDEFGHIJK67890', '837dd917-464e-4c1d-80cf-7e2a2de7cc80',
        'd9d63f99-c9a7-47d6-8892-79d5d1ec5ebc', 'BYN', 40000, '2023-05-29');
INSERT INTO account
VALUES (uuid_generate_v4(), '15LMNOPQRST90123', '837dd917-464e-4c1d-80cf-7e2a2de7cc80',
        '1b917584-3799-40e4-b09d-c14f6dcae97d', 'BYN', 65000, '2023-07-12');
INSERT INTO account
VALUES (uuid_generate_v4(), '16UVWXYZABC23456', '837dd917-464e-4c1d-80cf-7e2a2de7cc80',
        'ae156372-5133-4cc1-b3a4-0abec86fc20f', 'BYN', 30000, '2022-12-25');
INSERT INTO account
VALUES (uuid_generate_v4(), '17DEFGHIJKLM7890', '337059d7-793d-40f7-b482-2d628c67b8e6',
        '984e9881-d993-463e-9e4d-aafa4edd391b', 'BYN', 45000, '2022-03-25');
INSERT INTO account
VALUES (uuid_generate_v4(), '18PQRSTUVWXYZ345', '337059d7-793d-40f7-b482-2d628c67b8e6',
        'a3006eb2-938d-439a-831c-ebd9f23ebfcb', 'BYN', 70000, '2023-04-22');
INSERT INTO account
VALUES (uuid_generate_v4(), '19FGHIJKLMNOP678', '337059d7-793d-40f7-b482-2d628c67b8e6',
        'b8fc7f19-bf01-4507-8a22-327131967576', 'BYN', 50000, '2022-01-12');
INSERT INTO account
VALUES (uuid_generate_v4(), '20QRSTUWXYZ90123', '337059d7-793d-40f7-b482-2d628c67b8e6',
        '514b80e6-f65f-48f6-af91-bce45bdc5fd6', 'BYN', 80000, '2022-01-12');
INSERT INTO account
VALUES (uuid_generate_v4(), '21GHIJKLMNOPA345', '337059d7-793d-40f7-b482-2d628c67b8e6',
        '2d55dda9-8e2f-40b8-962e-14bac7ede8cc', 'BYN', 25000, '2023-06-07');
INSERT INTO account
VALUES (uuid_generate_v4(), '22VWXYZABCDE6789', '337059d7-793d-40f7-b482-2d628c67b8e6',
        'c58c7e62-3501-41e0-9461-4af251e3804d', 'BYN', 40000, '2022-07-15');
INSERT INTO account
VALUES (uuid_generate_v4(), '23HIJKLMNOPQR789', '337059d7-793d-40f7-b482-2d628c67b8e6',
        'fa1dfe9b-fb59-4490-813d-69abb4201371', 'BYN', 30000, '2022-04-25');
INSERT INTO account
VALUES (uuid_generate_v4(), '24XYZABCDE234567', '337059d7-793d-40f7-b482-2d628c67b8e6',
        'f7500bc3-955a-44c2-868b-13576d8eb874', 'BYN', 55000, '2023-07-17');
INSERT INTO account
VALUES (uuid_generate_v4(), '25IJKLMNOPQRS567', '23ffa5c8-d534-4216-bb6c-d174ce02e11e',
        '26a9446e-5f62-4f2e-92f5-71faafedb538', 'BYN', 45000, '2022-09-26');
INSERT INTO account
VALUES (uuid_generate_v4(), '26BCDEFGHIJK8901', '23ffa5c8-d534-4216-bb6c-d174ce02e11e',
        '6e52a9a8-603e-4df8-bf5e-5fbd979b4016', 'BYN', 70000, '2022-11-06');
INSERT INTO account
VALUES (uuid_generate_v4(), '27LMNOPQRST23456', '23ffa5c8-d534-4216-bb6c-d174ce02e11e',
        'a7fec0ed-2a3b-4ff9-bec9-95b284dcafe4', 'BYN', 35000, '2023-02-04');
INSERT INTO account
VALUES (uuid_generate_v4(), '28CDEFGHIJKLM678', '23ffa5c8-d534-4216-bb6c-d174ce02e11e',
        '5ca7213b-bea0-40ee-a0ce-db3db2b01e90', 'BYN', 90000, '2023-08-22');
INSERT INTO account
VALUES (uuid_generate_v4(), '29UVWXYZABC34567', '23ffa5c8-d534-4216-bb6c-d174ce02e11e',
        '7edcbdb9-8c2f-4dc9-9788-7533994a1488', 'BYN', 55000, '2023-05-29');
INSERT INTO account
VALUES (uuid_generate_v4(), '30DEFGHIJKLM9012', '23ffa5c8-d534-4216-bb6c-d174ce02e11e',
        'c8c9a121-4b5e-4dce-8397-5e87f186d416', 'BYN', 80000, '2022-11-10');
INSERT INTO account
VALUES (uuid_generate_v4(), '31PQRSTUVWXYZ123', '23ffa5c8-d534-4216-bb6c-d174ce02e11e',
        '3f2cef77-866a-415e-8240-c922a8291e70', 'BYN', 60000, '2022-11-09');
INSERT INTO account
VALUES (uuid_generate_v4(), '32FGHIJKLMNOP901', '23ffa5c8-d534-4216-bb6c-d174ce02e11e',
        '5596290d-c4c3-45b0-9d76-486c53bcd713', 'BYN', 45000, '2022-11-06');
INSERT INTO account
VALUES (uuid_generate_v4(), '33STUVWXYZ234567', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        'f579ed90-170f-4273-af17-81853f06c208', 'BYN', 70000, '2022-11-08');
INSERT INTO account
VALUES (uuid_generate_v4(), '34BCDEFGHI567890', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        '9f47624f-00ba-4040-b451-2b0c97d02d07', 'BYN', 40000, '2022-11-07');
INSERT INTO account
VALUES (uuid_generate_v4(), '35JKLMNOPQRST678', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        'd9d63f99-c9a7-47d6-8892-79d5d1ec5ebc', 'BYN', 65000, '2023-05-23');
INSERT INTO account
VALUES (uuid_generate_v4(), '36UVWXYZAB345678', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        '1b917584-3799-40e4-b09d-c14f6dcae97d', 'BYN', 30000, '2023-05-24');
INSERT INTO account
VALUES (uuid_generate_v4(), '37CDEFGHIJK90123', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        'ae156372-5133-4cc1-b3a4-0abec86fc20f', 'BYN', 45000, '2023-05-25');
INSERT INTO account
VALUES (uuid_generate_v4(), '38LMNOPQRS234567', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        '984e9881-d993-463e-9e4d-aafa4edd391b', 'BYN', 70000, '2023-05-29');
INSERT INTO account
VALUES (uuid_generate_v4(), '39DEFGHIJKLM7890', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        'a3006eb2-938d-439a-831c-ebd9f23ebfcb', 'BYN', 50000, '2023-05-28');
INSERT INTO account
VALUES (uuid_generate_v4(), '40PQRSTUVWXYZ678', '84a2bc6d-633b-4dd8-b2de-d7a6fc35b28a',
        'c58c7e62-3501-41e0-9461-4af251e3804d', 'BYN', 80000, '2023-05-14');
INSERT INTO account
VALUES ('c48ed62a-06eb-4c2e-b332-62e1b1676a44', '40PQRSTUVWXYZ680', '0e549729-183f-4c8e-b68c-401855423301',
        'fa1dfe9b-fb59-4490-813d-69abb4201371', 'BYN', 80000, '2023-06-15');
INSERT INTO account
VALUES ('e3454e71-e9e5-4bbe-8f8d-f210b6c006d1', '40PQRSTUVWXYZ692', '837dd917-464e-4c1d-80cf-7e2a2de7cc80',
        'f7500bc3-955a-44c2-868b-13576d8eb874', 'BYN', 80000, '2023-05-14');
INSERT INTO account
VALUES ('a42ca913-a684-43a6-851c-edd9fe83c4e3', '40PQRSTUVWXYZ644', '0e549729-183f-4c8e-b68c-401855423301',
        '26a9446e-5f62-4f2e-92f5-71faafedb538', 'BYN', 80000, '2023-05-14');

INSERT INTO operation
VALUES (uuid_generate_v4(), null, 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', 'BYN', 'REPLENISHMENT', 100, '2023-01-16');
INSERT INTO operation
VALUES (uuid_generate_v4(), null, 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', 'BYN', 'REPLENISHMENT', 200, '2023-04-17');
INSERT INTO operation
VALUES (uuid_generate_v4(), null, 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', 'BYN', 'REPLENISHMENT', 300, '2023-06-18');
INSERT INTO operation
VALUES (uuid_generate_v4(), null, 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', 'BYN', 'REPLENISHMENT', 50, '2023-08-16');

INSERT INTO operation
VALUES (uuid_generate_v4(), 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', null, 'BYN', 'WITHDRAWAL', 50, '2023-01-16');
INSERT INTO operation
VALUES (uuid_generate_v4(), 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', null, 'BYN', 'WITHDRAWAL', 60, '2023-04-17');
INSERT INTO operation
VALUES (uuid_generate_v4(), 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', null, 'BYN', 'WITHDRAWAL', 70, '2023-06-18');
INSERT INTO operation
VALUES (uuid_generate_v4(), 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', null, 'BYN', 'WITHDRAWAL', 200, '2023-08-16');

INSERT INTO operation
VALUES (uuid_generate_v4(), 'e3454e71-e9e5-4bbe-8f8d-f210b6c006d1', 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', 'BYN',
        'TRANSFER', 50, '2023-01-16');
INSERT INTO operation
VALUES (uuid_generate_v4(), 'e3454e71-e9e5-4bbe-8f8d-f210b6c006d1', 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', 'BYN',
        'TRANSFER', 60, '2023-04-12');
INSERT INTO operation
VALUES (uuid_generate_v4(), 'e3454e71-e9e5-4bbe-8f8d-f210b6c006d1', 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', 'BYN',
        'TRANSFER', 70, '2023-06-11');
INSERT INTO operation
VALUES (uuid_generate_v4(), 'e3454e71-e9e5-4bbe-8f8d-f210b6c006d1', 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', 'BYN',
        'TRANSFER', 80, '2023-08-23');

INSERT INTO operation
VALUES (uuid_generate_v4(), 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', 'e3454e71-e9e5-4bbe-8f8d-f210b6c006d1', 'BYN',
        'TRANSFER', 50, '2023-01-16');
INSERT INTO operation
VALUES (uuid_generate_v4(), 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', 'e3454e71-e9e5-4bbe-8f8d-f210b6c006d1', 'BYN',
        'TRANSFER', 60, '2023-04-12');
INSERT INTO operation
VALUES (uuid_generate_v4(), 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', 'e3454e71-e9e5-4bbe-8f8d-f210b6c006d1', 'BYN',
        'TRANSFER', 70, '2023-06-11');
INSERT INTO operation
VALUES (uuid_generate_v4(), 'c48ed62a-06eb-4c2e-b332-62e1b1676a44', 'e3454e71-e9e5-4bbe-8f8d-f210b6c006d1', 'BYN',
        'TRANSFER', 80, '2023-08-23');
