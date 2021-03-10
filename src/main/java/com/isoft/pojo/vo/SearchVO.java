package com.isoft.pojo.vo;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class SearchVO {

	private long pagenum;
	private long pagesize;
	private String a;
}
