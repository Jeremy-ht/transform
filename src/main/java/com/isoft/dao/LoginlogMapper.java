package com.isoft.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Loginlog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoft.pojo.vo.LoginlogVo;
import org.apache.ibatis.annotations.Select;


public interface LoginlogMapper extends BaseMapper<Loginlog> {

    @Select("select l.*, a.username, a.name from loginlog l " +
            "left join admin a on l.userid = a.id " +
            "where l.uid is null and l.userid is not null " +
            "order by l.logintime desc")
    Page<LoginlogVo> getLogList(Page<LoginlogVo> page);

    @Select("select l.*, u.uname username from loginlog l " +
            "left join user u on l.uid = u.id " +
            "where l.userid is null order by l.logintime desc")
    Page<LoginlogVo> getUserLogList(Page<LoginlogVo> page);
}
