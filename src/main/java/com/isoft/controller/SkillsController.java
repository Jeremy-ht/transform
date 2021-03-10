package com.isoft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isoft.pojo.entity.Detail;
import com.isoft.pojo.entity.Skills;
import com.isoft.service.DetailService;
import com.isoft.service.SkillsService;
import com.isoft.utils.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;


@RestController
@RequestMapping("/skills")
@Api("技能管理")
public class SkillsController {
	@Autowired
	private SkillsService skillsService;

	/**
	 * 添加家政人员技能
	 */
	@PostMapping("/addSkills")
	public ResponseData addSkills(@RequestBody Skills skills) {
		return skillsService.save(skills) ? ResponseData.success().message("添加成功！")
				: ResponseData.error().message("添加失败!");
	}


	/**
	 * 删除技能
	 */
	@GetMapping("/delSkills/{infoid}/{skillid}")
	public ResponseData delSkills(@PathVariable("infoid") Integer infoid,
								  @PathVariable("skillid") Integer skillid) {
		if (infoid == null || skillid == null) {
			return ResponseData.error().message("请求参数不能为空");
		}

		return skillsService.remove(new QueryWrapper<Skills>().eq("infoid", infoid)
				.eq("skillid", skillid)) ?
				ResponseData.success().message("删除成功！") :
				ResponseData.error().message("删除失败!");
	}

}

