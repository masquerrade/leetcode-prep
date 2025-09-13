# Write your MySQL query statement below
#What I'm planning to do .
#I'll create a modified table with the extra rank column
#Then will join this column with the other table or use this table 
-- | Column Name  | Type    |
-- +--------------+---------+
-- | id           | int     |
-- | name         | varchar |
-- | salary       | int     |
-- | departmentId | int     |

-- Department

-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | id          | int     |
-- | name        | varchar |

-- +------------+----------+--------+
-- | Department | Employee | Salary |
-- +------------+----------+--------+
-- | IT         | Max      | 90000  |
-- | IT         | Joe      | 85000  |
-- | IT         | Randy    | 85000  |
-- | IT         | Will     | 70000  |
-- | Sales      | Henry    | 80000  |
-- | Sales      | Sam      | 60000  |


SELECT 
d.name AS Department,e.name AS Employee,e.salary as Salary
FROM

( SELECT *, 
DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS rnk
 FROM Employee) e

-- Here what I'm doing is creating a sub table using window function where I'm adding the additional info needed 
-- Because  FROM clause is evaluated first

JOIN Department d

 ON e.departmentId=d.id

 WHERE e.rnk<=3

-- SELECT *, 
-- DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS rnk
--  FROM Employee

