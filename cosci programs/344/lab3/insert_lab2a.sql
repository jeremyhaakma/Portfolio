DROP TABLE lab2a;

CREATE TABLE lab2a (
       i INT, r number(6,2), s varchar2(20), d DATE);

INSERT INTO lab2a VALUES (5, 10.5, 'Hello!', TO_DATE('09-March-1990', 'dd-month-yyyy'));
INSERT INTO lab2a VALUES (500000, 10.53456, 'single quotes only', TO_DATE('09-01-1990', 'dd-mm-yyyy'));

COMMIT;

select * from lab2a;
