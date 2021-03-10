package com.isoft.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Detail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoft.pojo.vo.DetailVo;
import com.isoft.pojo.vo.EchartsVo;
import com.isoft.pojo.vo.FlowersVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface DetailMapper extends BaseMapper<Detail> {

    Page<FlowersVo> getSceneryList(@Param("page") Page<FlowersVo> page,
                                   @Param("categoryId") Integer categoryId);

    FlowersVo getSceneryInfo(Integer id);

    @Update("update flowers set state = #{state} where id = #{id}")
    int pullScenery(@Param("id") Integer id, @Param("state") Integer state);

    @Select("SELECT count(1) FROM detail WHERE releasetime >= CURDATE() and state = 1 and draft = 1")
    int countDetail();

    List<Detail> getSearchContent(String content);

    List<EchartsVo> getEchartsCategory();

    List<EchartsVo> getEchartsUser(String date);

    List<EchartsVo> getEchartsYY(String valueOf);

    List<EchartsVo> getEchartsInfo(String valueOf);
}
