package com.techacademy.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

import org.apache.catalina.User;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    /** 権限用の列挙型 */
    public static enum Role {
        一般, 管理者
    }

    /** 社員番号 */
    @Id
    @Column(length = 20, nullable = false)
    private String code;

    /** パスワード */
    @Column(length = 225, nullable = false)
    private String password;

    /** 権限。3桁。列挙型（文字列） */
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    /** 従業員テーブルのID	 */
    @OneToOne
    @JoinColumn(name="employeeId", referencedColumnName="id")
    private Employee employee;
}