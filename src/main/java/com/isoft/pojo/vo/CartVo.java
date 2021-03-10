package com.isoft.pojo.vo;


import com.isoft.pojo.entity.Cart;
import com.isoft.pojo.entity.Flowers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVo extends Cart {

    private String name;
    private String price;
    private String image;

}
