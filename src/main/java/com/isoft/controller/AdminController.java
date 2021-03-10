package com.isoft.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.enums.ResultCodeEnum;
import com.isoft.pojo.entity.Admin;
import com.isoft.pojo.vo.AdminVo;
import com.isoft.pojo.vo.LoginUserPwdVo;
import com.isoft.service.AdminService;
import com.isoft.service.CommentService;
import com.isoft.service.DetailService;
import com.isoft.service.UserService;
import com.isoft.utils.CommonUtil;
import com.isoft.utils.MD5Code;
import com.isoft.utils.ResponseData;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Api(tags = "员工管理")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private DetailService detailService;

    /**
     * 登录
     */
    @PostMapping("/adminLogin")
    public ResponseData adminLogin(@RequestBody LoginUserPwdVo loginForm) {
        if (StringUtils.isBlank(loginForm.getUsername()) || StringUtils.isBlank(loginForm.getPassword())) {
            return ResponseData.error().message("用户名或密码不能为空！");
        }

        Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("username", loginForm.getUsername()));

        if (org.springframework.util.StringUtils.isEmpty(admin)) {
            return ResponseData.error().message("用户不存在，请重新登录！");
        }
        String salt = admin.getSalt();
        MD5Code md5Code = new MD5Code();
        String md5ofStr = md5Code.getMD5ofStr(loginForm.getPassword() + salt);
        if (!StringUtils.equals(md5ofStr, admin.getPassword())) {
            return ResponseData.error().message("密码错误，请重试！");
        }
        admin.setSalt("");
        admin.setPassword("******");
        return ResponseData.success().message("登录成功").data("data", admin);
    }


    /**
     * 修改密码
     */
    @PostMapping("updPasswordById/{id}")
    public ResponseData updPasswordById(@PathVariable("id") String id, @RequestBody LoginUserPwdVo loginForm) {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(loginForm.getPassword()) || StringUtils.isBlank(loginForm.getNewPassword())) {
            return ResponseData.error().message("请填写密码！");
        }

        Admin admin = adminService.getById(Integer.parseInt(id));
        String salt = admin.getSalt();
        if (org.springframework.util.StringUtils.isEmpty(admin)) {
            return ResponseData.error().message("用户不存在！");
        }
        MD5Code md5Code = new MD5Code();
        String md5ofStr = md5Code.getMD5ofStr(loginForm.getPassword() + salt);
        if (!StringUtils.equals(md5ofStr, admin.getPassword())) {
            return ResponseData.error().message("对不起，您输入的原密码有误，请重试！");
        }
        String newPas = md5Code.getMD5ofStr(loginForm.getNewPassword() + salt);

        return adminService.updPwdById(admin.getId(), newPas) ? ResponseData.success().code(ResultCodeEnum.SUCCESS.getCode()).message("修改密码成功！")
                : ResponseData.error().code(ResultCodeEnum.FAILED.getCode()).message("修改密码失败!");
    }


    /**
     * 添加
     */
    @PostMapping("/addAdmin/{creator}")
    public ResponseData addAdmin(@PathVariable("creator") String creator,
                                 @RequestBody Admin admin) {
        if (StringUtils.isBlank(admin.getName())) {
            return ResponseData.error().message("员工姓名不能为空");
        }
        if (StringUtils.isBlank(admin.getUsername())) {
            return ResponseData.error().message("用户名不能为空");
        }
        admin.setIcon("https://sf3-ttcdn-tos.pstatp.com/img/user-avatar/0df415c26ce5767088ea12245993360a~300x300.image");
        String salt = CommonUtil.getRandomSixNum();
        admin.setSalt(salt);
        MD5Code md5Code = new MD5Code();
        String aaaaa = md5Code.getMD5ofStr(admin.getUsername() + salt);
        admin.setPassword(aaaaa);
        admin.setCreator(Integer.parseInt(creator));
        return adminService.save(admin) ? ResponseData.success().code(ResultCodeEnum.SUCCESS.getCode()).message("添加员工成功！")
                : ResponseData.error().code(ResultCodeEnum.FAILED.getCode()).message("添加员工失败!");
    }


    /**
     * 删除
     */
    @GetMapping("/delAdmin/{id}")
    public ResponseData delAdmin(@PathVariable("id") Integer id) {
        return adminService.removeById(id) ? ResponseData.success().message("删除员工成功!")
                : ResponseData.error().message("删除员工失败!");
    }


    /**
     * 获取admin列表    分页+查询
     */
    @GetMapping("/getAdminList")
    public ResponseData getAdminList(@RequestParam(name = "pagenum", defaultValue = "1", required = false) long pagenum,
                                     @RequestParam(name = "pagesize", defaultValue = "10", required = false) long pagesize) {
        Page<AdminVo> page = adminService.getCateList(pagenum, pagesize);
        if (page != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("total", page.getTotal());
            map.put("data", page.getRecords());
            return ResponseData.success().message("获取分类列表成功！").data(map);
        }
        return ResponseData.error().message("获取分类列表失败！");
    }

    /**
     * home  数据
     */
    @GetMapping("/getHomeCount")
    public ResponseData getHomeCount() {

        //   新增评论
        int count = commentService.countComment();
        //   新增景点
        int count2 = detailService.countDetail();
        //   新增用户
        int count3 = userService.countUser();
        //    用户总计
        int count4 = userService.countUser2();
        Map<String, Integer> map = new HashMap<>();
        map.put("comment", count);
        map.put("detail", count2);
        map.put("todayUser", count3);
        map.put("totalUser", count4);
        return ResponseData.success().message("获取控制面板数据成功！").data("data", map);
    }


}

