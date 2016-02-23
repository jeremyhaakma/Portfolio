DROP TABLE loan_of;
DROP TABLE account_of;
DROP TABLE customer;
DROP TABLE loan;
DROP TABLE account;
DROP TABLE bank_branch;
DROP TABLE bank;

--Create tables

--BANK TABLE
CREATE TABLE bank
       (bname           VARCHAR2(20)       NOT NULL UNIQUE,
       baddr            VARCHAR2(30),
       routing_code     CHAR(5)            PRIMARY KEY
           CONSTRAINT rc_num_len CHECK (REGEXP_LIKE(routing_code, '[[:digit:]]{5}'))
                    );
--Insert bank values
INSERT INTO bank VALUES (       'Eastpac',           '32 Euston Road',       '57558');
INSERT INTO bank VALUES (       'Bank of Bankerton', '35 Regent Street',     '00128');
INSERT INTO bank VALUES (       'Peoples Bank ',     '1004 Park Lane',       '99147');
COMMIT;

--BANK_BRANCH TABLE
CREATE TABLE bank_branch
       (branch_name         VARCHAR2(20)        NOT NULL,
       branch_addr          VARCHAR(30),
       branchno             CHAR(2)             NOT NULL UNIQUE
           --check to make sure branch number is a 2 digit number
           CONSTRAINT br_num_len CHECK (REGEXP_LIKE(branchno, '[[:digit:]]{2}')),
       routing_code    CHAR(5) REFERENCES bank(routing_code),
           PRIMARY KEY (branchno, routing_code) );

--Insert branch values
INSERT INTO bank_branch VALUES ('Eastpac HQ',        '32 Euston Road',       '78', '57558');
INSERT INTO bank_branch VALUES ('Fleet Branch',      '34 Regent St',          '01', '00128');
INSERT INTO bank_branch VALUES ('Hobo Branch',       '21 Old Kent Road',     '03', '00128');
INSERT INTO bank_branch VALUES ('Trafalgar Branch',  '122 Trafalgar Square', '02', '00128');
INSERT INTO bank_branch VALUES ('Trafalgar Branch',  '123 Trafalgar Square', '26', '99147');
INSERT INTO bank_branch VALUES ('Railroad Branch',   '8 Park Lane',   '27', '99147');
COMMIT;
          
--CUSTOMER TABLE
CREATE TABLE customer
       (ird           CHAR(9)           PRIMARY KEY
           --check that ird is an 8 or 9 digit number
           CONSTRAINT ird_num_len CHECK (REGEXP_LIKE(ird, '[[:digit:]]{8,9}')),
        cname         VARCHAR(20)       NOT NULL,
        caddr         VARCHAR(30),
        phone         CHAR(16)
           --check that phone is a viable phone number
           CONSTRAINT ph_num_check CHECK (REGEXP_LIKE(phone, '+?([[:digit:]]){1,16}'))
        );
--Insert customer values
INSERT INTO customer VALUES('36328378',  'Scott Terrier',    '34 Malborough St',   NULL);
INSERT INTO customer VALUES ('32352342', 'Meghan Terrier',   '34 Malborough St',   '027 509 5789'); 
INSERT INTO customer VALUES('111111112', 'Jane Thimble',     '5 Park Lane',       '278 5606');      
INSERT INTO customer VALUES('900980987', 'Timmy Shoe',       '12 Whitechapel Rd',  '+021 883 1555');
INSERT INTO customer VALUES('27958588',  'Neil Barrow',      '45 Regent St',       '127 123 5432');  
INSERT INTO customer VALUES('33300583',  'Boris Iron',       '11 Coventry Street', '+64 21 3081094');
INSERT INTO customer VALUES('123456781', 'Battleship Jones', '123 Park Lane',      NULL);
INSERT INTO customer VALUES('98723555',  'Otto Mobyl',       '27 Malborough St',       '378 3789');
INSERT INTO customer VALUES('454540454', 'Patty Tops',       '86 Vine Street',     '+65 335 1097');
INSERT INTO customer VALUES('23087920',  'Guy Monopoly',     '1 Park Lane',        '+54 27 111 1111');
 
COMMIT;

--ACCOUNT TABLE        
CREATE TABLE account
       (acctno             CHAR(5)          PRIMARY KEY,
             CONSTRAINT acct_num_check CHECK (REGEXP_LIKE(acctno, '[[:digit:]]{5}')),
       atype               CHAR(10)         NOT NULL
             CONSTRAINT atype_check   CHECK (atype IN ('savings', 'checking', 'investment')),
       balance             NUMBER(18,2)     NOT NULL,
       branchno            CHAR(2),      --    REFERENCES bank_branch(branchno),
       routing_code        CHAR(5),      --    REFERENCES bank_branch(b_routing_code)
             CONSTRAINT account_fkey FOREIGN KEY (branchno, routing_code)
              REFERENCES bank_branch(branchno, routing_code)
       );
