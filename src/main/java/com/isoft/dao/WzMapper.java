package com.isoft.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Wz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoft.pojo.vo.WzVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *

 */
public interface WzMapper extends BaseMapper<Wz> {

	Page<WzVo> getInfoList(Page<WzVo> page, @Param("id") Integer id);

	@Update("update Wz set top = 1 where id = #{id}")
	int upd(Integer id);
}
