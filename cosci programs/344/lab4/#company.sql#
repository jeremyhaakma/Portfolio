DROP TABLE dependent;
DROP TABLE works_on;
DROP TABLE project;
DROP TABLE dept_locations;
DROP TABLE employee cascade constraints;
DROP TABLE department cascade constraints;

CREATE TABLE department
  (dname        VARCHAR2(15)  NOT NULL UNIQUE,
   dnumber      INT           PRIMARY KEY,
   mgrssn       CHAR(9)       NOT NULL,
   mgrstartdate DATE);

INSERT INTO department VALUES
   ('Research', 5, '333445555', TO_DATE('22-05-1988','DD-MM-YYYY'));
INSERT INTO department VALUES
   ('Administration', 4, '987654321', TO_DATE('01-01-1995','DD-MM-YYYY'));
INSERT INTO department VALUES
   ('Headquarters', 1, '888665555', TO_DATE('19-06-1981','DD-MM-YYYY'));
INSERT INTO department VALUES
   ('Dummies', 0, '111100000', TO_DATE('31-12-2004','DD-MM-YYYY'));


CREATE TABLE employee
  (fname    VARCHAR2(15) NOT NULL,
   minit    CHAR,
   lname    VARCHAR2(15) NOT NULL,
   ssn      CHAR(9)      PRIMARY KEY,
   bdate    DATE,
   address  VARCHAR2(30),
   sex      CHAR,
   salary   NUMBER(6),
   superssn CHAR(9)
      CONSTRAINT superssn_cnst REFERENCES employee(ssn) DISABLE,
   dno      INT          NOT NULL 
      CONSTRAINT dno_cnst REFERENCES department(dnumber) DISABLE);

ALTER TABLE employee ENABLE CONSTRAINT dno_cnst;

INSERT INTO employee VALUES
  ('John','B','Smith','123456789',TO_DATE('09-01-1965','DD-MM-YYYY'),
   '731 Fondren, Houston, TX','M',30000,'333445555',5);
INSERT INTO employee VALUES
  ('Franklin','T','Wong','333445555',TO_DATE('08-12-1955','DD-MM-YYYY'),
   '638 Voss, Houston, TX','M',40000,'888665555',5);
INSERT INTO employee VALUES
  ('Alicia','J','Zelaya','999887777',TO_DATE('19-07-1968','DD-MM-YYYY'),
   '3321 Castle, Spring,TX','F',25000,'987654321',4);
INSERT INTO employee VALUES
  ('Jennifer','S','Wallace','987654321',TO_DATE('20-06-1941','DD-MM-YYYY'),
   '291 Berry, Bellaire, TX','F',43000,'888665555',4);
INSERT INTO employee VALUES
  ('Ramesh','K','Narayan','666884444',TO_DATE('15-09-1962','DD-MM-YYYY'),
   '975 Fire Oak, Humble, TX','M',38000,'333445555',5);
INSERT INTO employee VALUES
  ('Joyce','A','English','453453453',TO_DATE('31-07-1972','DD-MM-YYYY'),
   '5631 Rice, Houston, TX','F',25000,'333445555',5);
INSERT INTO employee VALUES
  ('Ahmad','V','Jabbar','987987987',TO_DATE('29-03-1969','DD-MM-YYYY'),
   '980 Dallas, Houston, TX','M',25000,'987654321',4);
INSERT INTO employee VALUES
  ('James','E','Borg','888665555',TO_DATE('10-11-1937','DD-MM-YYYY'),
   '450 Stone, Houston, TX','M',55000,NULL,1);

ALTER TABLE employee ENABLE CONSTRAINT superssn_cnst;

CREATE TABLE dept_locations
  (dnumber  INT           REFERENCES department(dnumber),
   dlocation VARCHAR2(15),
  PRIMARY KEY(dnumber, dlocation) );

INSERT INTO dept_locations VALUES (1,'Houston');
INSERT INTO dept_locations VALUES (4,'Stafford');
INSERT INTO dept_locations VALUES (5,'Bellaire');
INSERT INTO dept_locations VALUES (5,'Sugarland');
INSERT INTO dept_locations VALUES (5,'Houston');

CREATE TABLE project
  (pname     VARCHAR2(15) NOT NULL UNIQUE,
   pnumber   INT          PRIMARY KEY,
   plocation VARCHAR2(15),
   dnum      INT          NOT NULL REFERENCES department(dnumber));

INSERT INTO project VALUES
   ('ProductX',1,'Bellaire',5);
INSERT INTO project VALUES
   ('ProductY',2,'Sugarland',5);
INSERT INTO project VALUES
   ('ProductZ',3,'Houston',5);
INSERT INTO project VALUES
   ('Computerisation',10,'Stafford',4);
INSERT INTO project VALUES
   ('Reorganisation',20,'Houston',1);
INSERT INTO project VALUES
   ('Newbenefits',30,'Stafford',4);
INSERT INTO project VALUES
   ('NonProject',40,NULL,4);

CREATE TABLE works_on
  (essn   CHAR(9)      REFERENCES employee(ssn),
   pno    INT          REFERENCES project(pnumber),
   hours  NUMBER(4,1)  NOT NULL,
  PRIMARY KEY(essn, pno));

INSERT INTO works_on VALUES ('123456789',1,32.5);
INSERT INTO works_on VALUES ('123456789',2,7.5);
INSERT INTO works_on VALUES ('666884444',3,40.0);
INSERT INTO works_on VALUES ('453453453',1,20.0);
INSERT INTO works_on VALUES ('453453453',2,20.0);
INSERT INTO works_on VALUES ('333445555',2,10.0);
INSERT INTO works_on VALUES ('333445555',3,10.0);
INSERT INTO works_on VALUES ('333445555',10,10.0);
INSERT INTO works_on VALUES ('333445555',20,10.0);
INSERT INTO works_on VALUES ('999887777',30,30.0);
INSERT INTO works_on VALUES ('999887777',10,10.0);
INSERT INTO works_on VALUES ('987987987',10,35.0);
INSERT INTO works_on VALUES ('987987987',30,30.0);
INSERT INTO works_on VALUES ('987654321',30,20.0);
INSERT INTO works_on VALUES ('987654321',20,15.0);
INSERT INTO works_on VALUES ('888665555',20,0.0);

CREATE TABLE dependent
  (essn           CHAR(9)      REFERENCES employee(ssn),
   dependent_name VARCHAR2(15) ,
   sex            CHAR,
   bdate          DATE,
   relationship   VARCHAR(8),
  PRIMARY KEY(essn, dependent_name));

INSERT INTO dependent VALUES
   ('333445555','Alice','F',TO_DATE('05-04-1986','DD-MM-YYYY'),'Daughter');
INSERT INTO dependent VALUES
   ('333445555','Theodore','M',TO_DATE('25-10-1983','DD-MM-YYYY'),'Son');
INSERT INTO dependent VALUES
   ('333445555','Joy','F',TO_DATE('03-05-1958','DD-MM-YYYY'),'Spouse');
INSERT INTO dependent VALUES
   ('987654321','Abner','M',TO_DATE('28-02-1942','DD-MM-YYYY'),'Spouse');
INSERT INTO dependent VALUES
   ('123456789','Michael','M',TO_DATE('04-01-1988','DD-MM-YYYY'),'Son');
INSERT INTO dependent VALUES
   ('123456789','Alice','F',TO_DATE('30-12-1988','DD-MM-YYYY'),'Daughter');
INSERT INTO dependent VALUES
   ('123456789','Elizabeth','F',TO_DATE('05-05-1967','DD-MM-YYYY'),'Spouse');

COMMIT;
