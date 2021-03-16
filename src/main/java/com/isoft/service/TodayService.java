package com.isoft.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Today;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *


 */
public interface TodayService extends IService<Today> {

	Page<Today> getInfoList(long pagenum, long pagesize );

	List<Today> getTodayListToday();


}
