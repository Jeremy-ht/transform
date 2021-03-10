package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Admin;
import com.isoft.dao.AdminMapper;
import com.isoft.pojo.vo.AdminVo;
import com.isoft.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Page<AdminVo> getCateList(long pagenum, long pagesize) {
        Page<AdminVo> page = new Page<>(pagenum, pagesize);
        Page<AdminVo> ipage = adminMapper.selCateList(page);
        return ipage;
    }

    @Override
    public boolean updPwdById(Integer id, String newPas) {
        return adminMapper.updPwdById(id, newPas) == 1;
    }
}
