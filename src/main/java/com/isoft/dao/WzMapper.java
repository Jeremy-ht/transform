package com.isoft.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Wz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *

 */
public interface WzMapper extends BaseMapper<Wz> {

	Page<Wz> getInfoList(Page<Wz> page,@Param("id") Integer id);
}
