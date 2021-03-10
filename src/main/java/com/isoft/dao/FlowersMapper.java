package com.isoft.dao;

import com.isoft.pojo.entity.Flowers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoft.utils.ResponseData;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface FlowersMapper extends BaseMapper<Flowers> {

    @Select("SELECT * FROM flowers WHERE isshow = 'false' and state = 1 ORDER BY creatime DESC limit 4")
    List<Flowers> newFlower();

    @Select("SELECT * FROM flowers WHERE isshow = 'true' and state = 1 ORDER BY creatime DESC limit 8")
    List<Flowers> newFlower3();

    @Select("SELECT categoryname FROM category where id = #{id}")
    String getSceneryListByCate(Integer id);


    @Update("UPDATE flowers SET state=0 WHERE id=#{id}")
    boolean removeByIdss(Integer id);
}
