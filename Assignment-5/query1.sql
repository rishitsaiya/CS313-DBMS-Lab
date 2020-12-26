BEGIN TRANSACTION;
INSERT INTO course VALUES ('060', 'Capture The Flag', 'Comp. Sci.', 6);
INSERT INTO section VALUES ('060', '1', 'Spring', '2019', 'Gates', '707', 'K');
INSERT INTO teaches VALUES ('3199', '060', '1', 'Spring', '2019');
COMMIT TRANSACTION;