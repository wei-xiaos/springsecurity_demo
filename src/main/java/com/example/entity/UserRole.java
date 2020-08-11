package com.example.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
@Entity
@Data
@Table(name="tbl_user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_role_id")
    private Long userRoleId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="role_id")
    private Long roleId;
}
