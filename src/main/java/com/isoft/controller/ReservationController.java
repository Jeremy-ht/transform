package com.isoft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.InfoMapper;
import com.isoft.dao.ReservationMapper;
import com.isoft.pojo.entity.Comment;
import com.isoft.pojo.entity.Reservation;
import com.isoft.pojo.vo.AdminVo;
import com.isoft.pojo.vo.ReservationVo;
import com.isoft.pojo.vo.ReservationVo2;
import com.isoft.service.CommentService;
import com.isoft.service.ReservationService;
import com.isoft.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private InfoMapper infoMapper;

    @Autowired
    private CommentService commentService;

    @GetMapping("/goYY/{userid}/{infoid}")
    public ResponseData goYY(@PathVariable("userid") Integer userid,
                             @PathVariable("infoid") Integer infoid) {

        Reservation reservation = new Reservation();
        reservation.setUserid(userid);
        reservation.setInfoid(infoid);
        reservation.setIscomment(0);
        return reservationService.save(reservation) ? ResponseData.success().message("预约成功，请保持手机畅通")
                : ResponseData.error().message("预约失败");
    }

    @GetMapping("/isYY/{userid}/{infoid}")
    public ResponseData isYY(@PathVariable("userid") Integer userid,
                             @PathVariable("infoid") Integer infoid) {

        boolean temp = false;
        if (reservationMapper.isYY(userid, infoid) != null) {
            infoMapper.updateState(infoid);
            temp = true;
        }
        return ResponseData.success().message("").data("data", temp);
    }

    @GetMapping("/getYYinfo/{userid}")
    public ResponseData getYYinfo(@PathVariable("userid") Integer userid) {
        List<ReservationVo> list = reservationMapper.getYYinfo(userid);
        return ResponseData.success().message("获取成功").data("data", list);
    }


    @GetMapping("/getReservationList")
    public ResponseData getReservationList(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
                                     @RequestParam(name = "pagesize", defaultValue = "10", required = false) long pagesize) {
        Page<ReservationVo2> page = reservationService.getReservationList(pagenum, pagesize);
        if (page != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("total", page.getTotal());
            map.put("data", page.getRecords());
            return ResponseData.success().message("获取预约列表成功！").data(map);
        }
        return ResponseData.error().message("获取预约列表失败！");
    }

}

