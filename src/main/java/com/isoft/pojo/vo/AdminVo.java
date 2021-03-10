package com.isoft.pojo.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class AdminVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户名
     */
    private String username;


    /**
     * 真实姓名
     */
    private String name;


    /**
     * 联系方式
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String icon;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatime;

    /**
     * 创建人
     */
    private Integer creator;

    private String creatorName;

    /**
     * 1-有效，0-删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer state;

    private Integer sex;

}
