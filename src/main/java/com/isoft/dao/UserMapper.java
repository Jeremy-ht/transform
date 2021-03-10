package com.isoft.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface UserMapper extends BaseMapper<User> {

    Page<User> selCateList(Page<User> page);

    int updAbleUserById(@Param("id") int id,
                        @Param("state") Integer state);

    @Select("SELECT count(1) FROM user WHERE creatime >= CURDATE() and (state = 1 or state = 2)")
    int countUser();

    @Select("SELECT count(1) FROM user")
    int countUser2();

    @Update("UPDATE user SET phone=#{phone} WHERE id=#{id} AND state=1")
    int updateByIds(@Param("phone") String phone,
                    @Param("id") Integer id);

    @Update("update user set pwd = #{newPas} where id = #{id} ")
    int updPwdById(@Param("id") Integer id,
                   @Param("newPas") String newPas);
}
