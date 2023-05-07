package com.techacademy.entity;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove; // 追加
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional; // 追加

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
@Where(clause = "delete_flag = 0")
public class Employee {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 氏名。20桁。null不許可 */
    @Column(length = 20, nullable = false)
    private String name;

    /** 削除フラグ */
    @Column(nullable = false)
    private Integer	deleteFlag;

    /** 登録日時 */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /** 更新日時 */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Authentication authentication;

    /** レコードが削除される前に行なう処理 */
    @PreRemove
    @Transactional
    private void preRemove() {
        // 認証エンティティからuserを切り離す
        if (authentication!=null) {
            authentication.setEmployee(null);
        }
    }


}