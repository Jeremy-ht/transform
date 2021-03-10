//package com.isoft.scenery.realm;
//
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class AdminRealm extends AuthorizingRealm {
//
//    @Autowired
//    private AdminService adminService;
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String username = (String) authenticationToken.getPrincipal();
//        System.out.println((String) authenticationToken.getPrincipal());
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
//        Admin admin = adminService.loginByName(usernamePasswordToken.getUsername());
//
//        if (ObjectUtils.isEmpty(admin)){
//            return null;
//        }
//        System.out.println(admin+" ----");
//
//        return new SimpleAuthenticationInfo(admin, admin.getPassword(), getName());
//    }
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        return null;
//    }
//
//}