--Insert account values (acctno, atype, balance, branch_num, rcode

--Eastpac(57558) accounts    
INSERT INTO account VALUES (   '11111', 'investment', 450123.00,  '78', '57558');
INSERT INTO account VALUES (   '11112', 'checking',   101.54,     '78', '57558');

--Bankerton(00128) accounts
INSERT INTO account VALUES (   '04569', 'savings',    500.00,     '01', '00128');
INSERT INTO account VALUES (   '65445', 'checking',   2000.02,       '03', '00128');
INSERT INTO account VALUES (   '22053', 'checking',   27.59,      '02', '00128');
INSERT INTO account VALUES (   '22054', 'savings',    1000.00,    '02', '00128');
INSERT INTO account VALUES (   '09545', 'savings',    871.99,     '01', '00128');

--Peoples Bank(99147) accounts
INSERT INTO account VALUES (   '10840', 'checking',   217.13,     '27', '99147');
INSERT INTO account VALUES (   '58588', 'savings',    475.08,     '27', '99147');
INSERT INTO account VALUES (   '23346', 'savings',    3098.12,    '26', '99147');
INSERT INTO account VALUES (   '23347', 'investment', 10200.00,   '26', '99147');
INSERT INTO account VALUES (   '90562', 'savings',    950.4,      '26', '99147');
COMMIT;



--LOAN TABLE       
CREATE TABLE loan
       (loanno             CHAR(3)         PRIMARY KEY,
            CONSTRAINT loan_num_check CHECK (REGEXP_LIKE(loanno, '[[:digit:]]{3}')),
       ltype               CHAR(10)
            CONSTRAINT ltype_check CHECK (ltype IN ('car', 'home', 'personal')),
       amount              NUMBER(18,2),
       contract_date       DATE            NOT NULL,
       branchno            CHAR(2),--          REFERENCES bank_branch(branchno),
       routing_code   CHAR(5),--          REFERENCES bank_branch(b_routing_code)
            CONSTRAINT loan_fkey FOREIGN KEY (branchno, routing_code)
             REFERENCES bank_branch(branchno, routing_code)
       );

--Insert loans (number, atype, amount, date, branch, rcode)
--Timmy Shoe savings loan
INSERT INTO loan VALUES('002', 'personal', 400, TO_DATE('18-08-2015', 'dd-mm-yyyy'), '78', '57558');
--Battleship Jones investment loan
INSERT INTO loan VALUES ('354', 'home', 10000.00, TO_DATE('05-03-2001', 'dd-mm-yyyy'), '26', '99147');
--Scott/Meghan joint loan
INSERT INTO loan VALUES ('042', 'car', 2500.00, TO_DATE('12-12-2010', 'dd-mm-yyyy'), '01', '00128');
--Boris loan
INSERT INTO loan VALUES ('012', 'personal', 2000.00, TO_DATE('03-02-2009', 'dd-mm-yyyy'), '03', '00128');

--ACCOUNT_OF TABLE
CREATE TABLE account_of
       (acctno      CHAR(5)       REFERENCES account(acctno),
       ird          CHAR(9)       REFERENCES customer(ird),
       PRIMARY KEY (acctno, ird)
       );

--Insert account-customer relationships
INSERT INTO account_of VALUES ('11111', '23087920');
INSERT INTO account_of VALUES ('11112', '23087920');
INSERT INTO account_of VALUES ('04569', '36328378');
INSERT INTO account_of VALUES ('04569', '32352342');
INSERT INTO account_of VALUES ('65445', '33300583');
INSERT INTO account_of VALUES ('22053', '98723555');
INSERT INTO account_of VALUES ('90562', '111111112');
INSERT INTO account_of VALUES ('23346', '123456781');
INSERT INTO account_of VALUES ('23347', '123456781');
INSERT INTO account_of VALUES ('22054', '27958588');
INSERT INTO account_of VALUES ('09545', '454540454');
INSERT INTO account_of VALUES ('10840', '900980987');
INSERT INTO account_of VALUES ('58588', '900980987');
COMMIT;

--LOAN_OF TABLE
CREATE TABLE loan_of
       (loanno      CHAR(3)        REFERENCES loan(loanno),
       ird         CHAR(9)        REFERENCES customer(ird),
       PRIMARY KEY (loanno, ird)
       );

--Timmy Shoe
INSERT INTO loan_of VALUES ('002', '900980987');
--Battleship Jones
INSERT INTO loan_of VALUES ('354', '123456781');
--Scott joint
INSERT INTO loan_of VALUES ('042', '36328378');
--Meghan joint
INSERT INTO loan_of VALUES ('042', '32352342');
--Boris
INSERT INTO loan_of VALUES ('012', '33300583');





