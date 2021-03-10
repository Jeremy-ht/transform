package com.isoft.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Reservation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoft.pojo.vo.ReservationVo;
import com.isoft.pojo.vo.ReservationVo2;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReservationMapper extends BaseMapper<Reservation> {

    @Select("SELECT * FROM reservation WHERE userid = #{userid} and infoid = #{infoid} and state = 1 and iscomment = 0")
    Reservation isYY(@Param("userid") Integer userid,
                     @Param("infoid") Integer infoid);

    List<ReservationVo> getYYinfo(Integer userid);

    @Update("update reservation set iscomment = 1 where userid = #{userid} and infoid = #{infoid} and iscomment = 0")
    int upd(@Param("userid") Integer userid,
            @Param("infoid") Integer infoid);

    Page<ReservationVo2> getReservationList(Page<ReservationVo2> page);
}
