package com.isoft.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.vo.LoginlogVo;
import com.isoft.service.LoginlogService;
import com.isoft.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/log")
public class LoginlogController {

    @Autowired
    private LoginlogService loginlogService;

    /**
     * 查看系统日志
     */
    @GetMapping("/getLogList")
    public ResponseData getLogList(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
                                     @RequestParam(name = "pagesize", defaultValue = "10", required = false) long pagesize) {
        Page<LoginlogVo> page =loginlogService.getLogList(pagenum, pagesize);
        if (page != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("total", page.getTotal());
            map.put("data", page.getRecords());
            return ResponseData.success().message("获取日志列表成功！").data(map);
        }
        return ResponseData.error().message("获取日志列表失败！");
    }

    @GetMapping("/getUserLogList")
    public ResponseData getUserLogList(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
                                     @RequestParam(name = "pagesize", defaultValue = "10", required = false) long pagesize) {
        Page<LoginlogVo> page =loginlogService.getUserLogList(pagenum, pagesize);
        if (page != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("total", page.getTotal());
            map.put("data", page.getRecords());
            return ResponseData.success().message("获取日志列表成功！").data(map);
        }
        return ResponseData.error().message("获取日志列表失败！");
    }

}

