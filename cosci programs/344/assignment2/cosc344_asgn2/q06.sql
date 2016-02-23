--Query 6
--List all the accounts from customers who live on Malborough Street

SELECT DISTINCT acctno, c.cname, atype
FROM account, customer c
WHERE  acctno IN
      (SELECT acctno
      FROM account_of ao
      WHERE ao.ird = c.ird AND
      c.caddr LIKE '%Malborough%')
ORDER BY acctno;
