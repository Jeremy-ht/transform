package com.isoft.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVo {
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
    private String categoryid;

    /**
     * 1-发布，0-草稿
     */
    private Integer draft;

    private String cover;

    private Integer top;

    private String categoryname;

    private Integer creator;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String state;

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

    private String username;
    private String phone;
    private String pay;
    private String opentime;
    private Integer indextop;

}
