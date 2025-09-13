# Write your MySQL query statement below
-- SELECT *,MAX(salary)
-- FROM 
-- Department d JOIN Employee e
-- on e.departmentId=d.id
-- GROUP BY e.departmentId

SELECT d.name as Department ,e.name as Employee, salary as Salary
FROM 
(SELECT *,
DENSE_RANK() OVER (PARTITION BY departmentid ORDER BY salary DESC) as r
FROM Employee
) e
JOIN Department d 
on e.departmentId=d.id
WHERE r=1

