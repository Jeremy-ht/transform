package com.isoft.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Infos;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
public interface InfosMapper extends BaseMapper<Infos> {

    @Update("update Infos set state = #{state}, releasetime =  #{now} where id = #{id} ")
    int pullInfo(@Param("id") Integer id,
                 @Param("state") Integer state,
                 @Param("now") LocalDateTime now);

    Page<Infos> getInfoList(Page<Infos> page, @Param("id") Integer id);

    @Update("update Infos set state = 0 where id = #{id} ")
    int upd(@Param("id") Integer id);
}
