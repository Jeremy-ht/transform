package com.isoft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.InfoMapper;
import com.isoft.pojo.entity.Detail;
import com.isoft.pojo.entity.Info;
import com.isoft.pojo.entity.Skills;
import com.isoft.pojo.entity.User;
import com.isoft.pojo.vo.DetailVo;
import com.isoft.pojo.vo.EchartsVo;
import com.isoft.pojo.vo.InfoVo;
import com.isoft.service.DetailService;
import com.isoft.service.InfoService;
import com.isoft.service.SkillsService;
import com.isoft.service.UserService;
import com.isoft.utils.ResponseData;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/info")
@Api(tags = "家政人员管理")
public class InfoController {

	@Autowired
	private InfoService infoService;
	@Autowired
	private SkillsService skillsService;
	@Autowired
	private DetailService detailService;
	@Autowired
	private UserService userService;
	@Autowired
	private InfoMapper infoMapper;


	/**
	 * 添加家政人员
	 */
	@PostMapping("/addInfo")
	public ResponseData addInfo(@RequestBody Info info) {

		return infoService.save(info) ? ResponseData.success().message("添加成功")
				: ResponseData.error().message("添加失败");
	}


	/**
	 * 删除家政人员
	 */
	@GetMapping("/delInfo/{id}")
	public ResponseData delInfo(@PathVariable("id") Integer id) {
		if (id == null) {
			return ResponseData.error().message("请求参数不能为空");
		}

		return infoService.removeById(id) ? ResponseData.success().message("删除成功")
				: ResponseData.error().message("删除失败");
	}


	/**
	 * 获取家政人员具体信息
	 */
	@GetMapping("/getInfoDeatilById/{id}")
	public ResponseData getInfoDeatilById(@PathVariable("id") Integer id) {

		Info info = infoService.getById(id);
		if (info == null) {
			return ResponseData.error().message("没有查询到该家政人员信息，请刷新再试");
		}

		List<Skills> skillsname = skillsService.list(new QueryWrapper<Skills>().eq("infoid", id));
		Map<String, Object> map = new HashMap<>();
		map.put("info", info);
		map.put("skillsname", skillsname);
		return ResponseData.success().data(map);
	}

	/**
	 *
	 * @param pagenum
	 * @param pagesize
	 * @param id 分类id
	 * @return
	 */
	@GetMapping("/getInfoList/{id}")
	public ResponseData getInfoList(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
									   @RequestParam(name = "pagesize", defaultValue = "100", required = false) long pagesize,
									   @PathVariable("id") Integer id) {

		Page<InfoVo> page = infoService.getInfoList(pagenum, pagesize, id);
		if (page != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("total", page.getTotal());
			map.put("data", page.getRecords());
			return ResponseData.success().message("获取成功！").data(map);
		}
		return ResponseData.error().message("获取失败！");

	}

	@GetMapping("/getTopList")
	public ResponseData getTopList() {

		List<InfoVo> list = infoMapper.getTopList();
		if (list != null || !list.isEmpty()) {
			if (list.size() < 4){
				List<InfoVo> list2 = infoMapper.getTopListBy(4-list.size());
				list.addAll(list2);
			}
			return ResponseData.success().message("获取成功！").data("data",list);
		}
		return ResponseData.error().message("获取失败！");

	}


	/**
	 * 统计 预约
	 */
	@GetMapping("/getEchartsYY")
	public ResponseData getEchartsYY() {
		List<EchartsVo> list = detailService.getEchartsYY();
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

	/**
	 * 统计 人员
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

	/**
	 * 统计 家政
	 */
	@GetMapping("/getEchartsInfo")
	public ResponseData getEchartsInfo() {
		List<EchartsVo> list = detailService.getEchartsInfo();
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

	/**
	 * 统计 家政
	 */
	@GetMapping("/getEchartsSex")
	public ResponseData getEchartsSex() {
		int sex0 = userService.count(new QueryWrapper<User>()
				.eq("sex", 0));

		int sex1 = userService.count(new QueryWrapper<User>()
				.eq("sex", 1));

		List<EchartsVo> list = new ArrayList<>();
		EchartsVo echartsVo = new EchartsVo();
		echartsVo.setName("男");
		echartsVo.setValue(sex1);

		EchartsVo echartsVo1 = new EchartsVo();
		echartsVo1.setName("女");
		echartsVo1.setValue(sex0);

		list.add(echartsVo);
		list.add(echartsVo1);

		return ResponseData.success().message("获取数据成功！").data("data", list);
	}

}

