package com.isoft.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.CategoryMapper;
import com.isoft.pojo.entity.Category;
import com.isoft.pojo.vo.CategoryVo;
import com.isoft.service.CategoryService;
import com.isoft.utils.ResponseData;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
@Api("分类")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryMapper categoryMapper;

	// 添加分类
	@PostMapping("/addCategory")
	public ResponseData addCategory(@RequestBody Category addCateInfo) {
		if (StringUtils.isBlank(addCateInfo.getCategoryname())) {
			return ResponseData.error().message("分类名称不能为空！");
		}
		return categoryService.save(addCateInfo) ? ResponseData.success().message("分类添加成功！")
				: ResponseData.error().message("分类添加失败!");

	}

	// 删除分类
	@GetMapping("/delCategory/{categoryId}")
	public ResponseData delCategory(@PathVariable("categoryId") Integer categoryId) {
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
//        for (Integer l : list) {
//            if (categoryId == l) {
//                return ResponseData.error().message("禁止删除固定分类！");
//            }
//        }
		return categoryService.removeById(categoryId) ? ResponseData.success().message("分类删除成功！")
				: ResponseData.error().message("分类删除失败!");

	}


	@GetMapping("/getCategoryById/{id}")
	public ResponseData getCategoryById(@PathVariable("id") Integer categoryId) {
		Category category = categoryService.getById(categoryId);
		if (category == null) {
			return ResponseData.error().message("获取分类失败!");
		}

		return ResponseData.success().message("获取分类成功！").data("data", category);

	}

	@PostMapping("/updCategory")
	public ResponseData updCategory(@RequestBody Category updCateInfo) {
		if (StringUtils.isBlank(updCateInfo.getCategoryname())) {
			return ResponseData.error().message("分类名称不能为空！");
		}
		return categoryService.updateById(updCateInfo) ? ResponseData.success().message("分类修改成功！")
				: ResponseData.error().message("分类修改失败!");

	}

	@GetMapping("/getCategoryList")
	public ResponseData getCategoryList(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
										@RequestParam(name = "pagesize", defaultValue = "10", required = false) long pagesize) {
		Page<CategoryVo> page = categoryService.getCateList(pagenum, pagesize);
		if (page != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("total", page.getTotal());
			map.put("data", page.getRecords());
			return ResponseData.success().message("获取分类列表成功！").data(map);
		}
		return ResponseData.error().message("获取分类列表失败！");
	}


	/**
	 * 展示 1
	 */
	@GetMapping("/pullCategory/{id}/{state}")
	public ResponseData pullCategory(@PathVariable("id") Integer id,
									 @PathVariable("state") Integer state) {
		if (id == null) {
			return ResponseData.error().message("参数不能为空");
		}

		return categoryMapper.pullScenery(id, state) == 1 ? ResponseData.success().message("成功！")
				: ResponseData.error().message("失败!");
	}




}

