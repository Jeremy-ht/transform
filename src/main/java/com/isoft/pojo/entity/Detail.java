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
@TableName("detail")
public class Detail implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 正文介绍
     */
    private String introduction;

    /**
     * 地点   路线
     */
    private String gopath;

    /**
     * 特色内容
     */
    private String feature;

    /**
     * 分类id
     */
    private Integer categoryid;

    /**
     * 1-发布，0-草稿
     */
    private Integer draft;

    private String cover;

    private Integer top;

    private Integer indextop;

    private Integer creator;

    /**
     * 1-发布, 7-弃用
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer state;

    /**
     * 发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime releasetime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatime;

    private String phone;
    private String pay;
    private String opentime;
}
