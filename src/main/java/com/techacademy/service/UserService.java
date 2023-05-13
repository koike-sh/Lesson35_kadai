package com.techacademy.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set; // 追加

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.DailyReport;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;
import com.techacademy.repository.AuthenticationRepository;
import com.techacademy.repository.DailyReportRepository;

@Service
public class UserService {
    private final EmployeeRepository employeeRepository;
    private final AuthenticationRepository authenticationRepository;
    private final DailyReportRepository dailyreportRepository;

    public UserService(EmployeeRepository employeerepository, AuthenticationRepository authenticationrepository, DailyReportRepository dailyreportrepository) {
        this.employeeRepository = employeerepository;
        this.authenticationRepository = authenticationrepository;
        this.dailyreportRepository = dailyreportrepository;
    }

    /** 全件を検索して返す */
    public List<Employee> getEmployeeList() {
        // リポジトリのfindAllメソッドを呼び出す
        return employeeRepository.findAll();
    }

    /** Employeeエンティティのレコード数を返す */
    public int getEmployeeSize() {
        List<Employee> employee = getEmployeeList();
        int employeesize = employee.size();
        return employeesize;
    }

    /** Employeeを1件検索して返す */
    public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).get();
    }

    /** Authenticationを1件検索して返す */
    public com.techacademy.entity.Authentication getAuthentication(String id) {
        return authenticationRepository.findById(id).get();
    }

    /** Employeeの登録を行なう */
    @Transactional
    public Employee saveEmployee(Employee employee) {
        com.techacademy.entity.Authentication auth = employee.getAuthentication();
        auth.setEmployee(employee);
        return employeeRepository.save(employee);
    }

    /** Employeeの削除を行なう */
    @Transactional
    public void deleteEmployee(Set<Integer> idck) {
        for(Integer id : idck) {
            employeeRepository.deleteById(id);
        }
    }
}