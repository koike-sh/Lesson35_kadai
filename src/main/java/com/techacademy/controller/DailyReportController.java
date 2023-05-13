package com.techacademy.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.DailyReport;
import com.techacademy.entity.Employee;
import com.techacademy.service.DailyReportService;
import com.techacademy.service.UserDetail;
import com.techacademy.service.UserService;

@Controller
@RequestMapping("dailyreport")
public class DailyReportController {
    private final DailyReportService service;

    public DailyReportController(DailyReportService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getlist(Model model) {

        // 全件検索結果をModelに登録
        model.addAttribute("dailyreportlist", service.getDailyReportList());

        // レコード数をModelに登録
        model.addAttribute("dailyreportsize", service.getDailyReportSize());

        // dailyreport/list.htmlに画面遷移
        return "dailyreport/list";
    }

    /** 詳細画面を表示 */
    @GetMapping("/detail/{id}/")
    public String getdetail(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal UserDetail userdetail) {

    	DailyReport dailyreport = service.getDailyReport(id);
        model.addAttribute("dailyreport", dailyreport);

//    	dailyreport.setEmployee(userdetail.getUser());
    	model.addAttribute("user", userdetail.getUser());

        // 全件検索結果をModelに登録
        // model.addAttribute("employeelist", service.getEmployeeList());

        // employee/detail.htmlに画面遷移
        return "dailyreport/detail";
    }

    /** DailyReport登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute DailyReport dailyreport, Model model, @AuthenticationPrincipal UserDetail userdetail) {
        // 従業員登録画面に遷移
    	dailyreport.setEmployee(userdetail.getUser());
    	model.addAttribute("dailyreport", userdetail.getUser());
        return "dailyreport/register";
    }

    /** DailyReport登録処理 */
    @PostMapping("/register")
    public String postRegister(@Validated DailyReport dailyreport, BindingResult res, Model model, @AuthenticationPrincipal UserDetail userdetail) {
    	dailyreport.setCreatedAt(LocalDateTime.now());
    	dailyreport.setUpdatedAt(LocalDateTime.now());
    	dailyreport.setReportDate(LocalDate.now());
    	dailyreport.setEmployee(userdetail.getUser());

        // Employee登録
        service.saveDailyReport(dailyreport);
        // 一覧画面にリダイレクト
        return "redirect:/dailyreport/list";
    }

    /** DailyReport編集画面を表示 */
    @GetMapping("/update/{id}/")
    public String getdailyreport(@PathVariable("id") Integer id, Model model, DailyReport dailyreport) {
//    	//Employeeエンティティのレコード（id）につき、パスワードのみを空で設定する。その後モデルに追加する。
    	DailyReport report = service.getDailyReport(id);
        // Modelに登録
        model.addAttribute("dailyReport", report);
        // User更新画面に遷移
        return "dailyreport/update";
    }

    /** DailyReport編集処理 */
    @PostMapping("/update/{id}/")
    public String postDailyReport(@PathVariable("id") Integer id, DailyReport dailyreport) {
    	DailyReport report = service.getDailyReport(id);
       	report.setReportDate(dailyreport.getReportDate());
       	report.setTitle(dailyreport.getTitle());
       	report.setContent(dailyreport.getContent());
       	report.setUpdatedAt(LocalDateTime.now());
        // Employee登録
        service.saveDailyReport(report);
        // 一覧画面にリダイレクト
        return "redirect:/dailyreport/list";
    }
}
