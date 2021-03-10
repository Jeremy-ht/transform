package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Reservation;
import com.isoft.dao.ReservationMapper;
import com.isoft.pojo.vo.AdminVo;
import com.isoft.pojo.vo.ReservationVo2;
import com.isoft.service.ReservationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation> implements ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public Page<ReservationVo2> getReservationList(long pagenum, long pagesize) {
        Page<ReservationVo2> page = new Page<>(pagenum, pagesize);
        Page<ReservationVo2> ipage = reservationMapper.getReservationList(page);
        return ipage;
    }
}
