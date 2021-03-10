package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.DetailMapper;
import com.isoft.pojo.entity.Info;
import com.isoft.dao.InfoMapper;
import com.isoft.pojo.vo.DetailVo;
import com.isoft.pojo.vo.InfoVo;
import com.isoft.service.InfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.isoft.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {
	@Autowired
	private InfoMapper infoMapper;


	@Override
	public ResponseData getInfoDeatilById(Integer id) {
		Info d = infoMapper.getInfoDeatilById(id);
		if (StringUtils.isEmpty(d)) {
			return ResponseData.error().message("获取详情失败！");
		}
		return ResponseData.success().message("获取详情成功！").data("data", d);
	}

	@Override
	public ResponseData getInfoByCategoryId(Integer id) {
		return null;
	}

	@Override
	public Page<InfoVo> getInfoList(long pagenum, long pagesize, Integer id) {
		Page<InfoVo> page = new Page<>(pagenum, pagesize);
		try {
			Page<InfoVo> p = infoMapper.getInfoList(page, id);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getInfoList：错误" + e);
			return null;
		}
	}
}
