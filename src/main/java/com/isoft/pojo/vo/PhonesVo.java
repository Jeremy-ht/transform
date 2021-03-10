package com.isoft.pojo.vo;


import com.isoft.pojo.entity.Flowers;
import com.isoft.pojo.entity.Phones;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhonesVo extends Phones {

    private String categoryname;

}
