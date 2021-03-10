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
public class CategoryVo implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String categoryname;

    /**
     * 1-有效，0-删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer state;

    private Integer creator;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatime;

    private String username;
}
