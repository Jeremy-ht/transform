package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Infos;
import com.isoft.pojo.entity.Wz;
import com.isoft.dao.WzMapper;
import com.isoft.service.WzService;
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
public class WzServiceImpl extends ServiceImpl<WzMapper, Wz> implements WzService {

	@Autowired
	private WzMapper wzMapper;


	@Override
	public Page<Wz> getInfoList(long pagenum, long pagesize, Integer id) {
		Page<Wz> page = new Page<>(pagenum, pagesize);
		try {
			Page<Wz> p = wzMapper.getInfoList(page, id);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getInfoList：错误" + e);
			return null;
		}

	}
}
