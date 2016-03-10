CREATE OR REPLACE TRIGGER tot_loan_trig1
BEFORE INSERT OR UPDATE OR DELETE OF amount ON loan
FOR EACH ROW
BEGIN
        IF INSERTING THEN
          UPDATE bank_branch
            SET total_loan = total_loan + :NEW.amount
              WHERE bank_branch.branchno = :NEW.branchno;
        ELSIF UPDATING THEN
          UPDATE bank_branch
            SET total_loan = total_loan + :NEW.amount - :OLD.amount
              WHERE bank_branch.branchno = :OLD.branchno;
        ELSE -- deleting
          UPDATE bank_branch
            SET total_loan = total_loan - :OLD.amount
              WHERE bank_branch.branchno = :OLD.branchno;
        END IF;
END;
/
