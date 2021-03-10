package com.isoft.service;

import com.isoft.pojo.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isoft.pojo.vo.CartVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}

 */
public interface CartService extends IService<Cart> {

    List<CartVo> list1(Integer id);
}
