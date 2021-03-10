package com.isoft.pojo.vo;

import com.isoft.pojo.entity.Info;
import com.isoft.pojo.entity.Skills;
import lombok.Data;

import java.util.List;

@Data
public class InfoVo extends Info {

	private List<Skills> skillName;

	private String categoryname;
	private String username;

}
