package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.PhonesMapper;
import com.isoft.pojo.entity.Infos;
import com.isoft.dao.InfosMapper;
import com.isoft.pojo.vo.PhonesVo;
import com.isoft.service.InfosService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *


 */
@Service
public class InfosServiceImpl extends ServiceImpl<InfosMapper, Infos> implements InfosService {
	@Autowired
	private InfosMapper infosMapper;



	@Override
	public Page<Infos> getInfoList(long pagenum, long pagesize, Integer id) {
		Page<Infos> page = new Page<>(pagenum, pagesize);
		try {
			Page<Infos> p = infosMapper.getInfoList(page, id);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getInfoList：错误" + e);
			return null;
		}
	}
}
