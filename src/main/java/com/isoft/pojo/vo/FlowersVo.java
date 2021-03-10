package com.isoft.pojo.vo;


import com.isoft.pojo.entity.Flowers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowersVo extends Flowers {

    private String categoryname;

}
