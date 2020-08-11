package com.example.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
@Entity
@Data
@Table(name="tbl_role_resource")
public class RoleResource {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="role_resource_id")
    private Long userResourceId;
    @Column(name="role_id")
    private Long roleId;
    @Column(name="resource_id")
    private Long resourceId;
}
