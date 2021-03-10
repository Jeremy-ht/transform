package com.isoft.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Phones;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isoft.pojo.vo.PhonesVo;
import com.isoft.utils.ResponseData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}

 */
public interface PhonesService extends IService<Phones> {

	Page<PhonesVo> getSceneryList(long pagenum, long pagesize, Integer categoryId);

	ResponseData getSceneryInfo(Integer id);

    Page<PhonesVo> getSearchList(long pagenum, long pagesize, String searchText);
}
