package com.isoft.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.InfoMapper;
import com.isoft.dao.ReservationMapper;
import com.isoft.pojo.entity.Comment;
import com.isoft.pojo.vo.CommentVo;
import com.isoft.service.CommentService;
import com.isoft.service.ReservationService;
import com.isoft.utils.ResponseData;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论管理")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private InfoMapper infoMapper;

    /**
     * 添加评论
     */
    @PostMapping("/addComment")
    public ResponseData addComment(@RequestBody Comment addCommentInfo) {
        // todo 评论内容需要过滤
        if (StringUtils.isBlank(addCommentInfo.getCommentary())) {
            return ResponseData.error().message("评论内容不能为空！");
        }
        addCommentInfo.setCommentary(addCommentInfo.getCommentary().trim());
        // 更新评论状态
        reservationMapper.upd(addCommentInfo.getUserid(), addCommentInfo.getInfoid());
        infoMapper.updateState0(addCommentInfo.getInfoid());
        return commentService.save(addCommentInfo) ? ResponseData.success().message("评论成功！")
                : ResponseData.error().message("评论失败!");
    }

    /**
     * 查询评论列表
     * 前后客户端都需要使用，参考后台展示内容，获取对应评论内容
     * 如果sceneryId == 0 后台展示使用
     */
    @GetMapping("getCommentList/{pagenum}/{pagesize}/{detailId}")
    public ResponseData getCommentList(@PathVariable("pagenum") long pagenum,
                                       @PathVariable("pagesize") long pagesize,
                                       @PathVariable("detailId") Integer detailId) {
        Page<CommentVo> page = new Page<>(pagenum, pagesize);
        page = commentService.getCommentList(page, detailId);
        if (page.getTotal() == 0) {
            return ResponseData.success().message("没有评论记录!");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("data", page.getRecords());
        return ResponseData.success().data(map).message("获取评论列表成功");

    }

    /**
     * 禁用评论展示       评论最好不能删除，，，在添加评论时过滤掉
     */
    @GetMapping("/disableComment/{id}/{state}")
    public ResponseData disableComment(@PathVariable("id") Integer id,
                                       @PathVariable("state") Integer state) {
        if (id == null) {
            return ResponseData.error().message("参数为空！");
        }

        return commentService.updCommentState(id, state) ? ResponseData.success().message("操作成功")
                : ResponseData.error().message("操作失败");
    }


}

