DELETE FROM product;
DELETE FROM barcode;
INSERT INTO barcode (barcode_id, barcode_value) VALUES (1, '101');
INSERT INTO product (name, price, fk_barcode_id) VALUES ('Produkt 1', '19.99', 1);
INSERT INTO barcode (barcode_id, barcode_value) VALUES (2, '202');
INSERT INTO product (name, price, fk_barcode_id) VALUES ('Produkt 2', '29.99', 2);
INSERT INTO barcode (barcode_id, barcode_value) VALUES (3, '303');
INSERT INTO product (name, price, fk_barcode_id) VALUES ('Produkt 3', '39.99', 3);
INSERT INTO barcode (barcode_id, barcode_value) VALUES (4, '404');
INSERT INTO product (name, price, fk_barcode_id) VALUES ('Produkt 4', '49.99', 4);
INSERT INTO barcode (barcode_id, barcode_value) VALUES (5, '505');
INSERT INTO product (name, price, fk_barcode_id) VALUES ('Produkt 5', '59.99', 5);
INSERT INTO barcode (barcode_id, barcode_value) VALUES (6, '606');
INSERT INTO product (name, price, fk_barcode_id) VALUES ('Produkt 6', '69.99', 6);
INSERT INTO barcode (barcode_id, barcode_value) VALUES (7, '707');
INSERT INTO product (name, price, fk_barcode_id) VALUES ('Produkt 7', '79.99', 7);
INSERT INTO barcode (barcode_id, barcode_value) VALUES (8, '808');
INSERT INTO product (name, price, fk_barcode_id) VALUES ('Produkt 8', '89.99', 8);