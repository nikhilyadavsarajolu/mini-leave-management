-- ==========================
-- Sample Employees
-- ==========================
INSERT INTO employee (id, name, joining_date, leave_balance) VALUES
(1, 'Nikhil Yadav', '2024-08-01', 20),
(2, 'Rohit Sharma', '2024-07-15', 15),
(3, 'Sita Verma', '2024-09-01', 25);

-- ==========================
-- Sample Leave Requests
-- ==========================
INSERT INTO leave_request (id, employee_id, start_date, end_date, status, applied_on) VALUES
(1, 1, '2025-08-20', '2025-08-22', 'Approved', '2025-08-10'),
(2, 2, '2025-08-25', '2025-08-26', 'Pending', '2025-08-12'),
(3, 3, '2025-09-01', '2025-09-03', 'Rejected', '2025-08-15');
