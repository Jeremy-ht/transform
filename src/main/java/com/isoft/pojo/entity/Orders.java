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


 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Orders implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单当前状态
     */
    private Integer type;

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 鲜花ID
     */
    private Integer flowerid;

    /**
     * 收件人名字
     */
    private String name;

    /**
     * 收件人联系方式
     */
    private String phone;

    /**
     * 收件人地址
     */
    private String address;

    /**
     * 订单总价
     */
    private String price;

    /**
     * 鲜花购买量
     */
    private String amount;

    /**
     * 订单备注
     */
    private String note;

    /**
     * 下单时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatime;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer state;


}
