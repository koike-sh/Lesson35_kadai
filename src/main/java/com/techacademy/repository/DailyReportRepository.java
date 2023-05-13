package com.techacademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.DailyReport;
import com.techacademy.entity.Employee;

public interface DailyReportRepository extends JpaRepository<DailyReport, Integer> {
    List<DailyReport> findByEmployee(Employee employee);

}