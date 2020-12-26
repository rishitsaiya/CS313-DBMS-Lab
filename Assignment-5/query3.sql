BEGIN TRANSACTION;
INSERT INTO course VALUES ('062', 'Binary Exploitation', 'Comp. Sci.', 6);
INSERT INTO section VALUES ('062', '1', 'Spring', '2019', 'Gates', '314', 'K');
INSERT INTO teaches VALUES ('79081', '062', '1', 'Spring', '2019');
ROLLBACK TRANSACTION;