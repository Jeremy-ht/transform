package com.isoft.service.impl;

import com.isoft.pojo.entity.Address;
import com.isoft.dao.AddressMapper;
import com.isoft.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

}
