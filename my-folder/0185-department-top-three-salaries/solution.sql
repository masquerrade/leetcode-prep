-- Write your PostgreSQL query statement below
SELECT Department,Employee,Salary FROM
(SELECT d.name AS Department,e.name as Employee,e.salary as Salary,
DENSE_RANK() OVER (PARTITION BY d.name ORDER BY e.salary DESC) as rank
FROM  Employee e LEFT JOIN Department d ON
e.departmentId=d.id)
WHERE rank<=3
