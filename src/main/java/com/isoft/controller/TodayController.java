package com.isoft.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.InfosMapper;
import com.isoft.dao.TodayMapper;
import com.isoft.pojo.entity.Infos;
import com.isoft.pojo.entity.Today;
import com.isoft.service.InfosService;
import com.isoft.service.TodayService;
import com.isoft.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@Controller
@RequestMapping("/today")
public class TodayController {

	@Autowired
	private TodayService todayService;

	@Autowired
	private TodayMapper todayMapper;

	// 添加
	@PostMapping("/addToday")
	public ResponseData addToday(@RequestBody Today today) {
		return todayService.save(today) ? ResponseData.success().message("添加成功！")
				: ResponseData.error().message("添加失败!");
	}


	// 删除
	@GetMapping("/delToday/{id}")
	public ResponseData delToday(@PathVariable("id") Integer id) {
		return todayService.removeById(id) ? ResponseData.success().message("删除成功!")
				: ResponseData.error().message("删除失败!");
	}


	// 查询 1-所有
	@GetMapping("/getTodayList")
	public ResponseData getTodayList(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
									 @RequestParam(name = "pagesize", defaultValue = "10", required = false) long pagesize) {

		Page<Today> page = todayService.getInfoList(pagenum, pagesize);
		if (page != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("total", page.getTotal());
			map.put("data", page.getRecords());
			return ResponseData.success().message("获取成功！").data(map);
		}
		return ResponseData.error().message("获取失败！");
	}

	// 当天
	@GetMapping("/getTodayListToday")
	public ResponseData getTodayListToday() {

		List<Today> page = todayService.getTodayListToday();
		if (page != null) {
			return ResponseData.success().message("获取成功！").data("data", page);
		}
		return ResponseData.error().message("获取失败！");
	}

	// 查询 1-所有
//	@GetMapping("/getTodayListTodayist")
//	public ResponseData getTodayListTodayist(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
//									 @RequestParam(name = "pagesize", defaultValue = "10", required = false) long pagesize) {
//
//		Page<Today> page = todayService.getInfoList(pagenum, pagesize);
//		if (page != null) {
//			Map<String, Object> map = new HashMap<>();
//			map.put("total", page.getTotal());
//			map.put("data", page.getRecords());
//			return ResponseData.success().message("获取成功！").data(map);
//		}
//		return ResponseData.error().message("获取失败！");
//	}


}

