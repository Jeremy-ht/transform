package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Orders;
import com.isoft.dao.OrdersMapper;
import com.isoft.pojo.vo.AdminVo;
import com.isoft.pojo.vo.OrderVo;
import com.isoft.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *


 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public Page<OrderVo> getCateList(long pagenum, long pagesize) {
        Page<OrderVo> page = new Page<>(pagenum, pagesize);
        Page<OrderVo> ipage = ordersMapper.selCateList(page);
        return ipage;
    }
}
