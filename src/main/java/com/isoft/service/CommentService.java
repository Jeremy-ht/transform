package com.isoft.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isoft.pojo.vo.CommentVo;


public interface CommentService extends IService<Comment> {

    Page<CommentVo> getCommentList(Page<CommentVo> page, Integer detailId);

    boolean updCommentState(Integer id, Integer state);

    int countComment();
}
