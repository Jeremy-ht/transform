package com.isoft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.CartMapper;
import com.isoft.dao.OrdercentMapper;
import com.isoft.enums.ResultCodeEnum;
import com.isoft.pojo.entity.Address;
import com.isoft.pojo.entity.Cart;
import com.isoft.pojo.entity.Ordercent;
import com.isoft.pojo.entity.Orders;
import com.isoft.pojo.vo.AdminVo;
import com.isoft.pojo.vo.EchartsVo;
import com.isoft.pojo.vo.OrderVo;
import com.isoft.service.AddressService;
import com.isoft.service.CartService;
import com.isoft.service.OrdersService;
import com.isoft.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *

 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrdercentMapper ordercentMapper;
    @Autowired
    private AddressService addressService;

    // 立即购买
    @PostMapping("/addOrder2")
    public ResponseData addOrder2(@RequestBody Orders order) {

    	Integer amount = Integer.valueOf(order.getPhone());
    	Integer phoneid = Integer.valueOf(order.getName());

        Address a = addressService.getById(Integer.valueOf(order.getFlowerid()));
        if (a == null) {
            ResponseData.error().code(ResultCodeEnum.FAILED.getCode()).message("请选择地址");
        }
        order.setName(a.getName());
        order.setAddress(a.getAddress());
        order.setPhone(a.getPhone());
        boolean save = ordersService.save(order);
        if (save) {
            Ordercent ordercent = new Ordercent();
            ordercent.setAmount(amount);
            ordercent.setFlowerid(phoneid);
            ordercent.setOrderid(order.getId());
            ordercentMapper.insert(ordercent);

        }

        return save ? ResponseData.success().message("成功！")
                : ResponseData.error().code(ResultCodeEnum.FAILED.getCode()).message("失败了请重试!");

    }




    @PostMapping("/addOrder")
    public ResponseData addOrder(@RequestBody Orders order) {
        String[] split = order.getName().split(",");
        Address a = addressService.getById(Integer.valueOf(order.getFlowerid()));
        if (a == null) {
            ResponseData.error().code(ResultCodeEnum.FAILED.getCode()).message("请选择地址");
        }
        order.setName(a.getName());
        order.setAddress(a.getAddress());
        order.setPhone(a.getPhone());
        boolean save = ordersService.save(order);
        if (save) {
            // 查找购物车 购物车id  查找
            for (String i : split) {
                Cart cart = cartMapper.selectById(i);
                Ordercent ordercent = new Ordercent();
                ordercent.setAmount(cart.getAmount());
                ordercent.setFlowerid(cart.getFlowerid());
                ordercent.setOrderid(order.getId());
                ordercentMapper.insert(ordercent);
                cartMapper.deleteById(i);
            }
        }

        return save ? ResponseData.success().message("成功！")
                : ResponseData.error().code(ResultCodeEnum.FAILED.getCode()).message("失败了请重试!");

    }


    @GetMapping("/getOrderList")
    public ResponseData getOrderList(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
                                     @RequestParam(name = "pagesize", defaultValue = "10", required = false) long pagesize) {
        Page<OrderVo> page = ordersService.getCateList(pagenum, pagesize);
        if (page != null) {

            List<OrderVo> orderVos = page.getRecords();
            for (OrderVo orderVo : orderVos) {
                List<Ordercent> ordercents = ordercentMapper.selectList(new QueryWrapper<Ordercent>().eq("orderid", orderVo.getId())
                        .eq("state", 1));

                Integer aount = 0;
                for (Ordercent ordercent : ordercents) {
                    aount += ordercent.getAmount();
                }
                orderVo.setAmount(aount.toString());
            }

            Map<String, Object> map = new HashMap<>();
            map.put("total", orderVos);
            map.put("data", page.getRecords());
            return ResponseData.success().message("获取列表成功！").data(map);
        }

        return ResponseData.error().message("获取列表失败！");
    }


    // 统计
    @GetMapping("/getEchartsOrder")
    public ResponseData getEchartsOrder() {
        List<EchartsVo> list = ordercentMapper.getEchartsOrder();
        if (list.isEmpty() || list == null) {
            return ResponseData.error().message("数据获取失败");
        }
        List<Integer> l = new ArrayList<>();
        int[] ii = new int[12];
        list.stream().forEach(i ->
                ii[Integer.parseInt(i.getName()) - 1] = i.getValue()
        );
        return ResponseData.success().message("获取数据成功！").data("data", ii);

    }

    // 统计
    @GetMapping("/getEchartsCate")
    public ResponseData getEchartsCate() {
        List<EchartsVo> list = ordercentMapper.getEchartsCate();
        if (list.isEmpty() || list == null) {
            return ResponseData.error().message("数据获取失败");
        }
        return ResponseData.success().message("获取数据成功！").data("data", list);

    }

}

