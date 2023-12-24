package com.occn.ai.user.dao;

import lombok.Data;

@Data
public class User {

    private String id;
    private String name;
    private String account;
    private String phone;
    private String password;
    private String email;
    private int status;

}
