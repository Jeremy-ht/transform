package com.isoft.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Infos;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *


 */
public interface InfosService extends IService<Infos> {

	Page<Infos> getInfoList(long pagenum, long pagesize, Integer id);
}
