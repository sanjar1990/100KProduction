INSERT INTO attach (original_name, path, size, extension, id)
SELECT 'OriginalName1', 'path1', 1024, 'jpg', 'f47ac10b-58cc-4372-a567-0e02b2c3d479'
WHERE NOT EXISTS (SELECT 1 FROM attach WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d479');

INSERT INTO attach (original_name, path, size, extension, id)
SELECT 'OriginalName2', 'path2', 2048, 'png', 'f47ac10b-58cc-4372-a567-0e02b2c3d480'
WHERE NOT EXISTS (SELECT 1 FROM attach WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d480');

INSERT INTO attach (original_name, path, size, extension, id)
SELECT 'OriginalName3', 'path3', 3072, 'jpeg', 'f47ac10b-58cc-4372-a567-0e02b2c3d481'
WHERE NOT EXISTS (SELECT 1 FROM attach WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d481');

INSERT INTO attach (original_name, path, size, extension, id)
SELECT 'OriginalName4', 'path4', 4096, 'gif', 'f47ac10b-58cc-4372-a567-0e02b2c3d482'
WHERE NOT EXISTS (SELECT 1 FROM attach WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d482');

INSERT INTO attach (original_name, path, size, extension, id)
SELECT 'OriginalName5', 'path5', 5120, 'bmp', 'f47ac10b-58cc-4372-a567-0e02b2c3d483'
WHERE NOT EXISTS (SELECT 1 FROM attach WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d483');
