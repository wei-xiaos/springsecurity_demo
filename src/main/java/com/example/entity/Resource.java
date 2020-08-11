package com.example.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
@Entity
@Data
@Table(name="tbl_resource")
public class Resource {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Long resourceId;
    @Column(name = "path",length = 1000)
    private String path;

}
