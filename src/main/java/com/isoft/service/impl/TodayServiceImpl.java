package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.dao.InfosMapper;
import com.isoft.pojo.entity.Infos;
import com.isoft.pojo.entity.Today;
import com.isoft.dao.TodayMapper;
import com.isoft.service.TodayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class TodayServiceImpl extends ServiceImpl<TodayMapper, Today> implements TodayService {

    @Autowired
    private TodayMapper todayMapper;


    @Override
    public Page<Today> getInfoList(long pagenum, long pagesize) {
        Page<Today> page = new Page<>(pagenum, pagesize);
        try {
            Page<Today> p = todayMapper.getInfoList(page);
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getInfoList：错误" + e);
            return null;
        }
    }

    @Override
    public List<Today> getTodayListToday() {
        int year = LocalDate.now().getYear();
        int monthValue = LocalDate.now().getMonthValue();
        int dayOfMonth = LocalDate.now().getDayOfMonth();

        return todayMapper.getTodayListToday(year,monthValue,dayOfMonth);
    }
}
