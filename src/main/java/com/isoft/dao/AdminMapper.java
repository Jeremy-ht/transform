package com.isoft.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoft.pojo.vo.AdminVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


public interface AdminMapper extends BaseMapper<Admin> {

    Page<AdminVo> selCateList(Page<AdminVo> page);

    @Update("update admin set password = #{newPas} where id = #{id} ")
    int updPwdById(@Param("id") Integer id,
                   @Param("newPas") String newPas);
}
