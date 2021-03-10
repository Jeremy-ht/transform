package com.isoft.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isoft.pojo.vo.OrderVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}

 */
public interface OrdersService extends IService<Orders> {

    Page<OrderVo> getCateList(long pagenum, long pagesize);
}
