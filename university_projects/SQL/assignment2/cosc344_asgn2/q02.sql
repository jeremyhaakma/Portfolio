--Query 2
--Give the name and address of every customer who lives on Park Lane

SELECT cname, caddr address
FROM customer
WHERE caddr LIKE '%Park Lane%';
