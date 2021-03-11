package com.isoft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isoft.enums.ResultCodeEnum;
import com.isoft.pojo.entity.Address;
import com.isoft.pojo.entity.Cart;
import com.isoft.pojo.entity.User;
import com.isoft.pojo.vo.CartVo;
import com.isoft.service.AddressService;
import com.isoft.service.UserService;
import com.isoft.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *

 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/addAddress")
    public ResponseData addUser(@RequestBody Address address) {
        return addressService.save(address) ? ResponseData.success().message("新增成功！")
                : ResponseData.error().code(ResultCodeEnum.FAILED.getCode()).message("注册失败了请重试!");

    }

    /**
     * 获取 Address 列表
     */
    @GetMapping("/getAddressList/{id}")
    public ResponseData getAddressList(@PathVariable("id") Integer id) {
        List<Address> list = addressService.list(new QueryWrapper<Address>().eq("userid", id).orderByDesc("creatime"));
        if (list != null) {
            return ResponseData.success().message("获取分类列表成功！").data("data", list);
        }
        return ResponseData.error().message("获取购物车失败，请刷新！");
    }


//    /**
//     * 获取Cart列表
//     */
//    @GetMapping("/getCartList/{id}")
//    public ResponseData getCartList(@PathVariable("id") Integer id) {
//        List<CartVo> list = cartService.list1(id);
//        if (list != null) {
//            return ResponseData.success().message("获取分类列表成功！").data("data", list);
//        }
//        return ResponseData.error().message("获取购物车失败，请刷新！");
//    }

}

