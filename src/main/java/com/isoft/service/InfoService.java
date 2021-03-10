package com.isoft.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Info;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isoft.pojo.vo.InfoVo;
import com.isoft.utils.ResponseData;


public interface InfoService extends IService<Info> {

	ResponseData getInfoDeatilById(Integer id);

	ResponseData getInfoByCategoryId(Integer id);

    Page<InfoVo> getInfoList(long pagenum, long pagesize, Integer id);
}
