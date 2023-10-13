INSERT INTO category (name, info, photo_id, id)
SELECT 'Category Title 1', 'Category Info 1', 'f47ac10b-58cc-4372-a567-0e02b2c3d479', 'f47ac10b-58cc-4372-a567-0e02b2c3d481'
WHERE NOT EXISTS (SELECT id FROM category WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d481');

INSERT INTO category (name, info, photo_id, id)
SELECT 'Category Title 2', 'Category Info 2', 'f47ac10b-58cc-4372-a567-0e02b2c3d480', 'f47ac10b-58cc-4372-a567-0e02b2c3d482'
WHERE NOT EXISTS (SELECT id FROM category WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d482');

INSERT INTO category (name, info, photo_id, id)
SELECT 'Category Title 3', 'Category Info 3', 'f47ac10b-58cc-4372-a567-0e02b2c3d481', 'f47ac10b-58cc-4372-a567-0e02b2c3d484'
WHERE NOT EXISTS (SELECT id FROM category WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d484');

INSERT INTO category (name, info, photo_id, id)
SELECT 'Category Title 5', 'Category Info 5', 'f47ac10b-58cc-4372-a567-0e02b2c3d482', 'f47ac10b-58cc-4372-a567-0e02b2c3d488'
WHERE NOT EXISTS (SELECT id FROM category WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d488');

INSERT INTO category (name, info, photo_id, id)
SELECT 'Category Title 4', 'Category Info 4', 'f47ac10b-58cc-4372-a567-0e02b2c3d483', 'f47ac10b-58cc-4372-a567-0e02b2c3d486'
WHERE NOT EXISTS (SELECT id FROM category WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d486');
