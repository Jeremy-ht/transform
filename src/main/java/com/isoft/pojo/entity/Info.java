package com.isoft.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Info implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer sex;

    private Integer age;

    /**
     * 籍贯
     */
    private String nativeplace;

    /**
     * 婚姻状况
     */
    private String marriage;

    /**
     * 当前状态
     */
    private Integer currentstate;

    private Integer height;

    private Integer weight;

    /**
     * 工作经验
     */
    private Integer workexper;

    private Integer salary;

    private Integer noroom;

    private String introduction;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatime;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer state;

    private Integer creator;

    private String image;

    private Integer categoryid;

    private String phone;
}
