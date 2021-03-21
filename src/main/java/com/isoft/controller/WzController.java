package com.isoft.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.InfosMapper;
import com.isoft.dao.UserMapper;
import com.isoft.dao.WzMapper;
import com.isoft.pojo.entity.Infos;
import com.isoft.pojo.entity.User;
import com.isoft.pojo.entity.Wz;
import com.isoft.pojo.vo.WzVo;
import com.isoft.service.InfosService;
import com.isoft.service.WzService;
import com.isoft.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/wz")
public class WzController {

    @Autowired
    private WzService wzService;

    @Autowired
    private WzMapper wzMapper;

    @Autowired
    private UserMapper userMapper;

    // 添加
    @PostMapping("/addWz")
    public ResponseData addWz(@RequestBody Wz wz) {


        return wzService.save(wz) ? ResponseData.success().message("添加成功！")
                : ResponseData.success().message("添加失败!");
    }


    // 删除
    @GetMapping("/delWz/{id}")
    public ResponseData delWz(@PathVariable("id") Integer id) {
        return wzService.removeById(id) ? ResponseData.success().message("删除成功!")
                : ResponseData.error().message("删除失败!");
    }


    @GetMapping("/updWZ/{userid}/{id}")
    public ResponseData updWZ(@PathVariable("userid") Integer userid,
                              @PathVariable("id") Integer id) {

        User user = userMapper.selectById(userid);
        if (user == null) {
            return ResponseData.error().message("没有此用户!");
        }
        Wz byId = wzService.getById(id);
        userMapper.updJF(userid, user.getJf() - byId.getJf());

        wzMapper.upd(id);

        return wzMapper.upd(id) == 1 ? ResponseData.success().message("成功!")
                : ResponseData.error().message("失败!");
    }


    // 查询 0-所有
    @GetMapping("/getWzList/{userId}")
    public ResponseData getWzList(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
                                  @RequestParam(name = "pagesize", defaultValue = "10", required = false) long pagesize,
                                  @PathVariable("userId") Integer id) {

        Page<WzVo> page = wzService.getInfoList(pagenum, pagesize, id);
        if (page != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("total", page.getTotal());
            map.put("data", page.getRecords());
            return ResponseData.success().message("获取成功！").data(map);
        }
        return ResponseData.error().message("获取失败！");
    }

}

