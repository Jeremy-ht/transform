package com.isoft.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UserService extends IService<User> {

    Page<User> getCateList(long pagenum, long pagesize);

    boolean updAbleUserById(int parseInt , Integer state);

    int countUser();

    int countUser2();

    boolean updateByIds(User user);

    boolean updPwdById(Integer id, String newPas);
}
