insert into cart (customer_id, created_at, status, total_amount) values (900, '2023-11-27 12:15:35', 'IN_PROCESS', 300.00);
insert into cart (customer_id, created_at, status, total_amount) values (901, '2023-11-27 14:25:10', 'IN_PROCESS', 300.00);

INSERT INTO cart_item (cart_id, product_id, quantity, price) VALUES (1, 100, 2, 1500.50);
INSERT INTO cart_item (cart_id, product_id, quantity, price) VALUES (1, 105, 4, 300.00);

INSERT INTO cart_item (cart_id, product_id, quantity, price) VALUES (2, 102, 2, 150.00);
INSERT INTO cart_item (cart_id, product_id, quantity, price) VALUES (2, 104, 5, 800.50);