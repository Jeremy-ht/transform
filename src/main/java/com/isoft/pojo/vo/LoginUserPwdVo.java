package com.isoft.pojo.vo;

import lombok.Data;

@Data
public class LoginUserPwdVo {
    private String username;
    private String password;

    private String newPassword;
}
