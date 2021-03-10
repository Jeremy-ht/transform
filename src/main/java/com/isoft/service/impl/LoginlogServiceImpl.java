package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Loginlog;
import com.isoft.dao.LoginlogMapper;
import com.isoft.pojo.vo.LoginlogVo;
import com.isoft.service.LoginlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginlogServiceImpl extends ServiceImpl<LoginlogMapper, Loginlog> implements LoginlogService {
    @Autowired
    private LoginlogMapper loginlogMapper;

    @Override
    public Page<LoginlogVo> getLogList(long pagenum, long pagesize) {
        Page<LoginlogVo> page = new Page<>(pagenum, pagesize);
        Page<LoginlogVo> ipage = loginlogMapper.getLogList(page);
        return ipage;
    }

    @Override
    public Page<LoginlogVo> getUserLogList(long pagenum, long pagesize) {
        Page<LoginlogVo> page = new Page<>(pagenum, pagesize);
        Page<LoginlogVo> ipage = loginlogMapper.getUserLogList(page);
        return ipage;
    }
}
