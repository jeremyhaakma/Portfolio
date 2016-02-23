DROP TABLE schedule;
CREATE TABLE schedule
                (date_only DATE,
               date_time  DATE );
INSERT into schedule VALUES
 ( TO_DATE('09-01-1990', 'dd-mm-yyyy'),
   TO_DATE('08-08-1993 10:53:00', 'dd-mm-yyyy hh24:mi:ss') );
   INSERT into schedule VALUES
   ( TO_DATE('15-02-2013', 'dd-mm-yyyy'),
   TO_DATE('01-01-2000 00:00:00', 'dd-mm-yyyy hh24:mi:ss') );
   INSERT into schedule VALUES
   ( TO_DATE('25-12-2001', 'dd-mm-yyyy'),
   TO_DATE('30-10-1984 14:43:43', 'dd-mm-yyyy hh24:mi:ss') );
   INSERT into schedule VALUES
   ( TO_DATE('15-06-1969', 'dd-mm-yyyy'),
   TO_DATE('04-01-1956 23:32:59', 'dd-mm-yyyy hh24:mi:ss') );

COMMIT;

SELECT TO_CHAR(date_only, 'dd-mm-yyyy'),
       TO_CHAR(date_time, 'hh-mi dd/mm/yy') from schedule;
