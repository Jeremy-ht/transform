package com.isoft.dao;

import com.isoft.pojo.entity.Ordercent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoft.pojo.vo.EchartsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *


 */
public interface OrdercentMapper extends BaseMapper<Ordercent> {

    List<EchartsVo> getEchartsOrder();

    List<EchartsVo> getEchartsCate();

}
