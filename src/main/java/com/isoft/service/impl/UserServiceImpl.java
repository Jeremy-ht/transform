package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.User;
import com.isoft.dao.UserMapper;
import com.isoft.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> getCateList(long pagenum, long pagesize) {

        Page<User> page = new Page<>(pagenum, pagesize);
        Page<User> ipage = userMapper.selCateList(page);
        return ipage;
    }

    @Override
    public boolean updAbleUserById(int parseInt, Integer state) {
        return userMapper.updAbleUserById(parseInt, state) == 1 ? true : false;
    }

    @Override
    public int countUser() {
        return userMapper.countUser();
    }

    @Override
    public int countUser2() {
        return userMapper.countUser2();
    }

    @Override
    public boolean updateByIds(User user) {
        return userMapper.updateByIds(user.getPhone(), user.getId()) > 0;
    }

    @Override
    public boolean updPwdById(Integer id, String newPas) {
        return userMapper.updPwdById(id, newPas) == 1;
    }
}
