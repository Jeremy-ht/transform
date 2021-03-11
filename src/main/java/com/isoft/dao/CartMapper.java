package com.isoft.dao;

import com.isoft.pojo.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoft.pojo.vo.CartVo;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *

 */
public interface CartMapper extends BaseMapper<Cart> {

    List<CartVo> list1(Integer id);

    @Update("update cart set amount = #{value} where id = #{id}")
    int updAmount(Integer uid, Integer id, Integer value);
}
