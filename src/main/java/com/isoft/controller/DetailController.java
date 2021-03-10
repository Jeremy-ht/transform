package com.isoft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Detail;
import com.isoft.pojo.vo.DetailVo;
import com.isoft.pojo.vo.EchartsVo;
import com.isoft.service.DetailService;
import com.isoft.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private DetailService detailService;

    /**
     * 添加景区
     */
    @PostMapping("/addScenery")
    public ResponseData addScenery(@RequestBody Detail detail) {
        return detailService.save(detail) ? ResponseData.success().message("添加成功！")
                : ResponseData.error().message("添加失败!");
    }


    /**
     * 删除景区
     */
    @GetMapping("/delScenery/{id}")
    public ResponseData delScenery(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResponseData.error().message("请求参数不能为空");
        }

        return detailService.removeById(id) ? ResponseData.success().message("删除成功！")
                : ResponseData.error().message("删除失败!");
    }



    /**
     * 弃用   state = 7
     */
    @GetMapping("/disableScenery/{id}")
    public ResponseData disableScenery(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResponseData.error().message("请求参数不能为空");
        }

        Detail detail = new Detail();
        detail.setId(id);
        detail.setState(7);
        return detailService.updateById(detail) ? ResponseData.success().message("设置成功！")
                : ResponseData.error().message("设置失败!");
    }


//    /**
//     * @param pagenum    分页 + 分类 获取
//     * @param pagesize   分页 + 分类 获取
//     * @param categoryId 0-获取全部分类
//     * @param draft
//     * @param creator    0-所有人
//     */
//    @GetMapping("/getSceneryList/{categoryId}/{draft}/{creator}")
//    public ResponseData getSceneryList(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
//                                       @RequestParam(name = "pagesize", defaultValue = "10", required = false) long pagesize,
//                                       @PathVariable("categoryId") Integer categoryId,
//                                       @PathVariable("draft") Integer draft,
//                                       @PathVariable("creator") Integer creator) {
//
//        Page<DetailVo> page = detailService.getSceneryList(pagenum, pagesize, categoryId, draft, creator);
//        if (page != null) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("total", page.getTotal());
//            map.put("data", page.getRecords());
//            return ResponseData.success().message("获取成功！").data(map);
//        }
//        return ResponseData.error().message("获取失败！");
//
//    }

    /**
     * 首页获取数据获取
     * 1  2  3  5
     */
    @GetMapping("/getSceneryIndex")
    public ResponseData getSceneryIndex() {
        QueryWrapper<Detail> wrapper = new QueryWrapper<>();
        wrapper.eq("state", 1);
        wrapper.eq("draft", 1);
        wrapper.eq("top", 1);
        wrapper.in("categoryid", 1, 2, 3, 5);
        wrapper.orderByDesc("releasetime");
        List<Detail> list = detailService.list(wrapper);
        if (list.isEmpty() || StringUtils.isEmpty(list)) {
            return ResponseData.error().message("首页数据失败！");
        }

        Map<String, List<Detail>> map = new HashMap<>();
        List<Detail> l1 = new ArrayList<>();
        List<Detail> l2 = new ArrayList<>();
        List<Detail> l3 = new ArrayList<>();
        List<Detail> l5 = new ArrayList<>();
        list.stream().forEach(detail -> {
            switch (detail.getCategoryid()) {
                case 1:
                    l1.add(detail);
                    break;
                case 2:
                    l2.add(detail);
                    break;
                case 3:
                    l3.add(detail);
                    break;
                case 5:
                    l5.add(detail);
                    break;
                default:
                    break;
            }
        });
        map.put("scenery", l1);
        map.put("food", l2);
        map.put("person", l3);
        map.put("shopping", l5);
        return ResponseData.success().message("获取首页分类成功！").data("data", map);
    }


//    /**
//     * 获取某一固定分类所有detail
//     */
//    @GetMapping("/getSceneryInfo/{id}")
//    public ResponseData getSceneryInfo(@PathVariable("id") Integer id) {
//        return detailService.getSceneryInfo(id);
//    }

    /**
     * 获取轮播图
     */
    @GetMapping("/getrotationList")
    public ResponseData getrotationList() {
        QueryWrapper<Detail> wrapper = new QueryWrapper<>();
        wrapper.eq("state", 1)
                .eq("indextop", 1)
                .eq("draft", 1);
        List<Detail> list = detailService.list(wrapper);
        if (list.isEmpty() || list == null) {
            return ResponseData.error().message("数据获取失败，请刷新重试！");
        }

        return ResponseData.success().message("获取轮播图数据成功！").data("data", list);
    }

    /**
     * 搜索
     */
    @GetMapping("/getSearchContent/{content}")
    public ResponseData getSearchContent(@PathVariable("content") String content) {
        List<Detail> list = detailService.getSearchContent(content);
        if (list.isEmpty() || list == null) {
            return ResponseData.error().message("数据获取失败");
        }
        return ResponseData.success().message("获取数据成功！").data("data", list);

    }


    /**
     * 统计
     */
    @GetMapping("/getEchartsCategory")
    public ResponseData getEchartsCategory() {
        List<EchartsVo> list = detailService.getEchartsCategory();
        if (list.isEmpty() || list == null) {
            return ResponseData.error().message("数据获取失败");
        }

        List<EchartsVo> l = list.stream().
                filter(vo -> !vo.getName().equals("首页轮播")).collect(Collectors.toList());
        return ResponseData.success().message("获取数据成功！").data("data", l);

    }

    /**
     * 统计
     */
    @GetMapping("/getEchartsUser")
    public ResponseData getEchartsUser() {
        List<EchartsVo> list = detailService.getEchartsUser();
        if (list.isEmpty() || list == null) {
            return ResponseData.error().message("数据获取失败");
        }
        List<Integer> l = new ArrayList<>();
        int[] ii = new int[12];
        list.stream().forEach(i ->
            ii[Integer.parseInt(i.getName()) - 1] = i.getValue()
        );
        return ResponseData.success().message("获取数据成功！").data("data", ii);
    }
}

