package com.isoft.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Category;
import com.isoft.dao.CategoryMapper;
import com.isoft.pojo.vo.CategoryVo;
import com.isoft.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Page<CategoryVo> getCateList(long pagenum, long pagesize) {
        Page<CategoryVo> page = new Page<>(pagenum, pagesize);
        Page<CategoryVo> ipage = categoryMapper.selCateList(page);
        return ipage;
    }
}
