package com.techacademy.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.techacademy.entity.DailyReport;
import com.techacademy.entity.Employee;
import com.techacademy.repository.DailyReportRepository;


@Service
public class DailyReportService {
    private final DailyReportRepository DailyReportRepository;

    public DailyReportService(DailyReportRepository repository) {
        this.DailyReportRepository = repository;
    }

    /** 全件を検索して返す */
    public List<DailyReport> getDailyReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return DailyReportRepository.findAll();
    }

    /** 全件を検索してemployee_idで絞り込む */
    public List<DailyReport> getDailyReportList(Employee employee) {
        // リポジトリのfindAllメソッドを呼び出す
        return DailyReportRepository.findByEmployee(employee);
    }

    /** DailyReportエンティティのレコード数を返す */
    public int getDailyReportSize() {
        List<DailyReport> dailyreport = getDailyReportList();
        int dailyreportsize = dailyreport.size();
        return dailyreportsize;
    }

    /** DailyReportエンティティのレコード数をemployee_idで絞り込んで返す */
    public int getDailyReportSize(Employee employee) {
        List<DailyReport> dailyreport = getDailyReportList(employee);
        int dailyreportsize = dailyreport.size();
        return dailyreportsize;
    }

    /** DailyReportの登録を行なう */
    @Transactional
    public DailyReport saveDailyReport(DailyReport dailyreport) {

        return DailyReportRepository.save(dailyreport);
    }

    /** DailyReportを1件検索して返す */
    public com.techacademy.entity.DailyReport getDailyReport(Integer id) {
        return DailyReportRepository.findById(id).get();
    }
}
