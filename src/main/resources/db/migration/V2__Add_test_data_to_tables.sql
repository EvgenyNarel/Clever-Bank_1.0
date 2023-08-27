/*data for the tables*/
--
INSERT INTO bank VALUES (uuid_generate_v4(), 'Clever_bank');
INSERT INTO bank VALUES (uuid_generate_v4(), 'RGB_bank');
INSERT INTO bank VALUES (uuid_generate_v4(), 'Seven_bank');
INSERT INTO bank VALUES (uuid_generate_v4(), 'Lord_bank');
INSERT INTO bank VALUES (uuid_generate_v4(), 'Grogu_bank');

INSERT INTO customer
VALUES (uuid_generate_v4(), 'Elijah Wood');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Ian McKellen');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Viggo Mortensen');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Sean Astin');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Billy Boyd');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Dominic Monaghan');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Dominic Monaghan');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Liv Tyler');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Orlando Bloomd');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'John Rhys-Davies');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Sean Bean');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Hugo Weaving');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Cate Blanchett');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Andy Serkis');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Miranda Otto');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'David Wenham');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Brad Dourif');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Ian Holm');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'John Noble');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'John Leigh');
INSERT INTO customer
VALUES (uuid_generate_v4(), 'Sala Baker');

INSERT INTO account
VALUES (uuid_generate_v4(), '01ABCDEF12345678', 'b8705576-9d5c-4368-99d8-a8b930d52264' , 'c58c7e62-3501-41e0-9461-4af251e3804d', 'рубли', 50000, '2022-11-02');
INSERT INTO account
VALUES (uuid_generate_v4(), '02GHIJKLM9012345', '16ed11c6-6049-4fc9-93eb-dfdd8a5b3527', 'fa1dfe9b-fb59-4490-813d-69abb4201371', 'рубли', 75000, '2022-12-03');
INSERT INTO account
VALUES (uuid_generate_v4(), '03NOPQRST5678901', '4435754f-27d1-4144-af33-528ebb61e069', 'f7500bc3-955a-44c2-868b-13576d8eb874', 'рубли', 60000, '2022-03-13');
INSERT INTO account
VALUES (uuid_generate_v4(), '04UVWXYZA3456789', 'b23418a4-fa5e-4566-987f-29fd016b39ba', '26a9446e-5f62-4f2e-92f5-71faafedb538', 'рубли', 80000, '2022-03-14');
INSERT INTO account
VALUES (uuid_generate_v4(), '05BCDEFGH6789012', '2d4cba3e-4a2a-43d3-bcd8-f46ffbbae299','6e52a9a8-603e-4df8-bf5e-5fbd979b4016', 'рубли', 10000, '2023-02-19');
INSERT INTO account
VALUES (uuid_generate_v4(), '06IJKLMNOP901234', 'b8705576-9d5c-4368-99d8-a8b930d52264','a7fec0ed-2a3b-4ff9-bec9-95b284dcafe4' , 'рубли', 45000, '2023-08-22');
INSERT INTO account
VALUES (uuid_generate_v4(), '07QRSTUVWXYZ1234', '16ed11c6-6049-4fc9-93eb-dfdd8a5b3527', '5ca7213b-bea0-40ee-a0ce-db3db2b01e90', 'рубли', 55000, '2022-12-05');
INSERT INTO account
VALUES (uuid_generate_v4(), '08ABCDEFGHI23456', '4435754f-27d1-4144-af33-528ebb61e069', '7edcbdb9-8c2f-4dc9-9788-7533994a1488', 'рубли', 70000, '2022-11-08');
INSERT INTO account
VALUES (uuid_generate_v4(), '09JKLMNOPQR34567', 'b23418a4-fa5e-4566-987f-29fd016b39ba', 'c8c9a121-4b5e-4dce-8397-5e87f186d416', 'рубли', 55000, '2023-02-22');
INSERT INTO account
VALUES (uuid_generate_v4(), '10STUVWXYZA67890', '2d4cba3e-4a2a-43d3-bcd8-f46ffbbae299', '3f2cef77-866a-415e-8240-c922a8291e70', 'рубли', 90000, '2023-04-22');
INSERT INTO account
VALUES (uuid_generate_v4(), '11BCDEFGHI789012', 'b8705576-9d5c-4368-99d8-a8b930d52264', '5596290d-c4c3-45b0-9d76-486c53bcd713', 'рубли', 20000, '2022-10-23');
INSERT INTO account
VALUES (uuid_generate_v4(), '12JKLMNOPQRS8901', '16ed11c6-6049-4fc9-93eb-dfdd8a5b3527', 'f579ed90-170f-4273-af17-81853f06c208', 'рубли', 35000, '2022-05-17');
INSERT INTO account
VALUES (uuid_generate_v4(), '13TUVWXYZAB56789', '4435754f-27d1-4144-af33-528ebb61e069', '9f47624f-00ba-4040-b451-2b0c97d02d07', 'рубли', 60000, '2022-04-24');
INSERT INTO account
VALUES (uuid_generate_v4(), '14CDEFGHIJK67890', 'b23418a4-fa5e-4566-987f-29fd016b39ba','d9d63f99-c9a7-47d6-8892-79d5d1ec5ebc' , 'рубли', 40000, '2023-05-29');
INSERT INTO account
VALUES (uuid_generate_v4(), '15LMNOPQRST90123', '2d4cba3e-4a2a-43d3-bcd8-f46ffbbae299', '1b917584-3799-40e4-b09d-c14f6dcae97d', 'рубли', 65000, '2023-07-12');
INSERT INTO account
VALUES (uuid_generate_v4(), '16UVWXYZABC23456', 'b8705576-9d5c-4368-99d8-a8b930d52264', 'ae156372-5133-4cc1-b3a4-0abec86fc20f', 'рубли', 30000, '2022-12-25');
INSERT INTO account
VALUES (uuid_generate_v4(), '17DEFGHIJKLM7890', '16ed11c6-6049-4fc9-93eb-dfdd8a5b3527', '984e9881-d993-463e-9e4d-aafa4edd391b', 'рубли', 45000, '2022-03-25');
INSERT INTO account
VALUES (uuid_generate_v4(), '18PQRSTUVWXYZ345', '4435754f-27d1-4144-af33-528ebb61e069', 'a3006eb2-938d-439a-831c-ebd9f23ebfcb', 'рубли', 70000, '2023-04-22');
INSERT INTO account
VALUES (uuid_generate_v4(), '19FGHIJKLMNOP678', 'b23418a4-fa5e-4566-987f-29fd016b39ba', 'b8fc7f19-bf01-4507-8a22-327131967576', 'рубли', 50000, '2022-01-12');
INSERT INTO account
VALUES (uuid_generate_v4(), '20QRSTUWXYZ90123', '2d4cba3e-4a2a-43d3-bcd8-f46ffbbae299', '514b80e6-f65f-48f6-af91-bce45bdc5fd6', 'рубли', 80000, '2022-01-12');
INSERT INTO account
VALUES (uuid_generate_v4(), '21GHIJKLMNOPA345', 'b8705576-9d5c-4368-99d8-a8b930d52264', '2d55dda9-8e2f-40b8-962e-14bac7ede8cc', 'рубли', 25000, '2023-06-07');
INSERT INTO account
VALUES (uuid_generate_v4(), '22VWXYZABCDE6789', '16ed11c6-6049-4fc9-93eb-dfdd8a5b3527', 'c58c7e62-3501-41e0-9461-4af251e3804d', 'рубли', 40000, '2022-07-15');
INSERT INTO account
VALUES (uuid_generate_v4(), '23HIJKLMNOPQR789', '4435754f-27d1-4144-af33-528ebb61e069', 'fa1dfe9b-fb59-4490-813d-69abb4201371', 'рубли', 30000,'2022-04-25');
INSERT INTO account
VALUES (uuid_generate_v4(), '24XYZABCDE234567', 'b23418a4-fa5e-4566-987f-29fd016b39ba', 'f7500bc3-955a-44c2-868b-13576d8eb874', 'рубли', 55000, '2023-07-17');
INSERT INTO account
VALUES (uuid_generate_v4(), '25IJKLMNOPQRS567', '2d4cba3e-4a2a-43d3-bcd8-f46ffbbae299', '26a9446e-5f62-4f2e-92f5-71faafedb538', 'рубли', 45000, '2022-09-26');
INSERT INTO account
VALUES (uuid_generate_v4(), '26BCDEFGHIJK8901', 'b8705576-9d5c-4368-99d8-a8b930d52264', '6e52a9a8-603e-4df8-bf5e-5fbd979b4016', 'рубли', 70000, '2022-11-06');
INSERT INTO account
VALUES (uuid_generate_v4(), '27LMNOPQRST23456', '16ed11c6-6049-4fc9-93eb-dfdd8a5b3527', 'a7fec0ed-2a3b-4ff9-bec9-95b284dcafe4', 'рубли', 35000, '2023-02-04');
INSERT INTO account
VALUES (uuid_generate_v4(), '28CDEFGHIJKLM678', '4435754f-27d1-4144-af33-528ebb61e069', '5ca7213b-bea0-40ee-a0ce-db3db2b01e90', 'рубли', 90000, '2023-08-22');
INSERT INTO account
VALUES (uuid_generate_v4(), '29UVWXYZABC34567', 'b23418a4-fa5e-4566-987f-29fd016b39ba', '7edcbdb9-8c2f-4dc9-9788-7533994a1488', 'рубли', 55000, '2023-05-29');
INSERT INTO account
VALUES (uuid_generate_v4(), '30DEFGHIJKLM9012', '2d4cba3e-4a2a-43d3-bcd8-f46ffbbae299', 'c8c9a121-4b5e-4dce-8397-5e87f186d416', 'рубли', 80000, '2022-11-10');
INSERT INTO account
VALUES (uuid_generate_v4(), '31PQRSTUVWXYZ123', 'b8705576-9d5c-4368-99d8-a8b930d52264', '3f2cef77-866a-415e-8240-c922a8291e70', 'рубли', 60000, '2022-11-09');
INSERT INTO account
VALUES (uuid_generate_v4(), '32FGHIJKLMNOP901', '16ed11c6-6049-4fc9-93eb-dfdd8a5b3527', '5596290d-c4c3-45b0-9d76-486c53bcd713', 'рубли', 45000, '2022-11-06');
INSERT INTO account
VALUES (uuid_generate_v4(), '33STUVWXYZ234567', '4435754f-27d1-4144-af33-528ebb61e069', 'f579ed90-170f-4273-af17-81853f06c208', 'рубли', 70000, '2022-11-08');
INSERT INTO account
VALUES (uuid_generate_v4(), '34BCDEFGHI567890', 'b23418a4-fa5e-4566-987f-29fd016b39ba', '9f47624f-00ba-4040-b451-2b0c97d02d07', 'рубли', 40000, '2022-11-07');
INSERT INTO account
VALUES (uuid_generate_v4(), '35JKLMNOPQRST678', '2d4cba3e-4a2a-43d3-bcd8-f46ffbbae299', 'd9d63f99-c9a7-47d6-8892-79d5d1ec5ebc', 'рубли', 65000, '2023-05-23');
INSERT INTO account
VALUES (uuid_generate_v4(), '36UVWXYZAB345678', 'b8705576-9d5c-4368-99d8-a8b930d52264', '1b917584-3799-40e4-b09d-c14f6dcae97d', 'рубли', 30000, '2023-05-24');
INSERT INTO account
VALUES (uuid_generate_v4(), '37CDEFGHIJK90123', '16ed11c6-6049-4fc9-93eb-dfdd8a5b3527', 'ae156372-5133-4cc1-b3a4-0abec86fc20f', 'рубли', 45000, '2023-05-25');
INSERT INTO account
VALUES (uuid_generate_v4(), '38LMNOPQRS234567', '4435754f-27d1-4144-af33-528ebb61e069', '984e9881-d993-463e-9e4d-aafa4edd391b', 'рубли', 70000, '2023-05-29');
INSERT INTO account
VALUES (uuid_generate_v4(), '39DEFGHIJKLM7890', 'b23418a4-fa5e-4566-987f-29fd016b39ba', 'a3006eb2-938d-439a-831c-ebd9f23ebfcb', 'рубли', 50000, '2023-05-28');
INSERT INTO account
VALUES (uuid_generate_v4(), '40PQRSTUVWXYZ678', '2d4cba3e-4a2a-43d3-bcd8-f46ffbbae299', 'b8fc7f19-bf01-4507-8a22-327131967576', 'рубли', 80000, '2023-05-14');

INSERT INTO kind_operation
VALUES (uuid_generate_v4(), 'Replenishment');
INSERT INTO kind_operation
VALUES (uuid_generate_v4(), 'Withdrawal');
INSERT INTO kind_operation
VALUES (uuid_generate_v4(), 'Transfer');
INSERT INTO kind_operation
VALUES (uuid_generate_v4(), 'Statement');
