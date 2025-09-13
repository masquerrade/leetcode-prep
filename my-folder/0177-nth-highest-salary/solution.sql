CREATE OR REPLACE FUNCTION NthHighestSalary(N INT) RETURNS TABLE (Salary INT) AS $$

BEGIN
    --  IF N <= 0 THEN
    --     RETURN;
    -- END IF;
  RETURN QUERY (
    -- Write your PostgreSQL query statement below.
   SELECT DISTINCT g.salary FROM
    (SELECT  e.salary,
    DENSE_RANK() OVER (ORDER BY e.salary DESC) as r
    FROM Employee e) g

    WHERE g.r=N
    
    
      
  );
END;
$$ LANGUAGE plpgsql;
