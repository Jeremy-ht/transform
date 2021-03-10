package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Detail;
import com.isoft.dao.DetailMapper;
import com.isoft.pojo.entity.Flowers;
import com.isoft.pojo.vo.DetailVo;
import com.isoft.pojo.vo.EchartsVo;
import com.isoft.pojo.vo.FlowersVo;
import com.isoft.service.DetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.isoft.utils.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;


@Service
@Slf4j
public class DetailServiceImpl extends ServiceImpl<DetailMapper, Detail> implements DetailService {

    @Autowired
    private DetailMapper detailMapper;

    @Override
    public Page<FlowersVo> getSceneryList(long pagenum, long pagesize,
                                          Integer categoryId) {
        Page<FlowersVo> page = new Page<>(pagenum, pagesize);
        try {
            Page<FlowersVo> p = detailMapper.getSceneryList(page, categoryId);
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getSceneryList：错误" + e);
            return null;
        }
    }

    @Override
    public ResponseData getSceneryInfo(Integer id) {
        FlowersVo d = detailMapper.getSceneryInfo(id);
        if (StringUtils.isEmpty(d)) {
            return ResponseData.error().message("获取详情失败！");
        }
        return ResponseData.success().message("获取详情成功！").data("data", d);
    }

    @Override
    public boolean pullScenery(Integer id,Integer state) {

        return detailMapper.pullScenery(id,state) == 1;
    }

    @Override
    public int countDetail() {

        return detailMapper.countDetail();
    }

    @Override
    public List<Detail> getSearchContent(String content) {
        return detailMapper.getSearchContent(content);
    }

    @Override
    public List<EchartsVo> getEchartsCategory() {
        return detailMapper.getEchartsCategory();
    }

    @Override
    public List<EchartsVo> getEchartsUser() {
        int year = LocalDate.now().getYear();
        return detailMapper.getEchartsUser(String.valueOf(year));
    }

    @Override
    public List<EchartsVo> getEchartsYY() {
        int year = LocalDate.now().getYear();
        return detailMapper.getEchartsYY(String.valueOf(year));
    }

    @Override
    public List<EchartsVo> getEchartsInfo() {
        int year = LocalDate.now().getYear();
        return detailMapper.getEchartsInfo(String.valueOf(year));
    }
}
