DROP TABLE x;

CREATE TABLE x
       (i INT,
          r NUMBER(6,2) );

          INSERT INTO x VALUES (1, 1.1);
          INSERT INTO x VALUES (2, 2.2);
          INSERT INTO x VALUES (3, 3.3);

          COMMIT;

          SELECT * FROM x;
          
          
