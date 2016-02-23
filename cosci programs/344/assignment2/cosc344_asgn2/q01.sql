--Query 1
--List the customer name, bank name and loan amount for every customer who has
--taken out a loan

SELECT cname, bname, amount
FROM customer c, bank b, loan l, loan_of lo
WHERE c.ird = lo.ird AND
      b.routing_code = l.routing_code AND
      l.loanno = lo.loanno
ORDER BY cname;
