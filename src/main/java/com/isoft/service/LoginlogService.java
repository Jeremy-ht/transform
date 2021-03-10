package com.isoft.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Loginlog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isoft.pojo.vo.LoginlogVo;


public interface LoginlogService extends IService<Loginlog> {

    Page<LoginlogVo> getLogList(long pagenum, long pagesize);

    Page<LoginlogVo> getUserLogList(long pagenum, long pagesize);
}
