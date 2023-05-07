package com.techacademy.controller;

import java.time.LocalDateTime;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // 追加
import org.springframework.web.bind.annotation.PathVariable; // 追加
import org.springframework.web.bind.annotation.PostMapping; // 追加
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee; // 追加
import com.techacademy.service.UserService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeController(UserService service) {
        this.service = service;
    }


    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getlist(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("employeelist", service.getEmployeeList());

        // レコード数をModelに登録
        model.addAttribute("employeesize", service.getEmployeeSize());

        // employee/list.htmlに画面遷移
        return "employee/list";
    }

    /** 詳細画面を表示 */
    @GetMapping("/detail/{id}/")
    public String getdetail(@PathVariable("id") Integer id, Model model) {

        Employee emp = service.getEmployee(id);
        model.addAttribute("employee", emp);
        // 全件検索結果をModelに登録
        // model.addAttribute("employeelist", service.getEmployeeList());

        // employee/detail.htmlに画面遷移
        return "employee/detail";
    }

    /** Employee登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        // 従業員登録画面に遷移
        return "employee/register";
    }

    /** Employee登録処理 */
    @PostMapping("/register")
    public String postRegister(@Validated Employee employee, BindingResult res, Model model) {
    	employee.setCreatedAt(LocalDateTime.now());
    	employee.setUpdatedAt(LocalDateTime.now());
    	employee.setDeleteFlag(0);
       	employee.getAuthentication().setPassword(passwordEncoder.encode(employee.getAuthentication().getPassword()));

        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** Employee更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String getEmployee(@PathVariable("id") Integer id, Model model, Employee employee) {
    	//Employeeエンティティのレコード（id）につき、パスワードのみを空で設定する。その後モデルに追加する。
        Employee emp = service.getEmployee(id);
        emp.getAuthentication().setPassword("");
        // Modelに登録
        model.addAttribute("employee", emp);
        // User更新画面に遷移
        return "employee/update";
    }

    /** Employee更新処理 */
    @PostMapping("/update/{id}/")
    public String postEmployee(@PathVariable("id") Integer id, Employee employee) {
       	Employee emp = service.getEmployee(id);
       	emp.setName(employee.getName());
       	emp.getAuthentication().setPassword(passwordEncoder.encode(employee.getAuthentication().getPassword()));
       	emp.getAuthentication().setRole(employee.getAuthentication().getRole());
       	emp.setUpdatedAt(LocalDateTime.now());
        // Employee登録
        service.saveEmployee(emp);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** deleteFlag更新処理 */
    @GetMapping("/list/{id}/")
    public String updateDeleteFlag(@PathVariable("id") Integer id, Model model, Employee employee) {
        Employee emp = service.getEmployee(id);
        emp.setDeleteFlag(1);
        // Employee登録
        service.saveEmployee(emp);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
}