package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.DailyReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("/")
public class TopPageController {
    private final DailyReportService service;

    public TopPageController(DailyReportService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/")
    public String gettoppage(Model model, @AuthenticationPrincipal UserDetail userdetail) {
        // 全件検索結果をModelに登録
        model.addAttribute("dailyreportlist", service.getDailyReportList(userdetail.getUser()));

        // レコード数をModelに登録
        model.addAttribute("dailyreportsize", service.getDailyReportSize(userdetail.getUser()));

        // /toppage.htmlに画面遷移
        return "toppage";
    }


}
