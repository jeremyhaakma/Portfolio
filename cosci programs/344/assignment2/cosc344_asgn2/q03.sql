--Query 3
--Give the contract date, amount and loan type for each loan taken out from
--the Bank of Bankerton

SELECT ltype, amount, TO_DATE(contract_date, 'dd-mon-yyyy') AS loan_date
FROM loan
ORDER BY contract_date;
