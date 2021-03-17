package com.isoft.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.InfosMapper;
import com.isoft.pojo.entity.Infos;
import com.isoft.service.InfosService;
import com.isoft.utils.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/infos")
@Api(tags = "交通咨询")
public class InfosController {

	@Autowired
	private InfosService infosService;

	@Autowired
	private InfosMapper infosMapper;

	// 添加  state==7 保存   state==1发布
	@PostMapping("/addInfo")
	public ResponseData addInfo(@RequestBody Infos infos) {
		return infosService.save(infos) ? ResponseData.success().message("添加成功！")
				: ResponseData.error().message("添加失败!");
	}


	// 删除
	@GetMapping("/delInfo/{id}")
	public ResponseData delInfo(@PathVariable("id") Integer id) {
		return infosMapper.upd(id) == 1 ? ResponseData.success().message("删除成功!")
				: ResponseData.error().message("删除失败!");
	}


	// 发布
	@GetMapping("/pullInfo/{id}/{state}")
	public ResponseData pullInfo(@PathVariable("id") Integer id,
								 @PathVariable("state") Integer state) {

		return infosMapper.pullInfo(id, state, LocalDateTime.now()) == 1 ? ResponseData.success().message("发布成功!")
				: ResponseData.error().message("发布失败!");
	}

	// 查询 1-所有
	@GetMapping("/getInfoList/{categoryId}")
	public ResponseData getInfoList(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
									@RequestParam(name = "pagesize", defaultValue = "10", required = false) long pagesize,
									@PathVariable("categoryId") Integer id) {

		Page<Infos> page = infosService.getInfoList(pagenum, pagesize, id);
		if (page != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("total", page.getTotal());
			map.put("data", page.getRecords());
			return ResponseData.success().message("获取成功！").data(map);
		}
		return ResponseData.error().message("获取失败！");
	}

}

