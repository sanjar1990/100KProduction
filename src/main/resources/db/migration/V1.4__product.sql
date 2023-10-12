INSERT INTO product (name, title, description, price, discount_price, preview_attach_id, amount, brand, made_in, category_id, prt_id, id, rating, view_count)
SELECT 'Product Name 1', 'Product Title 1', 'Product Description 1', 100.0, 90.0, 'f47ac10b-58cc-4372-a567-0e02b2c3d479', 10, 'Brand 1', 'Made in China', 'f47ac10b-58cc-4372-a567-0e02b2c3d481', 'f47ac10b-58cc-4372-a567-0e02b2c3d479', 'f47ac10b-58cc-4372-a567-0e02b2c3d479', 5, 50
WHERE NOT EXISTS (SELECT id FROM product WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d479');

INSERT INTO product (name, title, description, price, discount_price, preview_attach_id, amount, brand, made_in, category_id, prt_id, id, rating, view_count)
SELECT 'Product Name 2', 'Product Title 2', 'Product Description 2', 150.0, 130.0, 'f47ac10b-58cc-4372-a567-0e02b2c3d480', 15, 'Brand 2', 'Made in USA', 'f47ac10b-58cc-4372-a567-0e02b2c3d482', 'f47ac10b-58cc-4372-a567-0e02b2c3d480', 'f47ac10b-58cc-4372-a567-0e02b2c3d480', 4, 40
WHERE NOT EXISTS (SELECT id FROM product WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d480');

INSERT INTO product (name, title, description, price, discount_price, preview_attach_id, amount, brand, made_in, category_id, prt_id, id, rating, view_count)
SELECT 'Product Name 3', 'Product Title 3', 'Product Description 3', 200.0, 190.0, 'f47ac10b-58cc-4372-a567-0e02b2c3d481', 20, 'Brand 3', 'Made in Germany', 'f47ac10b-58cc-4372-a567-0e02b2c3d484', 'f47ac10b-58cc-4372-a567-0e02b2c3d481', 'f47ac10b-58cc-4372-a567-0e02b2c3d483', 3, 30
WHERE NOT EXISTS (SELECT id FROM product WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d479');

INSERT INTO product (name, title, description, price, discount_price, preview_attach_id, amount, brand, made_in, category_id, prt_id, id, rating, view_count)
SELECT 'Product Name 4', 'Product Title 4', 'Product Description 4', 250.0, 230.0, 'f47ac10b-58cc-4372-a567-0e02b2c3d482', 25, 'Brand 4', 'Made in France', 'f47ac10b-58cc-4372-a567-0e02b2c3d486', 'f47ac10b-58cc-4372-a567-0e02b2c3d482', 'f47ac10b-58cc-4372-a567-0e02b2c3d484', 2, 20
WHERE NOT EXISTS (SELECT id FROM product WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d480');
--
-- INSERT INTO product (name, title, description, price, discount_price, preview_attach_id, amount, brand, made_in, category_id, prt_id, id, rating, view_count)
-- SELECT 'Product Name 5', 'Product Title 5', 'Product Description 5', 300.0, 280.0, 'f47ac10b-58cc-4372-a567-0e02b2c3d483', 30, 'Brand 5', 'Made in Japan', 'f47ac10b-58cc-4372-a567-0e02b2c3d488', 'f47ac10b-58cc-4372-a567-0e02b2c3d482', 'f47ac10b-58cc-4372-a567-0e02b2c3d485', 1, 10
-- WHERE NOT EXISTS (SELECT id FROM product WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d483');
