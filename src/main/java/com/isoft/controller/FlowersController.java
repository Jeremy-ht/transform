package com.isoft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.FlowersMapper;
import com.isoft.pojo.entity.Detail;
import com.isoft.pojo.entity.Flowers;
import com.isoft.pojo.vo.DetailVo;
import com.isoft.pojo.vo.FlowersVo;
import com.isoft.service.DetailService;
import com.isoft.service.FlowersService;
import com.isoft.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/flowers")
public class FlowersController {

    @Autowired
    private DetailService detailService;
    @Autowired
    private FlowersService flowersService;
    @Autowired
    private FlowersMapper flowersMapper;

    /**
     * 添加
     */
    @PostMapping("/addScenery")
    public ResponseData addScenery(@RequestBody Flowers flowers) {
        return flowersService.save(flowers) ? ResponseData.success().message("添加成功！")
                : ResponseData.error().message("添加失败!");
    }


    /**
     * 删除
     */
    @GetMapping("/delScenery/{id}")
    public ResponseData delScenery(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResponseData.error().message("参数不能为空");
        }

        return flowersMapper.removeByIdss(id) ? ResponseData.success().message("删除成功！")
                : ResponseData.error().message("删除失败!");
    }

    /**
     * 发布
     */
    @GetMapping("/pullScenery/{id}/{state}")
    public ResponseData pullScenery(@PathVariable("id") Integer id,@PathVariable("state") Integer state) {
        if (id == null) {
            return ResponseData.error().message("请求参数不能为空");
        }

        return detailService.pullScenery(id, state) ? ResponseData.success().message("成功！")
                : ResponseData.error().message("失败!");
    }


    /**
     * @param pagenum    分页 + 分类 获取
     * @param pagesize   分页 + 分类 获取
     * @param categoryId 0-获取全部分类
     */
    @GetMapping("/getSceneryList/{categoryId}")
    public ResponseData getSceneryList(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
                                       @RequestParam(name = "pagesize", defaultValue = "10", required = false) long pagesize,
                                       @PathVariable("categoryId") Integer categoryId) {

        Page<FlowersVo> page = detailService.getSceneryList(pagenum, pagesize, categoryId);
        if (page != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("total", page.getTotal());
            map.put("data", page.getRecords());
            return ResponseData.success().message("获取成功！").data(map);
        }
        return ResponseData.error().message("获取失败！");

    }

    /**
     * 首页获取数据获取
     */
    @GetMapping("/getSceneryIndex")
    public ResponseData getSceneryIndex() {

        // 新品
        List<Flowers> li1 = flowersMapper.newFlower();

        // 热销
        List<Flowers> li2 = flowersMapper.newFlower();

        // 礼盒
        List<Flowers> li3 = flowersMapper.newFlower3();
        Map<String, List<Flowers>> map = new HashMap<>();
        map.put("new", li1);
        map.put("feature", li2);
        map.put("box", li3);
        return ResponseData.success().message("获取数据成功！").data("data", map);
    }


    /**
     * 获取详情
     */
    @GetMapping("/getSceneryInfo/{id}")
    public ResponseData getSceneryInfo(@PathVariable("id") Integer id) {
        return detailService.getSceneryInfo(id);
    }


    /**
     * 获取分类名称
     */
    @GetMapping("/getSceneryListByCate/{id}")
    public ResponseData getSceneryListByCate(@PathVariable("id") Integer id) {
        String name = flowersMapper.getSceneryListByCate(id);
        return ResponseData.success().message("获取数据成功！").data("data", name);
    }

}

