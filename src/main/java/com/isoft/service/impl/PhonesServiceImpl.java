package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Phones;
import com.isoft.dao.PhonesMapper;
import com.isoft.pojo.vo.FlowersVo;
import com.isoft.pojo.vo.PhonesVo;
import com.isoft.service.PhonesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.isoft.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *


 */
@Service
public class PhonesServiceImpl extends ServiceImpl<PhonesMapper, Phones> implements PhonesService {

	@Autowired
	private PhonesMapper phonesMapper;

	@Override
	public Page<PhonesVo> getSceneryList(long pagenum, long pagesize, Integer categoryId) {
		Page<PhonesVo> page = new Page<>(pagenum, pagesize);
		try {
			Page<PhonesVo> p = phonesMapper.getSceneryList(page, categoryId);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getSceneryList：错误" + e);
			return null;
		}
	}

	@Override
	public ResponseData getSceneryInfo(Integer id) {
		PhonesVo d = phonesMapper.getSceneryInfo(id);
		if (StringUtils.isEmpty(d)) {
			return ResponseData.error().message("获取详情失败！");
		}
		return ResponseData.success().message("获取详情成功！").data("data", d);
	}

	@Override
	public Page<PhonesVo> getSearchList(long pagenum, long pagesize, String searchText) {
		Page<PhonesVo> page = new Page<>(pagenum, pagesize);
		try {
			Page<PhonesVo> p = phonesMapper.getSearchList(page, searchText);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getSearchList：错误" + e);
			return null;
		}
	}
}
