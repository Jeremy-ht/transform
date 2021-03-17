package com.isoft.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MP 自动填充
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setInsertFieldValByName("creatime", LocalDateTime.now(), metaObject);
        this.setInsertFieldValByName("logintime", LocalDateTime.now(), metaObject);
        this.setInsertFieldValByName("releasetime", LocalDateTime.now(), metaObject);
        this.setInsertFieldValByName("state", 1, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setInsertFieldValByName("releasetime", LocalDateTime.now(), metaObject);
    }
}
