package com.isoft.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Today;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *


 */
public interface TodayMapper extends BaseMapper<Today> {

	Page<Today> getInfoList(Page<Today> page);

	@Select("")
	List<Today> getTodayListToday(LocalDate now);
}
