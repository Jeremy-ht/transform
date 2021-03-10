package com.isoft.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Reservation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isoft.pojo.vo.ReservationVo2;

public interface ReservationService extends IService<Reservation> {

    Page<ReservationVo2> getReservationList(long pagenum, long pagesize);
}
