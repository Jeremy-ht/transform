package com.isoft.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Detail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isoft.pojo.entity.Flowers;
import com.isoft.pojo.vo.DetailVo;
import com.isoft.pojo.vo.EchartsVo;
import com.isoft.pojo.vo.FlowersVo;
import com.isoft.utils.ResponseData;

import java.util.List;


public interface DetailService extends IService<Detail> {

    Page<FlowersVo> getSceneryList(long pagenum, long pagesize, Integer categoryId);

    ResponseData getSceneryInfo(Integer id);

    boolean pullScenery(Integer id,Integer state);

    int countDetail();

    List<Detail> getSearchContent(String content);

    List<EchartsVo> getEchartsCategory();

    List<EchartsVo> getEchartsUser();

    List<EchartsVo> getEchartsYY();

    List<EchartsVo> getEchartsInfo();
}
