package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Comment;
import com.isoft.dao.CommentMapper;
import com.isoft.pojo.vo.CommentVo;
import com.isoft.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Page<CommentVo> getCommentList(Page<CommentVo> page, Integer infoid) {
        Page<CommentVo> list = commentMapper.getCommentList(page, infoid);
        return list;
    }

    @Override
    public boolean updCommentState(Integer id, Integer state) {
        return commentMapper.updCommentState(id, state) == 1 ? true : false;
    }

    @Override
    public int countComment() {
        return commentMapper.countComment();
    }
}
