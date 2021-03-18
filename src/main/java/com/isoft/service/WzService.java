package com.isoft.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Wz;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isoft.pojo.vo.WzVo;

/**
 * <p>
 *  服务类
 * </p>
 *

 */
public interface WzService extends IService<Wz> {

	Page<WzVo> getInfoList(long pagenum, long pagesize, Integer id);
}
