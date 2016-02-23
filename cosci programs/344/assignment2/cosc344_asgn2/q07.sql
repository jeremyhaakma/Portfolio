--Query 7
--Count the number of accounts in each branch

SELECT b.routing_code AS Bank, bb.branchno, b.bname, bb.branch_name, COUNT(*) AS Num_Acc
FROM account a, bank_branch bb, bank b
WHERE b.routing_code = bb.routing_code AND
a.branchno IN bb.branchno 
GROUP BY b.bname, b.routing_code, bb.branchno, bb.branch_name
ORDER BY COUNT(*);
