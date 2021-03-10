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
public class Flowers implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    /**
     * 描述内容
     */
    private String content;

    /**
     * 产地
     */
    private String origin;

    private String image;

    private Integer kucun;

    private Integer price;

    private Integer categoryid;

    /**
     * 鲜花花语
     */
    private String description;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer state;

    /**
     * 新品上市 字段
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatime;

    private String isshow;
}
