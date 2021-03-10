package com.isoft.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Comment;
import com.isoft.pojo.vo.CommentVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface CommentMapper extends BaseMapper<Comment> {


    Page<CommentVo> getCommentList(@Param("page") Page<CommentVo> page,
								   @Param("infoid") Integer infoid);

    @Update("update comment set state = #{state} where id = #{id}")
    int updCommentState(Integer id, Integer state);

    @Select("SELECT count(1) FROM comment WHERE creatime >= CURDATE() and state = 1")
    int countComment();
}
