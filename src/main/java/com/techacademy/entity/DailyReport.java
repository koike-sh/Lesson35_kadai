package com.techacademy.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove; // 追加
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional; // 追加


import lombok.Data;

@Data
@Entity
@Table(name = "report")
public class DailyReport {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 日報の日付*/
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportDate;

    /** タイトル */
    @Column(length = 225, nullable = false)
    private String title;

    /** 内容 */
    @Column(nullable = false)
    @Type(type="text")
    private String content;

    /** 従業員テーブルのID	 */
    @ManyToOne
    @JoinColumn(name="employeeId", referencedColumnName="id")
    private Employee employee;

    /** 登録日時 */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /** 更新日時 */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /** レコードが削除される前に行なう処理 */
//    @PreRemove
//    @Transactional
//    private void preRemove() {
//        // 認証エンティティからuserを切り離す
//        if (authentication!=null) {
//            authentication.setEmployee(null);
//        }
//    }


}