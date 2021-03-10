package com.isoft;

import com.isoft.utils.MD5Code;

public class Test {

    @org.junit.jupiter.api.Test
    public void t1(){
        MD5Code md5Code = new MD5Code();
        String aaaaa = md5Code.getMD5ofStr("6666668d821f");
        System.out.println(aaaaa);
    }

}
