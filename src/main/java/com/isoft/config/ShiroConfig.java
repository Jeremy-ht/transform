//package com.isoft.scenery.config;
//
//import com.isoft.scenery.realm.AdminRealm;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.apache.shiro.mgt.SecurityManager;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Configuration
//public class ShiroConfig {
//
//    // ShiroFilterFactoryBean
//    @Bean
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
//        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//        bean.setSecurityManager(securityManager);
//
//        Map<String, String> filterChainMap= new LinkedHashMap<>();
////        filterChainMap.put("/admin/addAdmin","anon");
////        filterChainMap.put("/login","anon");
//        filterChainMap.put("/**","anon");
//        bean.setFilterChainDefinitionMap(filterChainMap);
//
//        return bean;
//    }
//
//    // SecurityManager
//    @Bean(name = "securityManager")
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("adminRealm") AdminRealm adminRealm){
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(adminRealm);
//        return securityManager;
//    }
//
//    // realm    @Qualifier("credentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher
//    @Bean
//    public AdminRealm adminRealm(){
//        AdminRealm realm = new AdminRealm();
////        realm.setCredentialsMatcher(hashedCredentialsMatcher);
//        return realm;
//    }
//
//   /* // 凭证匹配器
//    @Bean(name = "credentialsMatcher")
//    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName(Const.HASHNAME);
//        credentialsMatcher.setHashIterations(Const.HASHITERATIONS);
//        credentialsMatcher.setStoredCredentialsHexEncoded(true);
//        return credentialsMatcher;
//    }*/
//
//}
