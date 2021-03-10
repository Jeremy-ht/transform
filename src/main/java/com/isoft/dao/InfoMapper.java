package com.isoft.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Info;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoft.pojo.vo.DetailVo;
import com.isoft.pojo.vo.InfoVo;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface InfoMapper extends BaseMapper<Info> {

	Info getInfoDeatilById(Integer id);

    Page<InfoVo> getInfoList(Page<InfoVo> page, Integer id);

    @Update("update info set currentstate = 1 where id = #{infoid}")
    int updateState(Integer infoid);

    @Update("update info set currentstate = 0 where id = #{infoid}")
    int updateState0(Integer infoid);

    List<InfoVo> getTopList();

    List<InfoVo> getTopListBy(int i);
}
