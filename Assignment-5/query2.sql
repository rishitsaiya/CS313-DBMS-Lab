BEGIN TRANSACTION;
INSERT INTO course VALUES ('061', 'Reverse Engineering', 'Comp. Sci.', 6);
INSERT INTO section VALUES ('061', '1', 'Spring', '2019', 'Gates', '707', 'K');
ROLLBACK TRANSACTION;
INSERT INTO teaches VALUES ('3199', '061', '1', 'Spring', '2019');
COMMIT TRANSACTION;