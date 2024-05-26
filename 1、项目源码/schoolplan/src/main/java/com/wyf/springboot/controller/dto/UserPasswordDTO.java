package com.wyf.springboot.controller.dto;

import lombok.Data;

@Data
public class UserPasswordDTO {
    private String username;
    private String password;
    private String newPassword;
    private String Email;
    private String code;
}
