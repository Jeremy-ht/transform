package com.isoft.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}

 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Phones implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String image;

    private Integer kucun;

    private double price;

    /**
     * 重量 kg
     */
    private String weight;

    /**
     * 描述内容
     */
    private String content;

    /**
     * 品牌分类
     */
    private Integer categoryid;

    /**
     * 颜色（黑色 白色 蓝色 绿色 红色）
     */
    private String color;

    /**
     * 内存容量（8GB + 256GB 64 128 256）
     */
    private String version;

    /**
     * 产品名称
     */
    private String title;

    /**
     * 上市年份
     */
    private String year;

    /**
     * 无线充电
     */
    private Integer wuxian;

    /**
     * 主屏幕尺寸（英寸）
     */
    private String chicun;

    /**
     * NFC/NFC模式
     */
    private Integer nfc;

    /**
     * 首页  1
     */
    private Integer isshow;

    /**
     * 电池容量 5000mAh
     */
    private String pool;

    /**
     * 首页 四类使用 图片
     */
    private String icon;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer state;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatime;


}
