CREATE DATABASE daily_report_system CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
CREATE USER 'repuser'@'%' IDENTIFIED BY 'reppass';
GRANT ALL PRIVILEGES ON *.* to 'repuser'@'%';
