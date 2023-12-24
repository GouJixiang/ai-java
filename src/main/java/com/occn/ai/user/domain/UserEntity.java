package com.occn.ai.user.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Table(name = "AI_USER")
@Entity(name = "userEntity")
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -91874678527813456L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ACCOUNT")
    private String account;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "STATUS")
    private int status;



}
