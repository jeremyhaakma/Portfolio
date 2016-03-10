--Query 4
-- Print the name and sum of each branch whose accounts sum to
-- less than $10,000.

SELECT  branch_name, SUM(balance)
FROM bank_branch b, account a
WHERE b.branchno = a.branchno
GROUP BY b.branch_name
HAVING SUM(balance) < 10000
ORDER BY SUM(balance);
