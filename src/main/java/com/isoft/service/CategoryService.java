package com.isoft.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoft.pojo.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isoft.pojo.vo.CategoryVo;

public interface CategoryService extends IService<Category> {

    Page<CategoryVo> getCateList(long pagenum, long pagesize);
}
