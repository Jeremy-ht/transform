package com.isoft.service.impl;

import com.isoft.pojo.entity.Cart;
import com.isoft.dao.CartMapper;
import com.isoft.pojo.vo.CartVo;
import com.isoft.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *


 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<CartVo> list1(Integer id) {
        return cartMapper.list1(id);
    }
}
