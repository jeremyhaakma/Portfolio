--Query 5
--List the customers who have accounts with more than $10,000 in them

SELECT cname, ird
FROM customer
WHERE ird IN
      (SELECT ird FROM account a, account_of ao
      WHERE a.acctno = ao.acctno
      GROUP BY ird
      HAVING SUM(balance) > 10000);

