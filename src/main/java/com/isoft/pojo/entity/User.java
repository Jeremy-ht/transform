package com.isoft.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 盐
     */
    private String salt;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 登录方式
     */
    private Integer loginway;

    /**
     * 1-有效，0-无效
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer state;

    /**
     * 1-男，0-女
     */
    private String sex;

    private String image;

    private String phone;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatime;

    private String cph;
    private String color;
    private String carimage;
    private Integer jf;

}
