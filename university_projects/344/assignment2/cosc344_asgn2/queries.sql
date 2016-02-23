--Query 1
-- List the customer name, bank name and loan amount for every customer who has
-- taken out a loan
SELECT cname, bname, amount
FROM customer c, bank b, loan l, loan_of lo
WHERE c.ird = lo.ird AND
      b.routing_code = l.routing_code AND
      l.loanno = lo.loanno
ORDER BY cname;

--Query 2
-- Give the name and address of every customer who lives on Park Lane
SELECT cname, caddr address
FROM customer
WHERE caddr LIKE '%Park Lane%';

--Query 3
-- Give the contract date, amount and loan type for each loan taken out from
-- the Bank of Bankerton
SELECT ltype, amount, TO_DATE(contract_date, 'dd-mon-yyyy') AS loan_date
FROM loan
ORDER BY contract_date;

--Query 4
-- Print the name and sum of each branch whose accounts sum to
-- less than $10,000.
SELECT  branch_name, SUM(balance)
FROM bank_branch b, account a
WHERE b.branchno = a.branchno
GROUP BY b.branch_name
HAVING SUM(balance) < 10000
ORDER BY SUM(balance);

--Query 5
-- List the customers who have accounts with more than $10,000 in them
SELECT cname, ird
FROM customer
WHERE ird IN
      (SELECT ird FROM account a, account_of ao
      WHERE a.acctno = ao.acctno
      GROUP BY ird
      HAVING SUM(balance) > 10000);

--Query 6
-- List all the accounts from customers who live on Malborough Street
SELECT DISTINCT acctno, c.cname, atype
FROM account, customer c
WHERE  acctno IN
      (SELECT acctno
      FROM account_of ao
      WHERE ao.ird = c.ird AND
      c.caddr LIKE '%Malborough%')
ORDER BY acctno;


--Query 7
-- Count the number of accounts in each branch
SELECT b.routing_code AS Bank, bb.branchno, b.bname, bb.branch_name, COUNT(*) AS Num_Acc
FROM account a, bank_branch bb, bank b
WHERE b.routing_code = bb.routing_code AND
a.branchno IN bb.branchno 
GROUP BY b.bname, b.routing_code, bb.branchno, bb.branch_name
ORDER BY COUNT(*);

