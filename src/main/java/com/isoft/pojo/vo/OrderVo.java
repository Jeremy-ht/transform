package com.isoft.pojo.vo;


import com.isoft.pojo.entity.Cart;
import com.isoft.pojo.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo extends Orders {

    private String uname;
    private String uphone;
    private String image;


}
