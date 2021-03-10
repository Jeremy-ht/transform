package com.isoft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.CategoryMapper;
import com.isoft.dao.PhonesMapper;
import com.isoft.pojo.entity.Category;
import com.isoft.pojo.entity.Phones;
import com.isoft.pojo.vo.ImageVo;
import com.isoft.pojo.vo.PhonesVo;
import com.isoft.service.CategoryService;
import com.isoft.service.PhonesService;
import com.isoft.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 */
@RestController
@RequestMapping("/phones")
public class PhonesController {

    @Autowired
    private PhonesService phonesService;
    @Autowired
    private PhonesMapper phonesMapper;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 添加
     */
    @PostMapping("/addScenery")
    public ResponseData addScenery(@RequestBody Phones phones) {
        return phonesService.save(phones) ? ResponseData.success().message("添加成功！")
                : ResponseData.error().message("添加失败!");
    }

    /**
     * 删除0 下架3 上架1
     */
    @GetMapping("/pullScenery/{id}/{state}")
    public ResponseData delScenery(@PathVariable("id") Integer id,
                                   @PathVariable("state") Integer state) {

        if (id == null) {
            return ResponseData.error().message("参数不能为空");
        }

        return phonesMapper.pullScenery(id, state) == 1 ? ResponseData.success().message("成功！")
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
        if (categoryId == 777) {
            Category c = categoryMapper.selectOne1();
            categoryId = c.getId();
        }

        Page<PhonesVo> page = phonesService.getSceneryList(pagenum, pagesize, categoryId);
        if (page != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("total", page.getTotal());
            map.put("data", page.getRecords());
            return ResponseData.success().message("获取成功！").data(map);
        }
        return ResponseData.error().message("获取失败！");
    }

    /**
     * 首页四张图片获取
     */
    @GetMapping("/getFourIcon")
    public ResponseData getFourIcon() {

        List<PhonesVo> li1 = phonesMapper.getFourIcon();
        return ResponseData.success().message("获取数据成功！").data("data", li1);
    }


    /**
     * 首页三大分类获取
     */
    @GetMapping("/getSceneryIndex")
    public ResponseData getSceneryIndex() {

        List<Category> list = categoryService.list(new QueryWrapper<Category>().eq("creator", 1)
                .eq("state", 1));

        Map<String, List<Phones>> map = new HashMap<>();

        for (Category category : list) {
            Integer id = category.getId();

            List<Phones> l = phonesService.list(new QueryWrapper<Phones>().eq("state", 1)
                    .eq("categoryid", id).orderByDesc("creatime"));

            map.put(category.getCategoryname(), l);
        }
        return ResponseData.success().message("获取数据成功！").data("data", map);
    }


    /**
     * 获取详情
     */
    @GetMapping("/getSceneryInfo/{id}")
    public ResponseData getPhoneInfo(@PathVariable("id") Integer id) {
        return phonesService.getSceneryInfo(id);
    }


    /**
     * 获取分类名称
     */
    @GetMapping("/getSceneryListByCate/{id}")
    public ResponseData getSceneryListByCate(@PathVariable("id") Integer id) {
        String name = phonesMapper.getSceneryListByCate(id);
        return ResponseData.success().message("获取数据成功！").data("data", name);
    }


    /**
     * 添加到首页显示
     */
    @PostMapping("/addIndexShow")
    public ResponseData addIndexShow(@RequestBody ImageVo imageVo) {
        List<Phones> list = phonesService.list(new QueryWrapper<Phones>()
                .eq("state", 1)
                .isNotNull("isshow")
                .orderByAsc("isshow"));
        if (list.isEmpty()) {
            int ii = phonesMapper.updIsshowById2(imageVo.getId(), imageVo.getImage());
            return ResponseData.success().message("成功！");

        } else if (list.size() == 4) {
            List<Phones> collect = list.stream().limit(1).collect(Collectors.toList());
            Phones phones = collect.get(0);
            int i = phonesMapper.updIsshowById(phones.getId());

            List<Phones> list2 = phonesService.list(new QueryWrapper<Phones>()
                    .eq("state", 1)
                    .isNotNull("isshow")
                    .orderByDesc("isshow")).stream().limit(1).collect(Collectors.toList());
            Integer isshow = list2.get(0).getIsshow();
            int iii = phonesMapper.updIsshowById3(imageVo.getId(), isshow + 1, imageVo.getImage());
            return ResponseData.success().message("成功！");


        } else if (list.size() < 4) {
            List<Phones> list2 = phonesService.list(new QueryWrapper<Phones>()
                    .eq("state", 1)
                    .isNotNull("isshow")
                    .orderByDesc("isshow")).stream().limit(1).collect(Collectors.toList());
            Integer isshow = list2.get(0).getIsshow();
            int iii = phonesMapper.updIsshowById3(imageVo.getId(), isshow + 1, imageVo.getImage());
            return ResponseData.success().message("成功！");

        }

        return ResponseData.error().message("失败！");

    }


    /**
     * 排行榜
     */
    @GetMapping("/getInfoList6/{id}")
    public ResponseData getInfoList6(@PathVariable("id") Integer id) {
        Phones phones = phonesService.getById(id);
        Integer categoryid = phones.getCategoryid();

        List<Phones> list = phonesMapper.getInfoList6(categoryid);

        if (list.size() == 6) {
            return ResponseData.success().message("获取数据成功！").data("data", list);
        }

        List<Integer> strList = new ArrayList<>();
        List<Phones> list1 = null;
        if (list.size() == 0) {
            list1 = phonesService.list(new QueryWrapper<Phones>().
                    eq("state", 1)
                    .eq("categoryid", categoryid))
                    .stream().limit(6)
                    .collect(Collectors.toList());
        } else {
            for (Phones phonesVo : list) {
                strList.add(phonesVo.getId());
            }

            list1 = phonesService.list(new QueryWrapper<Phones>().
                    eq("state", 1)
                    .eq("categoryid", categoryid)
                    .notIn("id", strList))
                    .stream().limit(6 - list.size())
                    .collect(Collectors.toList());
        }


        list.addAll(list1);

        return ResponseData.success().message("获取数据成功！").data("data", list);
    }


}

