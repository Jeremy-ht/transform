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
public class CommentVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类id
     */
    private Integer categoryid;

    /**
     * 用户id
     */
    private Integer userid;
    /**
     * 评论正文
     */
    private String commentary;

    /**
     * 1-有效，0-删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer state;

    /**
     * 评论时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatime;

    private String uname;
    private String uimage;
    private String categoryname;
    private String name;
    private String image;


}
