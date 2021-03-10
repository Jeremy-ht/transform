package com.isoft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.CartMapper;
import com.isoft.enums.ResultCodeEnum;
import com.isoft.pojo.entity.Cart;
import com.isoft.pojo.entity.User;
import com.isoft.pojo.vo.AdminVo;
import com.isoft.pojo.vo.CartVo;
import com.isoft.service.CartService;
import com.isoft.service.UserService;
import com.isoft.utils.CommonUtil;
import com.isoft.utils.MD5Code;
import com.isoft.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @PostMapping("/addCart")
    public ResponseData addUser(@RequestBody Cart cart) {
//        if (org.apache.commons.lang3.StringUtils.isBlank(cart.getUname())) {
//            return ResponseData.error().message("用户名不能为空");
//        }
//        if (org.apache.commons.lang3.StringUtils.isBlank(user.getPwd())) {
//            return ResponseData.error().message("密码不能为空");
//        }
//        if (org.apache.commons.lang3.StringUtils.isBlank(user.getPhone())) {
//            return ResponseData.error().message("手机号不能为空");
//        }

        return cartService.save(cart) ? ResponseData.success().message("添加成功！")
                : ResponseData.error().code(ResultCodeEnum.FAILED.getCode()).message("添加失败!");
    }

    /**
     * 删除
     */
    @GetMapping("/delCart/{id}")
    public ResponseData delAdmin(@PathVariable("id") Integer id) {
        return cartService.removeById(id) ? ResponseData.success().message("删除成功!")
                : ResponseData.error().message("删除失败!");
    }

    /**
     * 获取Cart列表
     */
    @GetMapping("/getCartList/{id}")
    public ResponseData getCartList(@PathVariable("id") Integer id) {
        List<CartVo> list = cartService.list1(id);
        if (list != null) {
            return ResponseData.success().message("获取分类列表成功！").data("data", list);
        }
        return ResponseData.error().message("获取购物车失败，请刷新！");
    }

    /**
     * 更新购物车商品数量
     */
    @GetMapping("/updAmount/{uid}/{id}/{value}")
    public ResponseData updAmount(@PathVariable("uid") Integer uid,
                                  @PathVariable("id") Integer id,
                                  @PathVariable("value") Integer value) {
        cartMapper.updAmount(uid, id, value);
        return ResponseData.error().message("获取购物车失败，请刷新！");
    }

    /**
     * 查询购物车商品数量
     */
    @GetMapping("/getShoppingNum/{id}")
    public ResponseData getShoppingNum(@PathVariable("id") Integer id) {
        Integer integer = cartMapper.selectCount(new QueryWrapper<Cart>().eq("userid", id).eq("state", 1));
        return ResponseData.success().message("成功!").data("data", integer);
    }


}

